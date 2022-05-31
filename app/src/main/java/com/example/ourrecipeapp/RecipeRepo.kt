package com.example.ourrecipeapp

import android.content.Context
import androidx.lifecycle.LiveData

class RecipeRepo(context: Context) {
    var db:RecipeDao? = AppDatabase.getInstance(context)?.recipeDao()

    fun getAllRecipes(): LiveData<List<Recipe>>? {
        return db?.selectRecipes()
    }

    fun getRecipe(key: Int) : LiveData<Recipe>? {
        return db?.selectRecipe(key)
    }

    fun search(name: String): LiveData<List<Recipe>>? {
        return db?.search("%" + name + "%")
    }

    fun deleteAllRecipe() {
        db?.deleteRecipes()
    }

    fun insertRecipe(recipe: Recipe) {
        db?.insertRecipe(recipe)
    }

    fun updateRecipe(recipe: Recipe) {
        db?.updateRecipe(recipe)
    }

    fun deleteRecipe(recipe: Recipe) {
        db?.deleteRecipe(recipe)
    }
}