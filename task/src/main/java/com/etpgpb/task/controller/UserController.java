package com.etpgpb.task.controller;

import com.etpgpb.task.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.Optional;

public class UserController {

    /*@Autowired
    UserRepository userRepository;



    @RequestMapping("user/{userId}")
    public User getUsers(@PathParam("userId") Long userId) {
        Optional<User> result = userRepository.findById(userId);
        return result.orElseThrow(() -> new RuntimeException(String.format("Cannot get User with id", userId)));
    }*/
}
