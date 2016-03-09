package com.debes.plateplanner.dao.recipe.repository;

import com.debes.plateplanner.dao.recipe.RecipeCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lesley.debes
 */
@Repository
public interface RecipeCategoryRepository extends JpaRepository<RecipeCategory, Integer> {
    List<RecipeCategory> findAllByOrderByRecipeCategory();
    List<RecipeCategory> findAllByOrderByOrderSequence();
}
