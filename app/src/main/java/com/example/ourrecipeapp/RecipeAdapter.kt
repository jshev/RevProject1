package com.example.ourrecipeapp

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class RecipeAdapter(private val recipeList: List<Recipe>): RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflate
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recipe_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // add element to view holder
        val itemVM = recipeList[position]
        holder.name.text = itemVM.name

        holder.card.setOnClickListener() {
            //Log.d("CARD CLICKED", itemVM.recipeId.toString())
            var context = holder.card.getContext()
            var intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("ID", itemVM.recipeId )
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        // size of the list/datasource
        return recipeList.size
    }
}

class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val name : TextView = view.findViewById(R.id.recipeName)
    val card : CardView = view.findViewById(R.id.recipeCard)
}