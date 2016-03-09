package com.debes.plateplanner.core;

import com.debes.plateplanner.dao.recipe.Recipe;
import com.debes.plateplanner.dao.recipe.RecipeCategory;
import com.debes.plateplanner.dao.recipe.RecipeIngredient;
import com.debes.plateplanner.dao.recipe.repository.RecipeCategoryRepository;
import com.debes.plateplanner.dao.recipe.repository.RecipeIngredientRepository;
import com.debes.plateplanner.dao.recipe.repository.RecipeRepository;
import com.debes.plateplanner.models.BaseModel;
import com.debes.plateplanner.models.enums.ModelStatusEnum;
import com.debes.plateplanner.models.recipe.*;
import com.debes.plateplanner.util.DateTimeUtil;
import org.apache.commons.collections4.CollectionUtils;
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
    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private RecipeCategoryRepository recipeCategoryRepository;

    @Autowired
    private RecipeIngredientRepository recipeIngredientRepository;

    public RecipeCategoryListModel getRecipeCategoryList() {
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
                    recipeCategoryModel.setCreateTimestamp(DateTimeUtil.format(recipeCategory.getCreateTimestamp().toLocalDateTime()));
                    if (null != recipeCategory.getUpdateTimestamp()) {
                        recipeCategoryModel.setUpdateTimestamp(DateTimeUtil.format(recipeCategory.getUpdateTimestamp().toLocalDateTime()));
                    }
                    recipeCategoryModelList.add(recipeCategoryModel);
                }
                recipeCategoryListModel.setRecipeCategoryList(recipeCategoryModelList);
                recipeCategoryListModel.setModelStatusEnum(ModelStatusEnum.SUCCESS);
            } else {
                //TODO:  HOW TO THROW NO DATA FOUND EXCEPTION
                recipeCategoryListModel.setRecipeCategoryList(new ArrayList<>());
                recipeCategoryListModel.setModelStatusEnum(ModelStatusEnum.ERROR);
                recipeCategoryListModel.setMessage("There was an error retrieving recipe categories");
            }
        } catch (Exception e) {
            recipeCategoryListModel.setRecipeCategoryList(new ArrayList<>());
            recipeCategoryListModel.setModelStatusEnum(ModelStatusEnum.ERROR);
            recipeCategoryListModel.setMessage("There was an error retrieving recipe categories");
            //TODO:  LOGGER STATEMENT
        }

        return recipeCategoryListModel;
    }

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
            recipeModel.setIdRecipe(null);
            recipeModel.setModelStatusEnum(ModelStatusEnum.ERROR);
            recipeModel.setMessage("There was an error upserting recipe");
            //TODO:  LOGGER STATEMENT
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
            recipeModel.setCreateTimestamp(DateTimeUtil.format(recipe.getCreateTimestamp().toLocalDateTime()));
            if (recipe.getUpdateTimestamp() != null) {
                recipeModel.setUpdateTimestamp(DateTimeUtil.format(recipe.getUpdateTimestamp().toLocalDateTime()));
            }
            recipeModel.setModelStatusEnum(ModelStatusEnum.SUCCESS);
        } catch (Exception e) {
            recipeModel.setModelStatusEnum(ModelStatusEnum.ERROR);
            recipeModel.setMessage("There was an error retrieving recipe: " + idRecipe);
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
                    recipeModel.setCreateTimestamp(DateTimeUtil.format(recipe.getCreateTimestamp().toLocalDateTime()));
                    if (recipe.getUpdateTimestamp() != null) {
                        recipeModel.setUpdateTimestamp(DateTimeUtil.format(recipe.getUpdateTimestamp()
                                .toLocalDateTime()));
                    }
                    recipeModelList.add(recipeModel);
                }
                recipeListModel.setRecipeModelList(recipeModelList);
                recipeListModel.setModelStatusEnum(ModelStatusEnum.SUCCESS);
            } else {
                recipeListModel.setModelStatusEnum(ModelStatusEnum.ERROR);
                recipeListModel.setMessage("There was an error retrieving the recipe list");
            }
        } catch (Exception e) {
            recipeListModel.setModelStatusEnum(ModelStatusEnum.ERROR);
            recipeListModel.setMessage("There was an error retrieving the recipe list");
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
            baseModel.setModelStatusEnum(ModelStatusEnum.ERROR);
            baseModel.setMessage("There was an error removing recipe: " + idRecipe);
        }
        return baseModel;
    }

    @Transactional
    public IngredientModel addIngredient (Integer idRecipe, IngredientModel ingredientModel) {
        try {
            RecipeIngredient recipeIngredient = new RecipeIngredient();
            recipeIngredient.setIdRecipe(idRecipe);
            recipeIngredient.setIdMeasurement(ingredientModel.getIdMeasurement());
            recipeIngredient.setIngredientMeasurementAmount(ingredientModel.getIngredientMeasurementAmount());
            recipeIngredient.setIngredientName(ingredientModel.getIngredientName());
            recipeIngredient.setOrderSequence(ingredientModel.getOrderSequence());
            recipeIngredientRepository.save(recipeIngredient);
            ingredientModel.setModelStatusEnum(ModelStatusEnum.SUCCESS);
        } catch (Exception e) {
            ingredientModel.setModelStatusEnum(ModelStatusEnum.ERROR);
            ingredientModel.setMessage("There was an error adding the ingredient");
        }
        return ingredientModel;
    }

    @Transactional
    public IngredientModel updateIngredient(Integer idRecipe, IngredientModel ingredientModel) {
        try {
            RecipeIngredient recipeIngredient = recipeIngredientRepository.findByIdRecipeAndIdRecipeIngredient(
                    idRecipe, ingredientModel.getIdRecipeIngredient());
            recipeIngredient.setIdMeasurement(ingredientModel.getIdMeasurement());
            recipeIngredient.setIngredientMeasurementAmount(ingredientModel.getIngredientMeasurementAmount());
            recipeIngredient.setIngredientName(ingredientModel.getIngredientName());
            recipeIngredient.setOrderSequence(ingredientModel.getOrderSequence());
            recipeIngredientRepository.save(recipeIngredient);
            ingredientModel.setModelStatusEnum(ModelStatusEnum.SUCCESS);
        } catch (Exception e) {
            ingredientModel.setModelStatusEnum(ModelStatusEnum.ERROR);
            ingredientModel.setMessage("There was an error updating the ingredient");
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
            baseModel.setModelStatusEnum(ModelStatusEnum.ERROR);
            baseModel.setMessage("There was an error removing the ingredient");
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
            baseModel.setModelStatusEnum(ModelStatusEnum.ERROR);
            baseModel.setMessage("There was an error removing all ingredients for the recipe");
        }
        return baseModel;
    }

    public RecipeIngredientListModel getRecipeIngredientList(Integer idRecipe) {
        RecipeIngredientListModel recipeIngredientListModel = new RecipeIngredientListModel();
        try {
            //TODO: Use Lambdas?
            List<RecipeIngredient> recipeIngredientList = recipeIngredientRepository.findByIdRecipe(idRecipe);
            if (CollectionUtils.isNotEmpty(recipeIngredientList)) {
                List<IngredientModel> ingredientModelList = new ArrayList<>();
                for (RecipeIngredient ingredient : recipeIngredientList) {
                    IngredientModel ingredientModel = new IngredientModel();
                    ingredientModel.setIdRecipeIngredient(ingredient.getIdRecipeIngredient());
                    ingredientModel.setIngredientName(ingredient.getIngredientName());
                    ingredientModel.setIdMeasurement(ingredient.getIdMeasurement());
                    ingredientModel.setIngredientMeasurementAmount(ingredient.getIngredientMeasurementAmount());
                    ingredientModel.setOrderSequence(ingredient.getOrderSequence());
                    ingredientModel.setIdRecipe(ingredient.getIdRecipe());
                    ingredientModelList.add(ingredientModel);
                }
                recipeIngredientListModel.setIngredientModelList(ingredientModelList);
                recipeIngredientListModel.setModelStatusEnum(ModelStatusEnum.SUCCESS);
            } else {
                //TODO:  HOW TO THROW NO DATA FOUND EXCEPTION
                recipeIngredientListModel.setIngredientModelList(new ArrayList<>());
                recipeIngredientListModel.setModelStatusEnum(ModelStatusEnum.ERROR);
                recipeIngredientListModel.setMessage("There was an error retrieving ingredients for the recipe");
            }

        } catch (Exception e) {
            recipeIngredientListModel.setModelStatusEnum(ModelStatusEnum.ERROR);
            recipeIngredientListModel.setMessage("There was an error retrieving ingredients for the recipe");
        }
        return recipeIngredientListModel;
    }

}