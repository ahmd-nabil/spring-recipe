package nobel.spring.recipe.controllers;

import nobel.spring.recipe.domain.Recipe;
import nobel.spring.recipe.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class RecipeControllerTest {

    @Mock
    RecipeService recipeService;

    RecipeController controller;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new RecipeController(recipeService);
    }

    @Test
    void getRecipeById() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(2L);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        when(recipeService.findById(anyLong())).thenReturn(recipe);
        mockMvc.perform(MockMvcRequestBuilders.get("/recipes/2"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/show"));

    }
}