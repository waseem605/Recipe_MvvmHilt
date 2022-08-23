package com.food.recipemvvmhilt.hiltMvvm

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.food.recipemvvmhilt.AppUtils.RecipeAssistance
import com.food.recipemvvmhilt.R
import com.food.recipemvvmhilt.databinding.ActivityRecipeDetailsViewBinding
import com.food.recipemvvmhilt.hiltMvvm.adapterRecipe.DirectionsListAdapter
import com.food.recipemvvmhilt.hiltMvvm.adapterRecipe.IngredientListAdapter
import com.food.recipemvvmhilt.hiltMvvm.network.model.Ingredient
import com.food.recipemvvmhilt.hiltMvvm.network.model.Recipe
import java.text.DecimalFormat

class RecipeDetailsViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecipeDetailsViewBinding
    private lateinit var mIngredientListAdapter: IngredientListAdapter
    private lateinit var mDirectionsListAdapter: DirectionsListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeDetailsViewBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val hitRecipe: Recipe? = RecipeAssistance.recipeIntentModel

        if (hitRecipe != null) {

            binding.calories.text = "${DecimalFormat("#.#").format(hitRecipe.calories)} kcal"
            binding.servingFor.text = "${DecimalFormat("#").format(hitRecipe.yield)} Servings"
            if (hitRecipe.totalTime == 0.0){
                binding.timeTaking.visibility = View.GONE
            }else{
                binding.timeTaking.text = "${DecimalFormat("#").format(hitRecipe.totalTime)} min"
            }

            if (hitRecipe.ingredients.isNotEmpty()){
                mIngredientListAdapter = IngredientListAdapter(hitRecipe.ingredients as ArrayList<Ingredient>,this)
                binding.ingredientRecycler.apply {
                    setHasFixedSize(true)
                    layoutManager = GridLayoutManager(this@RecipeDetailsViewActivity,2)
                    adapter = mIngredientListAdapter
                }
            }
            if (hitRecipe.ingredientLines.isNotEmpty()){
                mDirectionsListAdapter = DirectionsListAdapter(hitRecipe.ingredientLines as ArrayList<String>,this)
                binding.ingredientDetailsRecycler.apply {
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(this@RecipeDetailsViewActivity)
                    adapter = mDirectionsListAdapter
                }
            }


            Glide.with(this)
                .load(hitRecipe.image)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true).listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        binding.progressBar.visibility = View.INVISIBLE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        binding.progressBar.visibility = View.INVISIBLE
                        binding.recipeImage.setImageDrawable(resource)
                        return true
                    }
                }).placeholder(R.drawable.ic_baseline_image).into(binding.recipeImage)
        }
    }




}