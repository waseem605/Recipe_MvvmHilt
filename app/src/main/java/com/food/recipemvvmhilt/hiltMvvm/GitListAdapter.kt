package com.food.recipemvvmhilt.hiltMvvm

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.food.recipemvvmhilt.R
import com.food.recipemvvmhilt.hiltMvvm.network.model.Hit
import com.food.recipemvvmhilt.hiltMvvm.weatherModels.WeatherList


class GitListAdapter(
    private val modelList: ArrayList<Hit>,
    private val context: Context,
    private val callBack:((Hit,Int)->Unit)
) : RecyclerView.Adapter<GitListAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
          val view =
              LayoutInflater.from(context).inflate(R.layout.sample_device_file_item, parent, false)
          return ListViewHolder(view)
    }
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val model = modelList[position]
        holder.apply {
//            videoTitle.text = model.main.temp.toString()
            videoDesc.text = model.recipe.label

//            Glide.with(context).load(model.owner).into(imageView)

            itemView.setOnClickListener {
                callBack(model,position)
            }
        }
    }


    override fun getItemCount(): Int {
          return modelList.size
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView = itemView.findViewById<ImageView>(R.id.imageData)
        var videoTitle:TextView = itemView.findViewById(R.id.videoTitle)
        var videoDesc:TextView = itemView.findViewById(R.id.videoDesc)

    }
}