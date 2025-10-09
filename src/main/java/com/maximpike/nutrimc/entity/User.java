package com.maximpike.nutrimc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseEntity {

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    @Column(name = "email", nullable = false, unique = true, length=255)
    private String email;

    @Column(name = "name")
    private String name;

    public User(String email) {
        this.email = email;
    }

    // TODO: Add googleId when implementing OAuth (Day 3)
    // TODO: Add profilePictureUrl for user avatar
    // TODO: Add relationship to DietaryProfile after creating that entity
    // TODO: Service layer validation: name cannot be blanked once set
}
