package com.maximpike.nutrimc.service;

import com.maximpike.nutrimc.entity.User;
import com.maximpike.nutrimc.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Create a new user with email validation and duplicate checking.
     *
     * @param email User's email address
     * @return Created and saved user
     * @throws IllegalArgumentException if email is invalid
     * @throws IllegalStateException if email already exists
     */
    public User createUser(String email) {
        validateEmailNotBlank(email);
        validateEmailFormat(email);
        checkEmailNotDuplicate(email);
        User user = new User(email);
        return userRepository.save(user);
    }

    private void checkEmailNotDuplicate(String email) {
        if(userRepository.findByEmail(email).isPresent()) {
            throw new IllegalStateException("User with email " + email + " already exists");
        }
    }

    private void validateEmailFormat(String email) {
        if(!email.contains("@")) {
            throw new IllegalArgumentException("Invalid format, email must contain an @ symbol");
        }
    }

    private void validateEmailNotBlank(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or blank");
        }
    }
}
