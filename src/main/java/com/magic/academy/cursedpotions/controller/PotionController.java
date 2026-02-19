package com.magic.academy.cursedpotions.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.magic.academy.cursedpotions.exception.InvalidRiskException;
import com.magic.academy.cursedpotions.exception.PotionAlreadyExistsException;
import com.magic.academy.cursedpotions.exception.PotionNotFoundException;
import com.magic.academy.cursedpotions.model.Potion;
import com.magic.academy.cursedpotions.service.PotionService;


/**
 * Controller for handling HTTP requests related to potion management.
 * Provides endpoints for viewing, creating, updating, and deleting potions.
 */
@Controller
public class PotionController {

    /** Service for managing potion operations. */
    private final PotionService potionService;

    /**
     * Constructs the controller with the required service dependency.
     * @param potionService the potion service to use
     */
    public PotionController (PotionService potionService) {
        this.potionService = potionService;
    }

    /**
     * Displays the potion catalog with all potions in inventory.
     * @param model the model to add inventory data
     * @return the catalog view page
     */
    @GetMapping ("/catalog")
    public String viewCatalog (Model model) {
        model.addAttribute("inventory", potionService.getInventory());

        return "catalog";
    }

    /**
     * Displays the form for creating a new potion.
     * @param model the model to add a new empty potion
     * @return the creation form view page
     */
    @GetMapping ("/create")
    public String showCreationForm (Model model) {
        model.addAttribute("potion", new Potion());

        return "create";
    }

    /**
     * Handles potion creation. Validates and saves the potion to inventory.
     * @param potion the potion data from the form
     * @param model the model for error messages
     * @return redirect to catalog on success, or return to form on error
     */
    @PostMapping ("/create")
    public String createPotion (@ModelAttribute Potion potion, Model model) {
        try {
            potionService.addPotion(potion);
            
            return "redirect:/catalog";            
        } catch (InvalidRiskException | PotionAlreadyExistsException e) {
            model.addAttribute("errorMessage", e.getMessage());

            return "create";
        }
    }

    /**
     * Deletes a potion from inventory by ID.
     * @param id the ID of the potion to delete
     * @param redirectAttributes attributes for redirect messages
     * @return redirect to catalog with success or error message
     */
    @GetMapping ("/delete")
    public String deletePotion (@RequestParam Long id, RedirectAttributes redirectAttributes) {
        try {
            potionService.removePotionById(id);
            
            redirectAttributes.addFlashAttribute("successMessage", "Potion successfully removed.");
            
        } catch (PotionNotFoundException e) {
            
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        
        return "redirect:/catalog";
    }

    /**
     * Displays detailed information about a specific potion.
     * @param id the ID of the potion to view
     * @param model the model to add potion details
     * @param redirectAttributes attributes for redirect messages
     * @return the details view page, or redirect to catalog if not found
     */
    @GetMapping ("/details")
    public String viewPotion (@RequestParam Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Potion potion = potionService.findPotionById(id);
            
            model.addAttribute("potion", potion);
            return "details"; 
            
        } catch (PotionNotFoundException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/catalog";
        }
    }

}