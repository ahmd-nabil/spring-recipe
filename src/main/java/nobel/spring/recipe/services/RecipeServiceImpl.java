package nobel.spring.recipe.services;

import lombok.extern.slf4j.Slf4j;
import nobel.spring.recipe.domain.Recipe;
import nobel.spring.recipe.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService{

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> findAll() {
        log.debug("In Recipe Service");
        Set<Recipe> recipes = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
        return recipes;
    }

    @Override
    public Recipe findById(Long id) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);
        if(!recipeOptional.isPresent())
            throw new RuntimeException("Recipe Not Found");
        return recipeOptional.get();
    }
}
