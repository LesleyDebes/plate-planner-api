package com.debes.plateplanner.dao.recipe.repository;

import com.debes.plateplanner.dao.recipe.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lesley.debes
 */
@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

}
