package nobel.spring.recipe.services;

import nobel.spring.recipe.commands.RecipeCommand;
import nobel.spring.recipe.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> findAll();
    Recipe findById(Long id);

    void deleteById(Long id);

    RecipeCommand findRecipeCommandById(Long id);
    RecipeCommand saveRecipeCommand(RecipeCommand command);
}
