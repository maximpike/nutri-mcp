package com.maximpike.nutrimc.repository;

import com.maximpike.nutrimc.entity.User;
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
    void testSaveUserAndGenerateId() {
        // given
        User user = new User("test@example.com");
        user.setName("Test User");

        // when
        User savedUser = userRepository.save(user);

        // then
        assertThat(savedUser.getId()).isNotNull();
        assertThat(savedUser.getCreatedAt()).isNotNull();
        assertThat(savedUser.getEmail()).isEqualTo("test@example.com");
        assertThat(savedUser.getName()).isEqualTo("Test User");
    }

    @Test
    void testFindUserById() {
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

    // TODO: Add test for findByEmail when we implement that custom query
    // TODO: Add test for user not found (empty Optional)

}
