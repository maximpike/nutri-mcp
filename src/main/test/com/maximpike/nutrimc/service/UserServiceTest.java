package com.maximpike.nutrimc.service;

import com.maximpike.nutrimc.entity.User;
import com.maximpike.nutrimc.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldCreateUserWithValidEmail() {
        // given
        String email = "email@unittest.com";
        when(userRepository.findByEmail(email))
                .thenReturn(Optional.empty());

        User savedUser = new User(email);
        savedUser.setId(1L);
        when(userRepository.save(any(User.class)))
                .thenReturn(savedUser);

        // when
        User testUser = userService.createUser(email);

        // then
        assertThat(testUser.getId()).isEqualTo(1L); //How do we know that we will have the same ID generate input vs service
        verify(userRepository).findByEmail(email);
        verify(userRepository).save(any(User.class));
    }

    @Test
    void shouldThrowExceptionWhenEmailAlreadyExists() {
        // given
        String email = "existing@email.com";

        User existingUser = new User(email);
        existingUser.setId(1L);
        when(userRepository.findByEmail(email))
                .thenReturn(Optional.of(existingUser));

        // when
        assertThatThrownBy(() -> userService.createUser(email))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("already exists");

        // then
        verify(userRepository).findByEmail(any());
        verify(userRepository, never()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenEmailIsNull() {
        // when
        assertThatThrownBy(() -> userService.createUser(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Email cannot be null or blank");

        // then
        verify(userRepository, never()).findByEmail(any());
        verify(userRepository, never()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenEmailIsBlank() {
        // given
        String email = "  ";

        // when
        assertThatThrownBy(() -> userService.createUser(email))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Email cannot be null or blank");

        // then
        verify(userRepository, never()).findByEmail(any());
        verify(userRepository, never()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenInvalidEmailFormat() {
        // given
        String email = "invalid email";

        // when
        assertThatThrownBy(() -> userService.createUser(email))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid format, email must contain an @ symbol");

        // then
        verify(userRepository, never()).findByEmail(any());
        verify(userRepository, never()).save(any());
    }
}