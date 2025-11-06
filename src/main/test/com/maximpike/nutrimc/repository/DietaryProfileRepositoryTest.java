package com.maximpike.nutrimc.repository;

import com.maximpike.nutrimc.entity.DietaryProfile;
import com.maximpike.nutrimc.entity.User;
import com.maximpike.nutrimc.entity.enums.DietType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class DietaryProfileRepositoryTest {

    @Autowired
    private DietaryProfileRepository dietaryProfileRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldSaveDietaryProfile() {
        // given
        User user = new User("testuser@dietaryprofile.com");
        userRepository.save(user);

        DietaryProfile dietaryProfile = new DietaryProfile(DietType.OMNIVORE);
        dietaryProfile.setCalorieTarget(1000);
        dietaryProfile.setUser(user);

        // when
        DietaryProfile savedDietaryProfile = dietaryProfileRepository.save(dietaryProfile);

        // then
        assertThat(savedDietaryProfile.getId()).isNotNull();
        assertThat(savedDietaryProfile.getCreatedAt()).isNotNull();
        assertThat(savedDietaryProfile.getUpdatedAt()).isNotNull();
        assertThat(savedDietaryProfile.getDietType()).isEqualTo(DietType.OMNIVORE);
        assertThat(savedDietaryProfile.getCalorieTarget()).isEqualTo(1000);
        assertThat(savedDietaryProfile.getUser()).isNotNull();
        assertThat(savedDietaryProfile.getUser().getEmail()).isEqualTo("testuser@dietaryprofile.com");
    }
}
