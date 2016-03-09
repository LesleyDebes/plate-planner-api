package com.debes.plateplanner.dao.recipe.repository;

import com.debes.plateplanner.dao.recipe.RecipeHasCategory;
import com.debes.plateplanner.dao.recipe.RecipeHasCategoryPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lesley.debes
 */
@Repository
public interface RecipeHasCategoryRepository extends JpaRepository<RecipeHasCategory, RecipeHasCategoryPK> {

}
