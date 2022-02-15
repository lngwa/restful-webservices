package com.lewis.in28minsrestfulwebservices.dao;

import com.lewis.in28minsrestfulwebservices.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class UserDao {
    private static List<User> users = new ArrayList<>();
    private static int usercount = 4;
    static {
        users.add(new User(1, "Lewis", new Date()));
        users.add(new User(2, "Melvis", new Date()));
        users.add(new User(3, "Brianna", new Date()));
        users.add(new User(4, "Ethan", new Date()));
    }

    public List<User> findAll(){
        return users;
    }

    public User save(User user){
        if(user.getId() == 0){
            user.setId(++usercount);
        }
        users.add(user);
        return user;
    }

    public User findOne(int id){
        for (User user: users) {
            if(user.getId() == id){
                return user;
            }
        }
        return null;
    }

    public User deleteById(int id){
        User res = null;
        for (User user: users) {
            if(user.getId() == id){
                res = user;
                break;
            }
        }
        return users.remove(res) ? res: null;
    }
}
