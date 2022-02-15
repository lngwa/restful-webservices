package com.lewis.in28minsrestfulwebservices.dao;

import com.lewis.in28minsrestfulwebservices.exception.UserNotFoundException;
import com.lewis.in28minsrestfulwebservices.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class PostDao {
    private static List<Post> posts = new ArrayList<>();
    @Autowired
    private UserDao userDao;
    private static int postCounter = 4;

    static {
        posts.add(new Post(1,1,"This looks good", new Date()));
        posts.add(new Post(2,1,"This is even better", new Date()));
        posts.add(new Post(3,2,"Live your dreams", new Date()));
        posts.add(new Post(4,3,"Take action", new Date()));
    }

    public List<Post> findAllById(int userId){
        List<Post> results = new ArrayList<>();
        if(userDao.findOne(userId) == null){
            throw new UserNotFoundException("Did not find user with id: " + userId);
        }
        for(Post post: posts){
            if(post.getUserId() == userId){
                results.add(post);
            }
        }
        return results;
    }

    public Post findById(int userId, int id) {
        if(userDao.findOne(userId) == null){
            throw new UserNotFoundException("Did not find user with id: " + userId);
        }
        for(Post post: posts){
            if(post.getUserId() == userId && post.getId() == id){
                return post;
            }
        }
        return null;
    }

    public Post savePost(Post post) {
        if(userDao.findOne(post.getUserId()) == null){
            throw new UserNotFoundException("Did not find user with id: " + post.getUserId());
        }
        post.setId(++postCounter);
        posts.add(post);
        return post;
    }
}
