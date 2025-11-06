package com.maximpike.nutrimc.entity;

import jakarta.persistence.*;
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

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private DietaryProfile dietaryProfile;

    public User(String email) {
        this.email = email;
    }

    public void setDietaryProfile(DietaryProfile dietaryProfile) {
        if (dietaryProfile != null) {
            dietaryProfile.setUser(this);
        }
        this.dietaryProfile = dietaryProfile;
    }
    // TODO: Add googleId when implementing OAuth (Day 3)
    // TODO: Add profilePictureUrl for user avatar
    // TODO: Service layer validation: name cannot be blanked once set
}
