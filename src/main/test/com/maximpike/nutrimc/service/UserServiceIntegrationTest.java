package com.maximpike.nutrimc.service;

import com.maximpike.nutrimc.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserServiceIntegrationTest {

    @Autowired
    private UserService userService;

    @Test
    void shouldCreateUser() {
        User user = userService.createUser("integration@email.com");
        assertThat(user.getId()).isNotNull();
        assertThat(user.getEmail()).isEqualTo("integration@email.com");
    }
}