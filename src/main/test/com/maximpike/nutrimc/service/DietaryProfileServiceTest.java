package com.maximpike.nutrimc.service;

import com.maximpike.nutrimc.entity.DietaryProfile;
import com.maximpike.nutrimc.entity.enums.DietType;
import com.maximpike.nutrimc.repository.DietaryProfileRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DietaryProfileServiceTest {

    @Mock
    private DietaryProfileRepository dietaryProfileRepository;

    @InjectMocks
    private DietaryProfileService dietaryProfileService ;

    @Test
    void shouldCreateProfileWithValidDietType() {
        // given
        DietType OmnivoreDiet = DietType.OMNIVORE;
        int calorieTarget = 2000;

        DietaryProfile savedProfile = new DietaryProfile(DietType.OMNIVORE);
        savedProfile.setId(1L);
        savedProfile.setCalorieTarget(2000);
        when(dietaryProfileRepository.save(any(DietaryProfile.class)))
                .thenReturn(savedProfile);

        // when
        DietaryProfile createdProfile = dietaryProfileService.createProfile(OmnivoreDiet, calorieTarget);

        // then
        assertThat(createdProfile).isNotNull();
        assertThat(createdProfile.getId()).isEqualTo(1L);
        assertThat(createdProfile.getDietType()).isEqualTo(DietType.OMNIVORE);
        assertThat(createdProfile.getCalorieTarget()).isEqualTo(2000);
        verify(dietaryProfileRepository).save(any(DietaryProfile.class));
    }

    @Test
    void shouldThrowExceptionWhenDietTypeIsNull() {
        // when
        assertThatThrownBy(() -> dietaryProfileService.createProfile(null, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Diet type cannot be null");
        // then
        verify(dietaryProfileRepository, never()).save(any());
    }

    // TODO: create test that creates profiles for all diet types and validates
}