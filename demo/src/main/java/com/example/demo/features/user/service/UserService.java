package com.example.demo.features.user.service;

import com.example.demo.features.user.entity.User;
import com.example.demo.features.user.repositoy.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Boolean createUser(User user) {

        userRepository.save(user);

        return true;
    }
}
