package nobel.spring.recipe.controllers;

import nobel.spring.recipe.domain.Category;
import nobel.spring.recipe.domain.Recipe;
import nobel.spring.recipe.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class IndexControllerTest {

    IndexController indexController;

    @Mock
    RecipeService recipeService;

    @Mock
    Model model;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        indexController = new IndexController(recipeService);
    }

    @Test
    void testMockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));

    }

    @Test
    void getIndexPage() {
        Set<Recipe> recipes = new HashSet<>();
        recipes.add(new Recipe());
        Recipe recipe2 = new Recipe();
        recipe2.setId(1l);
        recipes.add(recipe2);

        when(recipeService.findAll()).thenReturn(recipes);

        assertEquals("index", indexController.getIndexPage(model));

        Mockito.verify(recipeService, Mockito.times(1)).findAll();

        ArgumentCaptor<Set> argumentCaptor = ArgumentCaptor.forClass(Set.class); // this is just a matcher object (no actual data in it)

        // Making sure that model.addAttribute() was called once
        // and first argument was recipes, and second arg was of type Set (that is whats in ArgumentCaptor)
        // why not just use Set.class (Mockito.verify only works with Mocks and Arguments)
        Mockito.verify(model, Mockito.times(1)).addAttribute(Mockito.eq("recipes"), argumentCaptor.capture());

        Set<Category> setInController = argumentCaptor.getValue();

        assertEquals(2, setInController.size());
    }
}