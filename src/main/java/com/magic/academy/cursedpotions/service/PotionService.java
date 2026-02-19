package com.magic.academy.cursedpotions.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.magic.academy.cursedpotions.exception.InvalidRiskException;
import com.magic.academy.cursedpotions.exception.PotionAlreadyExistsException;
import com.magic.academy.cursedpotions.exception.PotionNotFoundException;
import com.magic.academy.cursedpotions.model.Potion;

/**
 * Service for managing potions inventory and operations.
 */
@Service
public class PotionService {

    /** Counter for generating unique potion IDs. */
    private Long nextId;
    /** Storage for potions indexed by their ID. */
    private Map<Long,Potion> inventory;

    /**
     * Initializes the service with an empty inventory and starting ID.
     */
    public PotionService () {
        inventory = new HashMap<>();
        nextId = 1L;
    }

    /**
     * Adds a new potion to the inventory after validation and risk audit.
     * @param potion the potion to add
     * @throws PotionAlreadyExistsException if name already exists
     * @throws InvalidRiskException if risk level is invalid
     */
    public void addPotion (Potion potion) {
        validatePotion(potion);
        assignId(potion);
        auditRisk(potion);
        Long id = potion.getId();
        inventory.put(id,potion);
    }

    /**
     * Retrieves all potions from the inventory.
     * @return a list of all potions
     */
    public List<Potion> getInventory () {
        return new ArrayList<>(inventory.values());
    }

    /**
     * Finds a potion by its ID.
     * @param id the potion ID
     * @return the potion with the given ID
     * @throws PotionNotFoundException if potion not found
     */
    public Potion findPotionById (Long id) {
        ensurePotionExists(id);

        return inventory.get(id);
    }

    /**
     * Removes a potion from the inventory by ID.
     * @param id the potion ID
     * @throws PotionNotFoundException if potion not found
     */
    public void removePotionById (Long id) {
        ensurePotionExists(id);

        inventory.remove(id);
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

        String name = potion.getName();
        if (existsByName(name)) 
            throw new PotionAlreadyExistsException();
    }

    /**
     * Checks if a potion with the given name exists in inventory.
     * @param name the potion name
     * @return true if exists, false otherwise
     */
    private boolean existsByName (String name) {
        for (Potion potion : inventory.values()) 
            if (potion.getName().equals(name))
                return true;

        return false;
    }

    /**
     * Assigns a unique ID to the potion and increments the counter.
     * @param potion the potion to assign ID to
     */
    private void assignId (Potion potion) {
        potion.setId(nextId);
        nextId++;
    }

    /**
     * Determines legality based on risk level (legal if risk <= 90).
     * @param potion the potion to audit
     */
    private void auditRisk (Potion potion) {
        int levelOfRisk = potion.getLevelOfRisk();
        potion.setLegal(levelOfRisk <= 90);
    }

    /**
     * Verifies that a potion exists in inventory.
     * @param id the potion ID
     * @throws PotionNotFoundException if potion does not exist
     */
    private void ensurePotionExists (Long id) {
        if (!inventory.containsKey(id))
            throw new PotionNotFoundException(id);   
    }
}