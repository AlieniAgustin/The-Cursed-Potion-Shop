package com.magic.academy.cursedpotions.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when a requested potion cannot be found in the inventory.
 */
@ResponseStatus(HttpStatus.NOT_FOUND) 
public class PotionNotFoundException extends RuntimeException {
    
    /**
     * Constructs the exception with the ID of the potion that was not found.
     * @param id the ID of the missing potion
     */
    public PotionNotFoundException(Long id) {
        super("Potion with ID " + id + " not found");
    }
}