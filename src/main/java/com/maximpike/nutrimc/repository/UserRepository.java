package com.maximpike.nutrimc.repository;

import com.maximpike.nutrimc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Spring automatically provides
    // - save(), findById(), findAll(), delete(), etc

    // TODO: Add custom query methods when needed
    // Example: Optional<User> findByEmail(String email)

}
