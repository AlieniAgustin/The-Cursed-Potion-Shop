package com.magic.academy.cursedpotions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.magic.academy.cursedpotions.model.Potion;

/**
 * Repository interface for performing CRUD operations on Potion entities.
*/
@Repository
public interface PotionRepository extends JpaRepository<Potion, Long> {
    
    public boolean existsByName (String name);
}