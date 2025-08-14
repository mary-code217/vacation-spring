package com.example.demo.features.user.controller;

import com.example.demo.features.user.entity.User;
import com.example.demo.features.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class userController {

    private final UserService userService;

    @PostMapping("/join")
    public Boolean userController(User user) {

        return userService.createUser(user);
    }
}
