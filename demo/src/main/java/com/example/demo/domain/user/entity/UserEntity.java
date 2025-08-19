package com.example.demo.domain.user.entity;

import com.example.demo.domain.user.dto.JoinDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter @Getter
@NoArgsConstructor
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    private String role;

    public UserEntity(JoinDTO joinDTO) {
        this.username = joinDTO.getUsername();
        this.password = joinDTO.getPassword();
        this.role = "ROLE_"+joinDTO.getRole();
    }
}
