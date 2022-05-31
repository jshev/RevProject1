package com.example.ourrecipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    lateinit var vm: MainViewModel
    var recipeID :Int = 0
    lateinit var currentRecipe : Recipe

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        vm = MainViewModel(application)

        var extras : Bundle? = getIntent().getExtras()
        if (extras != null) {
            recipeID = extras.getInt("ID")
        }

        vm.getRecipe(recipeID)

        vm.recipeById.observe(this, {
                recipe -> assignRecipe(recipe)
            setTexts()
        })

        backButton.setOnClickListener() {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        updateButton.setOnClickListener() {
            vm.updateRecipe(Recipe(recipeID, editName.text.toString(),
                editServing.text.toString().toInt(), editTime.text.toString().toInt(),
                editIngredients.text.toString(), editInstructions.text.toString()))
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        deleteButton.setOnClickListener() {
            vm.deleteRecipe(Recipe(recipeID, editName.text.toString(),
                editServing.text.toString().toInt(), editTime.text.toString().toInt(),
                editIngredients.text.toString(), editInstructions.text.toString()))
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        //editName.setText(currentRecipe.name)

    }

    fun assignRecipe(recipe: Recipe) {
        this.currentRecipe = recipe
    }

    fun setTexts() {
        editName.setText(currentRecipe.name)
        editServing.setText(Integer.toString(currentRecipe.servings))
        editTime.setText(Integer.toString(currentRecipe.time))
        editIngredients.setText(currentRecipe.ingredients)
        editInstructions.setText(currentRecipe.instructions)
    }
}