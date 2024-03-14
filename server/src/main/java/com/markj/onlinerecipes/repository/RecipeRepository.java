package com.markj.onlinerecipes.repository;

import com.markj.onlinerecipes.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository  extends JpaRepository <Recipe, Long>{
}
