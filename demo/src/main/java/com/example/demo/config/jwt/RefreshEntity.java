package com.example.demo.config.jwt;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class RefreshEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String refresh;
    private String expiration;

    public RefreshEntity(String username, String refresh, String expiration) {
        this.username = username;
        this.refresh = refresh;
        this.expiration = expiration;
    }
}
