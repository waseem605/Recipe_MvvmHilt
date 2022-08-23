package com.food.recipemvvmhilt.hiltMvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.food.recipemvvmhilt.databinding.ActivityMainListBinding
import com.food.recipemvvmhilt.hiltMvvm.network.model.Hit
import com.food.recipemvvmhilt.hiltMvvm.network.model.RecipeMainModelView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainListBinding
    private lateinit var mListAdapter: RecipeListAdapter
    private lateinit var mainActivityViewModel: MainActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerData()

    }

    private fun initRecyclerData() {
        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        val name = "chicken"
        val mealType = "Dinner"
        val appId = "8de29aae743926dea65823c2c79f136a"



    }

    private var mainObserverDataTranslator=object: Observer<RecipeMainModelView> {
        override fun onChanged(it: RecipeMainModelView?) {
            if (it!=null){
                Log.d("mainObserverData", "onChanged: ${it.hits.size}")
                    //showData(it.hits as ArrayList<Hit>)
                }else{
                    Toast.makeText(this@MainListActivity,"Result Not Found", Toast.LENGTH_SHORT).show()
                }
            }
        }

    private fun showData(hits: ArrayList<Hit>) {
        mListAdapter = RecipeListAdapter(hits,this){ _, _->

        }

        binding.listRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainListActivity)
            adapter = mListAdapter
            mListAdapter.notifyDataSetChanged()
        }
    }

}