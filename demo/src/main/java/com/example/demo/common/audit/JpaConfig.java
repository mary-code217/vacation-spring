package com.example.demo.common.audit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
class JpaConfig {
    @Bean
    AuditorAware<Long> auditorAware() {
        return () -> Optional.of(-1L);
    }
}
