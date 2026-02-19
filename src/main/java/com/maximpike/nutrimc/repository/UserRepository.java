package com.maximpike.nutrimc.repository;

import com.maximpike.nutrimc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find user by email address.
     * @param email User's email address
     * @return Optional containing user if found, empty otherwise
     */
    Optional<User> findByEmail(String email);

    /**
     * Find user by name.
     * @param name User's name
     * @return Optional containing user if found, empty otherwise
     */
    Optional<User> findByName(String name);

}
