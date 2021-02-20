package nobel.spring.recipe.controllers;

import nobel.spring.recipe.commands.RecipeCommand;
import nobel.spring.recipe.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/recipes")
public class RecipeController {
    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/")
    public String getRecipes(Model model) {
        model.addAttribute("recipes", recipeService.findAll());
        return "index";
    }
    @GetMapping("/{id}")
    public String getRecipeById(@PathVariable Long id, Model model) {
        model.addAttribute("recipe", recipeService.findById(id));
        return "recipe/show";
    }

    @GetMapping("/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/recipe-form";
    }

    @GetMapping("/update/{id}")
    public String updateRecipe(@PathVariable Long id, Model model) {
        RecipeCommand recipeCommand = recipeService.findRecipeCommandById(id);
        model.addAttribute("recipe", recipeCommand);
        return "recipe/recipe-form";
    }

    @PostMapping("/saveOrUpdate")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
        return "redirect:/recipes/"+savedCommand.getId();
    }
}
