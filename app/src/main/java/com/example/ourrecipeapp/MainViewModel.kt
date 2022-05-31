package com.example.ourrecipeapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(app: Application) : AndroidViewModel(app) {

    private val repo: RecipeRepo
    var allRecipes : LiveData<List<Recipe>>
    lateinit var recipeById : LiveData<Recipe>

    init {
        repo = RecipeRepo(app)
        allRecipes = repo.getAllRecipes()!!
        //allRecipes = repo.search("")!!
    }

    fun getAllRecipes() = viewModelScope.launch {
        allRecipes = repo.getAllRecipes()!!
    }

    fun getRecipe(key: Int) = viewModelScope.launch {
        recipeById = repo.getRecipe(key)!!
    }

    fun search(name: String) = viewModelScope.launch {
        allRecipes = repo.search(name)!!
    }

    fun deleteAllRecipe() = viewModelScope.launch {
        repo.deleteAllRecipe()
    }

    fun insertRecipe(recipe: Recipe) = viewModelScope.launch {
        repo.insertRecipe(recipe)
    }

    fun updateRecipe(recipe: Recipe) = viewModelScope.launch {
        repo.updateRecipe(recipe)
    }

    fun deleteRecipe(recipe: Recipe) = viewModelScope.launch {
        repo.deleteRecipe(recipe)
    }

}