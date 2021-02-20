package nobel.spring.recipe.controllers;

import nobel.spring.recipe.commands.RecipeCommand;
import nobel.spring.recipe.domain.Recipe;
import nobel.spring.recipe.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class RecipeControllerTest {

    @Mock
    RecipeService recipeService;

    RecipeController controller;
    MockMvc mockMvc;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new RecipeController(recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void getRecipeById() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(2L);

        when(recipeService.findById(anyLong())).thenReturn(recipe);
        mockMvc.perform(MockMvcRequestBuilders.get("/recipes/2"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/show"));

    }

    @Test
    public void newRecipeTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/recipes/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipe-form"));
    }

    @Test
    public void saveOrUpdateTest() throws Exception{
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(5L);

        when(recipeService.saveRecipeCommand(any())).thenReturn(recipeCommand);

        mockMvc.perform(MockMvcRequestBuilders.post("/recipes/saveOrUpdate")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("id", "")
                        .param("description", "some description")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipes/5"));
    }

   @Test
    public void deleteRecipeTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/recipes/delete/5"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipes/"));

        verify(recipeService, times(1)).deleteById(5L);
   }
}