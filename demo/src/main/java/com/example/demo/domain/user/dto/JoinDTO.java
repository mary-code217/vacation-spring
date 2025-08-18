package com.example.demo.domain.user.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter @Getter
@Data
public class JoinDTO {

    private String username;
    private String password;
    private String role;
}
