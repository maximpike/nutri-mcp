package com.maximpike.nutrimc.entity;

import com.maximpike.nutrimc.entity.enums.DietType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "dietary_profiles")
@Getter
@Setter
@NoArgsConstructor
public class DietaryProfile extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "diet_type", nullable = false)
    private DietType dietType;

    @Column(name = "calorie_target")
    private int calorieTarget;

    public DietaryProfile(DietType dietType) {
        this.dietType = dietType;
    }

    // TODO: Add validation - calorieTarget should be > 500
    // TODO: Add allergies field (JSON array)
    // TODO: Add culturalPreferences field (JSON array)

}
