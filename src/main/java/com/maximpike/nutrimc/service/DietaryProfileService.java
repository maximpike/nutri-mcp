package com.maximpike.nutrimc.service;

import com.maximpike.nutrimc.entity.DietaryProfile;
import com.maximpike.nutrimc.entity.enums.DietType;
import com.maximpike.nutrimc.repository.DietaryProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class DietaryProfileService {

    private final DietaryProfileRepository dietaryProfileRepository;

    public DietaryProfileService(DietaryProfileRepository dietaryProfileRepository) {
        this.dietaryProfileRepository = dietaryProfileRepository;
    }

    /**
     * Create a new dietary profile.
     *
     * @param dietType Diet type (enum)
     * @param calorieTarget Daily calorie goal
     * @return Created and saved dietary profile
     * @throws IllegalArgumentException if diet type is null
     */
    public DietaryProfile createProfile(DietType dietType, int calorieTarget) {
        validateDietType(dietType);
        DietaryProfile profile = new DietaryProfile(dietType);
        profile.setCalorieTarget(calorieTarget);
        return dietaryProfileRepository.save(profile);
    }

    private void validateDietType(DietType dietType) {
        if (dietType == null) {
            throw new IllegalArgumentException("Diet type cannot be null");
        }
    }

    // TODO: Add validation for calorie target (must be >= 500 if set)
    // TODO: Add method to find profile by user ID (when User relationship added)
    // TODO: Add method to update profile
}
