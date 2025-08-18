package com.example.demo.domain.user.service;

import com.example.demo.domain.user.dto.JoinDTO;
import com.example.demo.domain.user.entity.UserEntity;
import com.example.demo.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public boolean join(JoinDTO joinDTO) {
        Boolean isUser = userRepository.existsByUsername(joinDTO.getUsername());
        if (isUser) {
            return false;
        }

        joinDTO.setPassword(bCryptPasswordEncoder.encode(joinDTO.getPassword()));

        UserEntity user = new UserEntity(joinDTO);

        userRepository.save(user);

        return true;
    }
}
