package com.debes.plateplanner.dao.recipe.repository;

import com.debes.plateplanner.dao.recipe.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lesley.debes
 */
@Repository
public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Integer> {
    List<RecipeIngredient> findByIdRecipe(Integer idRecipe);
    RecipeIngredient findByIdRecipeAndIdRecipeIngredient(Integer idRecipe, Integer idIngredient);
    void deleteByIdRecipe(Integer idRecipe);
    void deleteByIdRecipeAndIdRecipeIngredient(Integer idRecipe, Integer idIngredient);
}
