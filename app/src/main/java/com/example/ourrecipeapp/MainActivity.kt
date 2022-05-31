package com.example.ourrecipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var vm: MainViewModel
    var recipeList = ArrayList<Recipe>()
    lateinit var adapter: RecipeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vm = MainViewModel(application)

        /*vm.insertRecipe(Recipe(null, "Chicken & Veggie Stir-Fry", 6, 32,
            "1 lb chicken breast, cubed\n" +
                "salt, to taste\n" +
                "pepper, to taste\n" +
                "1 lb broccoli florets\n" +
                "8 oz mushroom, sliced\n" +
                "3 tablespoons oil, for frying\n" +
                "\n" +
                "SAUCE\n" +
                "3 cloves garlic, minced\n" +
                "1 tablespoon ginger, minced\n" +
                "2 teaspoons sesame oil\n" +
                "⅓ cup reduced sodium soy sauce\n" +
                "1 tablespoon brown sugar\n" +
                "1 cup chicken broth\n" +
                "¼ cup flour",
            "In a large pan on medium-high heat, add 1 tablespoon of oil. Once the oil is " +
                    "hot, add chicken, season with salt and pepper, and sauté until cooked through " +
                    "and browned. Remove cooked chicken from pan and set aside.\n" +
                    "In the same pan, heat 1 tablespoon of oil and add mushrooms. When the mushrooms " +
                    "start to soften, add broccoli florets and stir-fry until the broccoli is tender. " +
                    "Remove cooked mushrooms and broccoli from the pan and set aside.\n" +
                    "Add 1 tablespoon of oil to the pan and sauté garlic and ginger until fragrant. " +
                    "Add the remaining sauce ingredients and stir until smooth.\n" +
                    "Return the chicken and vegetables to the saucy pan, stir until heated through.\n" +
                    "Serve with hot rice or noodles."))

        vm.insertRecipe(
            Recipe(null, "Breakfast Ramen", 1, 5,
                "1 package instant ramen, prepared\n" +
                    "1 egg, fried\n" +
                    "3 slices bacon, cooked and chopped\n" +
                    "1 tablespoon scallions, thinly sliced\n" +
                    "kosher salt, to taste\n" +
                    "black pepper, to taste",
                "To a pot with 2 cups (480 ml) of boiling water add the ramen seasoning " +
                        "packet and a tablespoon of butter. Stir until the butter melts and then add" +
                        " the ramen noodle cube and cook until the noodles become tender, 2 minutes. " +
                        "Pour the ramen into a bowl.\n" +
                    "Top the prepared ramen with the fried egg, bacon, and scallions and season with" +
                        " salt and pepper."))

        vm.insertRecipe(
            Recipe(null, "Red Curry Chicken", 6, 29,
            "2 tablespoons coconut oil\n" +
                    "1 (16 ounce) package skinless, boneless chicken breast halves, cut into small cubes\n" +
                    "1 (14 ounce) can cream of coconut\n" +
                    "1 (11 ounce) bottle red Thai curry sauce\n" +
                    "½ (16 ounce) package dried rice stick vermicelli noodles",
            "Heat oil in a large skillet on high heat. Add chicken cubes; cook until " +
                    "browned, about 2 minutes per side. Reduce heat to medium-high and add coconut " +
                    "cream and curry sauce. Cook until chicken is no longer pink in the center and " +
                    "the juices run clear, about 5 minutes. An instant-read thermometer inserted into" +
                    " the center should read at least 165 degrees F (74 degrees C).\n" +
                    "Fill a large pot with lightly salted water and bring to a rolling boil; stir in" +
                    " vermicelli pasta and return to a boil. Cook pasta uncovered, stirring occasionally," +
                    " until the pasta is tender yet firm to the bite, 4 to 5 minutes. Drain.\n" +
                    "Reduce skillet heat to simmer. Add the noodles and let simmer until flavors are" +
                    " absorbed, about 5 minutes. Divide chicken and noodles among individual serving bowls."))*/

        vm.allRecipes.observe(this, {
                recipeList -> getRecipes(recipeList)
            if (recipeList.isEmpty()) {
                emptyListText.text = "LIST EMPTY"
            } else {
                emptyListText.text = ""
            }
        })

        insertButton.setOnClickListener() {
            var intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }

        // get reference to view to populate
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // create adapter with data source and assign adapter
        adapter = RecipeAdapter(recipeList)
        recyclerView.adapter = adapter

        //search bar
        searchBar.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                var searchText = searchBar.text.toString()
                if (searchText != "") {
                    vm.search(searchText)
                } else {
                    vm.getAllRecipes()
                }
                vm.allRecipes.observe(this@MainActivity, {
                        recipeList -> getRecipes(recipeList)
                    if (recipeList.isEmpty()) {
                        emptyListText.text = "LIST EMPTY"
                    } else {
                        emptyListText.text = ""
                    }
                })
            }
        })

    }

    fun getRecipes(recipeList: List<Recipe>) {
        this.recipeList.clear()
        this.recipeList.addAll(recipeList)
        adapter.notifyDataSetChanged()
    }
}