package com.maximpike.nutrimc.repository;

import com.maximpike.nutrimc.entity.DietaryProfile;
import com.maximpike.nutrimc.entity.User;
import com.maximpike.nutrimc.entity.enums.DietType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldSaveUser() {
        // given
        User user = new User("test@example.com");
        user.setName("Test User");

        // when
        User savedUser = userRepository.save(user);

        // then
        assertThat(savedUser.getId()).isNotNull();
        assertThat(savedUser.getCreatedAt()).isNotNull();
        assertThat(savedUser.getUpdatedAt()).isNotNull();
        assertThat(savedUser.getEmail()).isEqualTo("test@example.com");
        assertThat(savedUser.getName()).isEqualTo("Test User");
    }

    @Test
    void shouldFindUserByEmail() {
        // given
        User user = new User("testfinduser@example.com");
        user.setName("Findable User");
        User savedUser = userRepository.save(user);

        // when
        Optional<User> result = userRepository.findById(savedUser.getId());

        // then
        assertThat(result).isPresent();

        User foundUser = result.get();
        assertThat(foundUser.getId()).isEqualTo(savedUser.getId());
        assertThat(foundUser.getEmail()).isEqualTo("testfinduser@example.com");
        assertThat(foundUser.getName()).isEqualTo("Findable User");
    }

    @Test
    void shouldSaveUserWithDietaryProfile() {
        // given
        User user = new User("testuser@user.com");
        DietaryProfile profile = new DietaryProfile(DietType.VEGETARIAN);

        // Set relationship (helper method maintains both sides)
        user.setDietaryProfile(profile);

        // when
        User savedUser = userRepository.save(user);

        // then

        assertThat(savedUser.getId()).isNotNull();
        assertThat(savedUser.getDietaryProfile()).isNotNull();
        assertThat(savedUser.getDietaryProfile().getId()).isNotNull();
        assertThat(savedUser.getDietaryProfile().getDietType()).isEqualTo(DietType.VEGETARIAN);
        assertThat(savedUser.getDietaryProfile().getUser()).isEqualTo(savedUser);
    }
    // TODO: Add test for user not found (empty Optional)
}
