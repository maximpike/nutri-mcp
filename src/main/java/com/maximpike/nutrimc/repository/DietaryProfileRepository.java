package com.maximpike.nutrimc.repository;

import com.maximpike.nutrimc.entity.DietaryProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DietaryProfileRepository extends JpaRepository<DietaryProfile, Long> {

    // Spring automatically provides CRUD operations

    // TODO: Add custom queries when we add User relationship
    // Example: Optional<DietaryProfile> findByUserId(Long userId);

}
