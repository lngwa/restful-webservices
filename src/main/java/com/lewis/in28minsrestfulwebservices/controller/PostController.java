package com.lewis.in28minsrestfulwebservices.controller;

import com.lewis.in28minsrestfulwebservices.dao.PostDao;
import com.lewis.in28minsrestfulwebservices.exception.PostAlreadyExistException;
import com.lewis.in28minsrestfulwebservices.exception.PostNotFoundException;
import com.lewis.in28minsrestfulwebservices.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequestMapping("/users/{userId}")
@RestController
public class PostController {
    @Autowired
    private PostDao postDao;

    @GetMapping("/posts")
    public ResponseEntity getPosts(@PathVariable int userId){
        List<Post> posts = postDao.findAllById(userId);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity getPostForUser(@PathVariable int userId, @PathVariable int id){
        Post post = postDao.findById(userId, id);
        if(post == null){
            throw new PostNotFoundException(String.format("Could not find post with post id: %d for this user", id));
        }
        return ResponseEntity.ok(post);
    }

    @PostMapping("/posts")
    public ResponseEntity createPost(@RequestBody Post post, @PathVariable int userId){
        post.setId(0);
        post.setUserId(userId);
        Post savePost = postDao.savePost(post);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savePost.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
