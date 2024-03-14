package com.markj.onlinerecipes.controller;

import com.markj.onlinerecipes.model.Recipe;
import com.markj.onlinerecipes.model.User;
import com.markj.onlinerecipes.repository.UserRepository;
import com.markj.onlinerecipes.service.RecipeService;
import com.markj.onlinerecipes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/recipe/user/{userId}")
    public Recipe createRecipe(@RequestBody Recipe recipe, @PathVariable Long userId) throws Exception{
        User user=userService.findUserById(userId);
        Recipe createdRecipe=recipeService.createRecipe(recipe,user);
        return createdRecipe;
    }

    @PutMapping("/api/recipe/{id}")
    public Recipe updateRecipe(@RequestBody Recipe recipe, @PathVariable Long id) throws Exception{

        Recipe updatedRecipe=recipeService.updateRecipe(recipe,id);
        return updatedRecipe;
    }


    @GetMapping("/api/recipe")
    public List<Recipe> getAllRecipe() throws Exception{

        List<Recipe> recipes=recipeService.findAllRecipe();
        return recipes;
    }


    @DeleteMapping("/api/recipe/{recipeId}")
    public String deleteRecipe(@PathVariable Long recipeId) throws Exception{

        recipeService.deleteRecipe(recipeId);
        return "recipe deleted successfully";
    }


    @PutMapping("/api/recipe/{id}/user/{userId}")
    public Recipe likeRecipe(@PathVariable Long userId, @PathVariable Long id) throws Exception{
        User user=userService.findUserById(userId);
        Recipe updatedRecipe=recipeService.likeRecipe(id, user);
        return updatedRecipe;
    }







}