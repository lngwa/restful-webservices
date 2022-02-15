package com.lewis.in28minsrestfulwebservices.controller;

import com.lewis.in28minsrestfulwebservices.dao.UserDao;
import com.lewis.in28minsrestfulwebservices.exception.UserNotFoundException;
import com.lewis.in28minsrestfulwebservices.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@EnableHypermediaSupport(type = {})
@RestController
public class UserController {

    @Autowired
    UserDao userDao;

    @GetMapping("/users")
    public ResponseEntity getAllUsers(){
        List<EntityModel> response = new ArrayList<>();
        List<User> users = userDao.findAll();
        for(User user: users){
            WebMvcLinkBuilder linkBuilder = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findUser(user.getId()));
            EntityModel<User> model = EntityModel.of(user);
            model.add(linkBuilder.withRel("user"+user.getId()));
            response.add(model);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<EntityModel> findUser(@PathVariable("id") int userId){
        User user = userDao.findOne(userId);
        if(user == null){
            throw new UserNotFoundException("Did not find user with id: " + userId);
        }
        WebMvcLinkBuilder linkBuilder = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers());
        EntityModel<User> model = EntityModel.of(user);
        model.add(linkBuilder.withRel("Get All users"));

        return ResponseEntity.ok(model);
    }

    @PostMapping("/users")
    public ResponseEntity saveUser(@Valid @RequestBody User user){
        User res = userDao.save(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(res.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity deleteUser(@PathVariable int id){
        User user = userDao.deleteById(id);
        if(user == null){
            throw new UserNotFoundException("Did not find user with id: " + id);
        }
        return ResponseEntity.noContent().build();
    }

}
