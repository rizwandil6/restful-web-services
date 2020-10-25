package com.adil.learnrest.restfulwebservices.Service;

import com.adil.learnrest.restfulwebservices.Model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class UserDao {

    private static List<User> users = new ArrayList<User>();
    private static int user_count = 4;

    static{
        users.add(new User(1,"adil",new Date()));
        users.add(new User(2,"rahil",new Date()));
        users.add(new User(3,"uzma",new Date()));
        users.add(new User(4,"iram",new Date()));
    }

    public List<User> findAll(){
        return users;
    }

    public User save(User user){
        if(user.getId() == null)
            user.setId(++user_count);
        users.add(user);
        return user;
    }

    public User findOne(Integer id){
        for(User user: users){
            if(user.getId() == id){
                return user;
            }
        }
        return null;
    }
}
