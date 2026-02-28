package com.magic.academy.cursedpotions.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.magic.academy.cursedpotions.exception.PotionAlreadyExistsException;
import com.magic.academy.cursedpotions.exception.PotionNotFoundException;
import com.magic.academy.cursedpotions.model.Potion;
import com.magic.academy.cursedpotions.repository.PotionRepository;

@ExtendWith(MockitoExtension.class)
public class PotionServiceTest {

    @Mock
    private PotionRepository potionRepository;

    @InjectMocks
    private PotionService potionService;

    @Test
    public void addPotion_WithValidRisk_ShouldSaveAndCalculateLegality () {
        // Arrange 
        Potion potion = new Potion("Test potion", "Test effect", 1);

        when(potionRepository.existsByName("Test potion")).thenReturn(false);

        // Act
        potionService.addPotion(potion);
        
        // Assert 
        assertTrue(potion.isLegal(), "The potion should be legal");

        verify(potionRepository, times(1)).save(potion);
    }

    @Test 
    public void addPotion_WhenNameExists_ShouldThrowException () {
        // Arrange 
        Potion potion = new Potion("Test potion", "Test effect", 100);

        when(potionRepository.existsByName("Test potion")).thenReturn(true);

        // Act y Assert 
        PotionAlreadyExistsException exception = assertThrows(PotionAlreadyExistsException.class, () -> {
            potionService.addPotion(potion);
        });
        assertEquals("A potion with this name already exists", exception.getMessage());

        verify(potionRepository, never()).save(any(Potion.class));        
    }

    @Test 
    public void getInventory_WithTwoPotions_ShouldReturnTheListWithThoseTwoPotions () {
        // Arrange 
        Potion potion1 = new Potion("First potion", "Test effect", 1);
        Potion potion2 = new Potion("Second potion", "Test effect", 100);
        List<Potion> expectedList = Arrays.asList(potion1, potion2);

        when(potionRepository.findAll()).thenReturn(expectedList);

        // Act
        List<Potion> result = potionService.getInventory();

        // Assert
        assertEquals(2, result.size());
        assertTrue(result.contains(potion1));
        assertTrue(result.contains(potion2));

        verify(potionRepository, times(1)).findAll();
    }

    @Test 
    public void findPotionById_WithExistingId_ShouldReturnAPotion () {
        //Arrange 
        Potion expectedPotion = new Potion(1L, "Test potion", "Test effect", 1, true);

        when(potionRepository.findById(1L)).thenReturn(Optional.of(expectedPotion));

        // Act
        Potion currentPotion = potionService.findPotionById(1L);

        // Assert
        assertEquals(expectedPotion, currentPotion);

        verify(potionRepository, times(1)).findById(1L);
    }

    @Test 
    public void findPotionById_WithNonExistentId_ShouldThrowException () {
        // Arrange 
        when(potionRepository.findById(1L)).thenReturn(Optional.empty());

        // Act y Assert 
        PotionNotFoundException exception = assertThrows(PotionNotFoundException.class, () -> {
            potionService.findPotionById(1L);
        });

        assertEquals("Potion with ID 1 not found", exception.getMessage());
    }

    @Test 
    public void removePotionById_WithExistingId_ShouldRemoveAPotion () {
        // Arrange
        when(potionRepository.existsById(1L)).thenReturn(true);

        // Act
        potionService.removePotionById(1L);

        // Assert 
        verify(potionRepository, times(1)).deleteById(1L);
    }

    @Test 
    public void removePotionById_WithNonExistentId_ShouldThrowException () {
        // Arrange
        when(potionRepository.existsById(1L)).thenReturn(false);

        // Act y Assert
        PotionNotFoundException exception = assertThrows(PotionNotFoundException.class, () -> {
            potionService.removePotionById(1L);
        });

        assertEquals("Potion with ID 1 not found", exception.getMessage());
    }
}