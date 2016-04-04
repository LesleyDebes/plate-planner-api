package com.debes.plateplanner.core;

import com.debes.plateplanner.dao.recipe.Recipe;
import com.debes.plateplanner.dao.recipe.RecipeIngredient;
import com.debes.plateplanner.dao.recipe.repository.RecipeIngredientRepository;
import com.debes.plateplanner.dao.recipe.repository.RecipeRepository;
import com.debes.plateplanner.models.BaseModel;
import com.debes.plateplanner.models.enums.MeasurementEnum;
import com.debes.plateplanner.models.enums.ModelStatusEnum;
import com.debes.plateplanner.models.recipe.RecipeIngredientListModel;
import com.debes.plateplanner.models.recipe.RecipeIngredientModel;
import com.debes.plateplanner.models.recipe.RecipeListModel;
import com.debes.plateplanner.models.recipe.RecipeModel;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lesley.debes
 */
@Service
public class RecipeService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private RecipeIngredientRepository recipeIngredientRepository;

    @Transactional
    public RecipeModel upsertRecipe(RecipeModel recipeModel) {
        try {
            Recipe recipe;
            if (recipeModel.getIdRecipe() == null) {
                // Create a new recipe record
                recipe = new Recipe();
                recipe.setCreateTimestamp(Timestamp.valueOf(LocalDateTime.now()));
            } else {
                // Use the existing recipe record
                recipe = recipeRepository.findOne(recipeModel.getIdRecipe());
                recipe.setUpdateTimestamp(Timestamp.valueOf(LocalDateTime.now()));
            }
            recipe.setRecipeName(recipeModel.getRecipeName());
            recipe.setRecipeSource(recipeModel.getRecipeSource());
            recipe = recipeRepository.save(recipe);
            if (recipe != null && recipe.getIdRecipe() != null) {
                recipeModel.setIdRecipe(recipe.getIdRecipe());
                recipeModel.setModelStatusEnum(ModelStatusEnum.SUCCESS);
            }
        } catch (Exception e) {
            logger.error("There was an error upserting the recipe: ", e);
            recipeModel.setIdRecipe(null);
            recipeModel.setModelStatusEnum(ModelStatusEnum.ERROR);
            recipeModel.setMessage("There was an error saving the recipe.");
        }
        return recipeModel;
    }

    public RecipeModel getRecipe(Integer idRecipe) {
        RecipeModel recipeModel = new RecipeModel();
        try {
            Recipe recipe = recipeRepository.findOne(idRecipe);
            recipeModel.setIdRecipe(recipe.getIdRecipe());
            recipeModel.setRecipeName(recipe.getRecipeName());
            recipeModel.setRecipeSource(recipe.getRecipeSource());
            recipeModel.setCreateTimestamp(recipe.getCreateTimestamp());
            recipeModel.setUpdateTimestamp(recipe.getUpdateTimestamp());
            recipeModel.setModelStatusEnum(ModelStatusEnum.SUCCESS);
        } catch (Exception e) {
            logger.error("There was an error retrieving the recipe: ", e);
            recipeModel.setModelStatusEnum(ModelStatusEnum.ERROR);
            recipeModel.setMessage("There was an error retrieving the recipe.");
        }
        return recipeModel;
    }

    public RecipeListModel getRecipeList() {
        RecipeListModel recipeListModel = new RecipeListModel();
        try {
            List<Recipe> recipeList = recipeRepository.findAll();
            if (CollectionUtils.isNotEmpty(recipeList)) {
                List<RecipeModel> recipeModelList = new ArrayList<>();
                for (Recipe recipe : recipeList) {
                    RecipeModel recipeModel = new RecipeModel();
                    recipeModel.setIdRecipe(recipe.getIdRecipe());
                    recipeModel.setRecipeName(recipe.getRecipeName());
                    recipeModel.setRecipeSource(recipe.getRecipeSource());
                    recipeModel.setCreateTimestamp(recipe.getCreateTimestamp());
                    recipeModel.setUpdateTimestamp(recipe.getUpdateTimestamp());
                    recipeModel.setModelStatusEnum(ModelStatusEnum.SUCCESS);
                    recipeModelList.add(recipeModel);
                }
                recipeListModel.setRecipeModelList(recipeModelList);
                recipeListModel.setModelStatusEnum(ModelStatusEnum.SUCCESS);
            } else {
                logger.error("No recipes found.");
                recipeListModel.setModelStatusEnum(ModelStatusEnum.ERROR);
                recipeListModel.setMessage("No recipes found.");
            }
        } catch (Exception e) {
            logger.error("There was an error retrieving the recipe list: ", e);
            recipeListModel.setModelStatusEnum(ModelStatusEnum.ERROR);
            recipeListModel.setMessage("There was an error retrieving the recipes.");
        }
        return recipeListModel;
    }

    @Transactional
    public BaseModel removeRecipe(Integer idRecipe) {
        BaseModel baseModel = new BaseModel();
        //TODO:  Cascade delete
        try {
            BaseModel ingredientRemoveBaseModel = this.removeAllIngredients(idRecipe);
            if (ingredientRemoveBaseModel.getModelStatusEnum().isSuccessful()) {
                recipeRepository.delete(idRecipe);
                baseModel.setModelStatusEnum(ModelStatusEnum.SUCCESS);
            }
        } catch (Exception e) {
            logger.error("There was an error removing the recipe {}:", idRecipe, e);
            baseModel.setModelStatusEnum(ModelStatusEnum.ERROR);
            baseModel.setMessage("There was an error removing the recipe.");
        }
        return baseModel;
    }

    @Transactional
    public RecipeIngredientModel addIngredient(RecipeIngredientModel ingredientModel) {
        try {
            RecipeIngredient recipeIngredient = new RecipeIngredient();
            recipeIngredient.setIdRecipe(ingredientModel.getIdRecipe());
            recipeIngredient.setIdMeasurement(ingredientModel.getMeasurement().getMeasurementValue());
            recipeIngredient.setIngredientMeasurementAmount(ingredientModel.getIngredientMeasurementAmount());
            recipeIngredient.setIngredientName(ingredientModel.getIngredientName());
            recipeIngredient.setOrderSequence(ingredientModel.getOrderSequence());
            recipeIngredient = recipeIngredientRepository.save(recipeIngredient);
            ingredientModel.setIdRecipeIngredient(recipeIngredient.getIdRecipeIngredient());
            ingredientModel.setModelStatusEnum(ModelStatusEnum.SUCCESS);
        } catch (Exception e) {
            logger.error("There was an error adding the ingredient to the recipe {}:", ingredientModel.getIdRecipe(), e);
            ingredientModel.setModelStatusEnum(ModelStatusEnum.ERROR);
            ingredientModel.setMessage("There was an error adding the ingredient.");
        }
        return ingredientModel;
    }

    @Transactional
    public RecipeIngredientModel updateIngredient(RecipeIngredientModel ingredientModel) {
        try {
            RecipeIngredient recipeIngredient =
                    recipeIngredientRepository.findByIdRecipeAndIdRecipeIngredient(
                    ingredientModel.getIdRecipe(), ingredientModel.getIdRecipeIngredient());
            recipeIngredient.setIdMeasurement(ingredientModel.getMeasurement().getMeasurementValue());
            recipeIngredient.setIngredientMeasurementAmount(ingredientModel.getIngredientMeasurementAmount());
            recipeIngredient.setIngredientName(ingredientModel.getIngredientName());
            recipeIngredient.setOrderSequence(ingredientModel.getOrderSequence());
            recipeIngredientRepository.save(recipeIngredient);
            ingredientModel.setModelStatusEnum(ModelStatusEnum.SUCCESS);
        } catch (Exception e) {
            logger.error("There was an error updating the ingredient: ", e);
            ingredientModel.setModelStatusEnum(ModelStatusEnum.ERROR);
            ingredientModel.setMessage("There was an error updating the ingredient.");
        }
        return ingredientModel;
    }

    @Transactional
    public BaseModel removeIngredient(Integer idRecipe, Integer idIngredient) {
        BaseModel baseModel = new BaseModel();
        try {
            recipeIngredientRepository.deleteByIdRecipeAndIdRecipeIngredient(idRecipe, idIngredient);
            baseModel.setModelStatusEnum(ModelStatusEnum.SUCCESS);
        } catch (Exception e) {
            logger.error("There was an error removing the ingredient: ", e);
            baseModel.setModelStatusEnum(ModelStatusEnum.ERROR);
            baseModel.setMessage("There was an error removing the ingredient.");
        }
        return baseModel;
    }

    @Transactional
    public BaseModel removeAllIngredients(Integer idRecipe) {
        BaseModel baseModel = new BaseModel();
        try {
            recipeIngredientRepository.deleteByIdRecipe(idRecipe);
            baseModel.setModelStatusEnum(ModelStatusEnum.SUCCESS);
        } catch (Exception e) {
            logger.error("There was an error removing all the ingredients for the recipe {}:", idRecipe, e);
            baseModel.setModelStatusEnum(ModelStatusEnum.ERROR);
            baseModel.setMessage("There was an error removing all ingredients for the recipe.");
        }
        return baseModel;
    }

    public RecipeIngredientListModel getRecipeIngredientList(Integer idRecipe) {
        RecipeIngredientListModel recipeIngredientListModel = new RecipeIngredientListModel();
        try {
            List<RecipeIngredient> recipeIngredientList = recipeIngredientRepository.findByIdRecipe(idRecipe);
            if (CollectionUtils.isNotEmpty(recipeIngredientList)) {
                List<RecipeIngredientModel> ingredientModelList = new ArrayList<>();
                for (RecipeIngredient ingredient : recipeIngredientList) {
                    RecipeIngredientModel ingredientModel = new RecipeIngredientModel();
                    ingredientModel.setIdRecipeIngredient(ingredient.getIdRecipeIngredient());
                    ingredientModel.setIngredientName(ingredient.getIngredientName());
                    ingredientModel.setMeasurement(MeasurementEnum.get(ingredient.getIdMeasurement()));
                    ingredientModel.setIngredientMeasurementAmount(ingredient.getIngredientMeasurementAmount());
                    ingredientModel.setOrderSequence(ingredient.getOrderSequence());
                    ingredientModel.setIdRecipe(ingredient.getIdRecipe());
                    ingredientModel.setModelStatusEnum(ModelStatusEnum.SUCCESS);
                    ingredientModelList.add(ingredientModel);
                }
                recipeIngredientListModel.setIngredientModelList(ingredientModelList);
                recipeIngredientListModel.setModelStatusEnum(ModelStatusEnum.SUCCESS);
            } else {
                logger.error("No ingredients found for recipe {}:", idRecipe);
                recipeIngredientListModel.setIngredientModelList(new ArrayList<>());
                recipeIngredientListModel.setModelStatusEnum(ModelStatusEnum.ERROR);
                recipeIngredientListModel.setMessage("No ingredients found for this recipe.");
            }
        } catch (Exception e) {
            logger.error("There was an error retrieving ingredients found for recipe {}:", idRecipe);
            recipeIngredientListModel.setModelStatusEnum(ModelStatusEnum.ERROR);
            recipeIngredientListModel.setMessage("There was an error retrieving ingredients for the recipe.");
        }
        return recipeIngredientListModel;
    }

   /* public RecipeCategoryListModel getRecipeCategoryList() {
        RecipeCategoryListModel recipeCategoryListModel = new RecipeCategoryListModel();
        try {
            List<RecipeCategory> recipeCategoryList = recipeCategoryRepository.findAllByOrderByOrderSequence();
            if (CollectionUtils.isNotEmpty(recipeCategoryList)) {
                List<RecipeCategoryModel> recipeCategoryModelList = new ArrayList<>();
                for (RecipeCategory recipeCategory : recipeCategoryList) {
                    RecipeCategoryModel recipeCategoryModel = new RecipeCategoryModel();
                    recipeCategoryModel.setIdRecipeCategory(recipeCategory.getIdRecipeCategory());
                    recipeCategoryModel.setRecipeCategory(recipeCategory.getRecipeCategory());
                    recipeCategoryModel.setOrderSequence(recipeCategory.getOrderSequence());
                    if (recipeCategory.getCreateTimestamp() != null) {
                        recipeCategoryModel.setCreateTimestamp(DateTimeUtil.format(recipeCategory.getCreateTimestamp
                                ().toLocalDateTime()));
                    }
                    if (recipeCategory.getUpdateTimestamp() != null) {
                        recipeCategoryModel.setUpdateTimestamp(DateTimeUtil.format(recipeCategory.getUpdateTimestamp
                                ().toLocalDateTime()));
                    }
                    recipeCategoryModel.setModelStatusEnum(ModelStatusEnum.SUCCESS);
                    recipeCategoryModelList.add(recipeCategoryModel);
                }
                recipeCategoryListModel.setRecipeCategoryList(recipeCategoryModelList);
                recipeCategoryListModel.setModelStatusEnum(ModelStatusEnum.SUCCESS);
            } else {
                logger.error("No recipe categories found.");
                recipeCategoryListModel.setRecipeCategoryList(new ArrayList<>());
                recipeCategoryListModel.setModelStatusEnum(ModelStatusEnum.ERROR);
                recipeCategoryListModel.setMessage("No recipe categories found.");
            }
        } catch (Exception e) {
            logger.error("There was an error retrieving recipe categories: ", e);
            recipeCategoryListModel.setRecipeCategoryList(new ArrayList<>());
            recipeCategoryListModel.setModelStatusEnum(ModelStatusEnum.ERROR);
            recipeCategoryListModel.setMessage("There was an error retrieving recipe categories.");
        }
        return recipeCategoryListModel;
    }

    public BaseModel addRecipeCategory(Integer idRecipe, Integer idRecipeCategory) {
        BaseModel baseModel = new BaseModel();
        try {
            RecipeHasCategory recipeHasCategory = new RecipeHasCategory();
            recipeHasCategory.setIdRecipe(idRecipe);
            recipeHasCategory.setIdRecipeCategory(idRecipeCategory);
            recipeHasCategory.setCreateTimestamp(Timestamp.valueOf(LocalDateTime.now()));
            recipeHasCategoryRepository.save(recipeHasCategory);
            baseModel.setModelStatusEnum(ModelStatusEnum.SUCCESS);
        } catch (Exception e) {
            logger.error("There was an error adding the category to the recipe {}:", idRecipe, e);
            baseModel.setModelStatusEnum(ModelStatusEnum.ERROR);
            baseModel.setMessage("There was an error adding the category to the recipe.");
        }
        return baseModel;
    }

    public BaseModel removeRecipeCategory(Integer idRecipe, Integer idRecipeCategory) {
        BaseModel baseModel = new BaseModel();
        try {
            RecipeHasCategoryPK recipeHasCategoryPK = new RecipeHasCategoryPK();
            recipeHasCategoryPK.setIdRecipe(idRecipe);
            recipeHasCategoryPK.setIdRecipeCategory(idRecipeCategory);
            recipeHasCategoryRepository.delete(recipeHasCategoryPK);
            baseModel.setModelStatusEnum(ModelStatusEnum.SUCCESS);
        } catch (Exception e) {
            logger.error("There was an error removing the category from the recipe {}:", idRecipe, e);
            baseModel.setModelStatusEnum(ModelStatusEnum.ERROR);
            baseModel.setMessage("There was an error removing the category from the recipe.");
        }
        return baseModel;
    }*/
}