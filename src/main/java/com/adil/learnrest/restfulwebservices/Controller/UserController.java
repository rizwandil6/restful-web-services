package com.adil.learnrest.restfulwebservices.Controller;

import com.adil.learnrest.restfulwebservices.Model.User;
import com.adil.learnrest.restfulwebservices.Service.UserDao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping(path = "/users")
    public List<User> retrieveAllUsers(){
        return userDao.findAll();
    }

    @GetMapping(path = "users/{id}")
    public User getUser(@PathVariable int id){
        return userDao.findOne(id);
    }

    @PostMapping(path = "/users")
    public ResponseEntity<Object> save(@RequestBody User user){
        User savedUser = userDao.save(user);
       URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(savedUser.getId()).toUri();

       return ResponseEntity.created(location).build();

    }
}
