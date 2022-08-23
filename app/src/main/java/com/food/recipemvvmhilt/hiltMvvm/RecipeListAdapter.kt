package com.food.recipemvvmhilt.hiltMvvm

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.food.recipemvvmhilt.R
import com.food.recipemvvmhilt.hiltMvvm.network.model.Hit


class RecipeListAdapter(
    private val modelList: ArrayList<Hit>,
    private val context: Context,
    private val callBack:((Hit,Int)->Unit)
) : RecyclerView.Adapter<RecipeListAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
          val view =
              LayoutInflater.from(context).inflate(R.layout.sample_recipe_item, parent, false)
          return ListViewHolder(view)
    }
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val model = modelList[position]
        try {
        holder.apply {
//            videoTitle.text = model.main.temp.toString()
            nameRecipe?.text = model.recipe.label

            Glide.with(context)
                .load(model.recipe.image)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true).listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        holder.progressBar?.visibility = View.INVISIBLE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        holder.progressBar?.visibility = View.INVISIBLE
                        holder.thumbNailImage?.setImageDrawable(resource)
                        return true
                    }
                }).placeholder(R.drawable.ic_baseline_image).into(holder.thumbNailImage!!)

            holder.itemView.setOnClickListener {
                callBack(model, position)
            }
            }
        } catch (e: Exception) {
        }

    }


    override fun getItemCount(): Int {
          return modelList.size
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var nameRecipe: TextView? = null
        internal var thumbNailImage: ImageView? = null
        internal var progressBar: ProgressBar? = null

        init {
            itemView.findViewById<TextView>(R.id.nameRecipeItem).also { nameRecipe = it }
            itemView.findViewById<ImageView>(R.id.thumbNailImage).also { thumbNailImage = it }
            itemView.findViewById<ProgressBar>(R.id.progressBar).also { progressBar = it }
        }
    }
}