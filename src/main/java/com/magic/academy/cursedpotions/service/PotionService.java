package com.magic.academy.cursedpotions.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.magic.academy.cursedpotions.exception.InvalidRiskException;
import com.magic.academy.cursedpotions.exception.PotionAlreadyExistsException;
import com.magic.academy.cursedpotions.exception.PotionNotFoundException;
import com.magic.academy.cursedpotions.model.Potion;
import com.magic.academy.cursedpotions.repository.PotionRepository;

/**
 * Service for managing potions inventory and operations.
 */
@Service
public class PotionService {

    /**
     * Repository used to persist and query potions.
     */
    private final PotionRepository potionRepository;

    /**
     * Constructs a new PotionService with the given repository.
     * @param potionRepository repository used for potion persistence and queries
     */
    public PotionService (PotionRepository potionRepository) {
        this.potionRepository = potionRepository;
    }

    /**
     * Adds a new potion to the inventory after validation and risk audit.
     * @param potion the potion to add
     * @throws PotionAlreadyExistsException if name already exists
     * @throws InvalidRiskException if risk level is invalid
     */
    public void addPotion (Potion potion) {
        validatePotion(potion);
        auditRisk(potion);

        potionRepository.save(potion);
    }

    /**
     * Retrieves all potions from the inventory.
     * @return a list of all potions
     */
    public List<Potion> getInventory () {
        return potionRepository.findAll();
    }

    /**
     * Finds a potion by its ID.
     * @param id the potion ID
     * @return the potion with the given ID
     * @throws PotionNotFoundException if potion not found
     */
    public Potion findPotionById (Long id) {
        return potionRepository.findById(id)
            .orElseThrow(() -> new PotionNotFoundException(id));
    }

    /**
     * Removes a potion from the inventory by ID.
     * @param id the potion ID
     * @throws PotionNotFoundException if potion not found
     */
    public void removePotionById (Long id) {
        if (!potionRepository.existsById(id))
            throw new PotionNotFoundException(id);
    
        potionRepository.deleteById(id); 
    }

    /**
     * Validates potion risk level and name uniqueness.
     * @param potion the potion to validate
     * @throws InvalidRiskException if risk level outside 1-100 range
     * @throws PotionAlreadyExistsException if name already exists
     */
    private void validatePotion (Potion potion) {
        int levelOfRisk = potion.getLevelOfRisk();
        if (levelOfRisk < 1 || levelOfRisk > 100)
            throw new InvalidRiskException();

        if (potionRepository.existsByName(potion.getName())) {
            throw new PotionAlreadyExistsException();
        }
    }

    /**
     * Determines legality based on risk level (legal if risk <= 90).
     * @param potion the potion to audit
     */
    private void auditRisk (Potion potion) {
        int levelOfRisk = potion.getLevelOfRisk();
        potion.setLegal(levelOfRisk <= 90);
    }
}