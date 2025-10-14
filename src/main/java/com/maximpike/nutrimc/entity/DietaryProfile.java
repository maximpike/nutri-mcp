package com.maximpike.nutrimc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "dietary_profiles")
@Getter
@Setter
@NoArgsConstructor
public class DietaryProfile extends BaseEntity {

    // TODO: Add @JoinColumn and @OneToOne relationship to User after we test basic entity

    @Column(name = "diet_type", nullable = false)
    private String dietType;

    @Column(name = "calorie_target")
    private int calorieTarget;

    public DietaryProfile(String dietType) {
        this.dietType = dietType;
    }

    // TODO: Add validation - calorieTarget should be > 500
    // TODO: Refactor dietType to ENUM
    // TODO: Add allergies field (JSON array)
    // TODO: Add culturalPreferences field (JSON array)
    // TODO: Add relationship to User entity

}
