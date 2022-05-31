package com.example.ourrecipeapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class Recipe(@PrimaryKey(autoGenerate = true) var recipeId:Int?,
                  var name:String,
                  var servings:Int,
                  var time:Int,
                  var ingredients:String,
                  var instructions:String) {
}