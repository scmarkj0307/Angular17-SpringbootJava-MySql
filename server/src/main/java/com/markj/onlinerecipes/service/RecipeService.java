package com.markj.onlinerecipes.service;

import com.markj.onlinerecipes.model.Recipe;
import com.markj.onlinerecipes.model.User;

import java.util.List;

public interface RecipeService {

    public  Recipe createRecipe(Recipe recipe, User user); //method to create recipe
    public Recipe findRecipeById(Long id)throws Exception; //method to find recipe by id

    public void deleteRecipe(Long id)throws Exception; //method to delete recipe

    public  Recipe updateRecipe(Recipe recipe, Long id) throws Exception; //method to update recipe

    public List<Recipe>findAllRecipe(); //method to find all recipe

    public Recipe likeRecipe(Long recipeId, User user) throws Exception; //method to like recipe


}
