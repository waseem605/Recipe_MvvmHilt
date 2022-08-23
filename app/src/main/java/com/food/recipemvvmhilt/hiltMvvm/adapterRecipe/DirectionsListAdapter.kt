package com.food.recipemvvmhilt.hiltMvvm.adapterRecipe

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.food.recipemvvmhilt.databinding.SampleDirectionItemBinding

class DirectionsListAdapter(
    private val modelArrayList: ArrayList<String>,
    private val context: Context,

    ) : RecyclerView.Adapter<DirectionsListAdapter.GridviewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridviewHolder {
        val binding = SampleDirectionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GridviewHolder(binding)
    }

    override fun onBindViewHolder(holder: GridviewHolder, i: Int) {
        val model = modelArrayList[i]
        holder.binding.ingredientName.text = model
    }

    override fun getItemCount(): Int {
        return modelArrayList.size
    }

    inner class GridviewHolder(val binding: SampleDirectionItemBinding) : RecyclerView.ViewHolder(binding.root)
}