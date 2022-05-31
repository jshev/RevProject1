package com.example.ourrecipeapp

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RecipeDao {

    // Create
    @Insert
    fun insertRecipe(recipe: Recipe)

    // Read
    @Query("select * from recipes")
    fun selectRecipes() : LiveData<List<Recipe>>

    @Query("SELECT * from recipes WHERE recipeId = :key")
    fun selectRecipe(key: Int): LiveData<Recipe>

    @Query("select * from recipes where name like :name")
    fun search(name: String) : LiveData<List<Recipe>>

    // Update
    @Update
    fun updateRecipe(recipe: Recipe)

    // Delete
    @Delete
    fun deleteRecipe(recipe: Recipe)

    @Query("delete from recipes")
    fun deleteRecipes()

}