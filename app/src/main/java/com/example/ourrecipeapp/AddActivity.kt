package com.example.ourrecipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {

    lateinit var vm: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        vm = MainViewModel(application)

        addButton.setOnClickListener() {
            vm.insertRecipe(Recipe(null, editName.text.toString(),
            editServing.text.toString().toInt(),editTime.text.toString().toInt(),
            editIngredients.text.toString(), editInstructions.text.toString()))
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        backButton.setOnClickListener() {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        resetButton.setOnClickListener() {
            editName.text.clear()
            editServing.text.clear()
            editTime.text.clear()
            editIngredients.text.clear()
            editInstructions.text.clear()
        }
    }
}