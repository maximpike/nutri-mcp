package com.maximpike.nutrimc.repository;

import com.maximpike.nutrimc.entity.DietaryProfile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class DietaryProfileRepositoryTest {

    @Autowired
    private DietaryProfileRepository dietaryProfileRepository;

    @Test
    void testSaveDietaryProfile() {
        // given
        DietaryProfile dietaryProfile = new DietaryProfile("Test Diet");
        dietaryProfile.setCalorieTarget(1000);

        // when
        DietaryProfile savedDietaryProfile = dietaryProfileRepository.save(dietaryProfile);

        // then
        assertThat(savedDietaryProfile.getId()).isNotNull();
        assertThat(savedDietaryProfile.getCreatedAt()).isNotNull();
        assertThat(savedDietaryProfile.getDietType()).isEqualTo("Test Diet");
        assertThat(savedDietaryProfile.getCalorieTarget()).isEqualTo(1000);
    }
}
