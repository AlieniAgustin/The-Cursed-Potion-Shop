package com.magic.academy.cursedpotions.controller;

import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.magic.academy.cursedpotions.exception.PotionAlreadyExistsException;
import com.magic.academy.cursedpotions.exception.PotionNotFoundException;
import com.magic.academy.cursedpotions.model.Potion;
import com.magic.academy.cursedpotions.service.PotionService;

@SpringBootTest
@AutoConfigureMockMvc
public class PotionControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PotionService potionService;

    @Test
    public void viewCatalog_ShouldReturnCatalogViewAndInventoryModel () throws Exception {
        mockMvc.perform(get("/catalog"))

            .andExpect(status().isOk())
            .andExpect(view().name("catalog"))
            .andExpect(model().attributeExists("inventory"));
    }
    
    @Test 
    public void showCreationForm_ShouldReturnCreateViewAndPotionModel () throws Exception {
        mockMvc.perform(get("/create"))

            .andExpect(status().isOk())
            .andExpect(view().name("create"))
            .andExpect(model().attributeExists("potion"));
    }

    @Test 
    public void createPotion_WithValidData_ShouldSaveAndRedirect () throws Exception {
        mockMvc.perform(post("/create")
            .param("name", "Potion Test")
            .param("effect", "Test Effect")
            .param("levelOfRisk", "40"))

            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/catalog"));
    }

    @Test 
    public void createPotion_WithInvalidRisk_ShouldReturnCreateViewWithErrors () throws Exception {
        mockMvc.perform(post("/create")
            .param("name", "Potion Test")
            .param("effect", "Test Effect")
            .param("levelOfRisk", "101"))

            .andExpect(status().isOk())
            .andExpect(view().name("create"))
            .andExpect(model().hasErrors())
            .andExpect(model().attributeHasFieldErrors("potion", "levelOfRisk"));
    }

    
    @Test 
    public void createPotion_WithExistentName_ShouldReturnCreateViewAndErrorModel () throws Exception {
        doThrow(new PotionAlreadyExistsException())
            .when(potionService).addPotion(any(Potion.class));

        mockMvc.perform(post("/create")
            .param("name", "Potion Test")
            .param("effect", "Test Effect")
            .param("levelOfRisk", "1"))

            .andExpect(status().isOk())
            .andExpect(view().name("create"))
            .andExpect(model().attributeExists("errorMessage"))
            .andExpect(model().attribute("errorMessage", "A potion with this name already exists"));
    }

    @Test 
    public void detelePotion_WithExistentId_ShouldRedirectWithSuccessMessage () throws Exception {
        mockMvc.perform(get("/delete").param("id","1"))
    
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/catalog"))

            .andExpect(flash().attributeExists("successMessage"))
            .andExpect(flash().attribute("successMessage", "Potion successfully removed."));
    }

    @Test 
    public void deletePotion_WithNonExistenId_ShouldRedirectWithErrorMessage () throws Exception {
        doThrow(new PotionNotFoundException(1L))
            .when(potionService).removePotionById(1L);

        mockMvc.perform(get("/delete").param("id","1"))

            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/catalog"))

            .andExpect(flash().attributeExists("errorMessage"))
            .andExpect(flash().attribute("errorMessage", "Potion with ID 1 not found"));
    }

    @Test 
    public void viewPotion_WithExistentId_ShouldReturnDetailsViewAndPotionModel () throws Exception {
        Potion potion = new Potion(1L, "Potion Test", "Test Effect", 1, true);
        when(potionService.findPotionById(1L)).thenReturn(potion);
       
        mockMvc.perform(get("/details").param("id","1"))
        
            .andExpect(status().isOk())
            .andExpect(view().name("details"))
            .andExpect(model().attributeExists("potion"));
    }

    @Test 
    public void viewPotion_WithNonExistentId_ShouldRedirectWithErrorMessage () throws Exception {
        doThrow(new PotionNotFoundException(1L))
            .when(potionService).findPotionById(1L);

        mockMvc.perform(get("/details").param("id","1"))
            
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/catalog"))

            .andExpect(flash().attributeExists("errorMessage"))
            .andExpect(flash().attribute("errorMessage", "Potion with ID 1 not found"));
    }
}