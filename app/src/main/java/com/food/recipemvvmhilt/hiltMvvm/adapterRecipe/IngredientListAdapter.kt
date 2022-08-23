package com.food.recipemvvmhilt.hiltMvvm.adapterRecipe

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.food.recipemvvmhilt.databinding.SampleIngredientsItemBinding
import com.food.recipemvvmhilt.hiltMvvm.network.model.Ingredient
import java.text.DecimalFormat

class IngredientListAdapter(
    private val modelArrayList: ArrayList<Ingredient>,
    private val context: Context,

    ) : RecyclerView.Adapter<IngredientListAdapter.GridviewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridviewHolder {
        val binding = SampleIngredientsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GridviewHolder(binding)
    }

    override fun onBindViewHolder(holder: GridviewHolder, i: Int) {
        val model = modelArrayList[i]
        holder.binding.apply {
            ingredientName.text = model.text
            ingredientQuantity.text = "Quantity: ${DecimalFormat("#.#").format(model.quantity)}"
            ingredientWeight.text = "weight: ${DecimalFormat("#.#").format(model.weight)} g"
        }
    }

    override fun getItemCount(): Int {
        return modelArrayList.size
    }

    inner class GridviewHolder(val binding: SampleIngredientsItemBinding) : RecyclerView.ViewHolder(binding.root)
}