package com.adil.learnrest.restfulwebservices.Controller;

import com.adil.learnrest.restfulwebservices.Model.User;
import com.adil.learnrest.restfulwebservices.Service.UserDao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
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
        User user = userDao.findOne(id);
        if(user == null){
            throw new UserNotFoundException("Id: "+id);
        }
        return user;
    }

    @PostMapping(path = "/users")
    public ResponseEntity<Object> save(@Valid @RequestBody User user){
        User savedUser = userDao.save(user);
       URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

       return ResponseEntity.created(location).build();

    }

    @DeleteMapping(path = "users/{id}")
    public User deleteUser(@PathVariable int id){
        User user = userDao.deleteById(id);
        if(user == null){
            throw new UserNotFoundException("Id: "+id);
        }
        return user;
    }
}
