package nobel.spring.recipe.services;

import nobel.spring.recipe.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> findAll();
    Recipe findById(Long id);
}
