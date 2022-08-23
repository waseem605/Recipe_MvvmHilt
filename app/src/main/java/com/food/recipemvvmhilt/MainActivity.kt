package com.food.recipemvvmhilt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.food.recipemvvmhilt.AppUtils.RecipeAssistance
import com.food.recipemvvmhilt.databinding.ActivityMainBinding
import com.food.recipemvvmhilt.hiltMvvm.RecipeListAdapter
import com.food.recipemvvmhilt.hiltMvvm.MainActivityViewModel
import com.food.recipemvvmhilt.hiltMvvm.RecipeDetailsViewActivity
import com.food.recipemvvmhilt.hiltMvvm.network.model.Hit
import com.food.recipemvvmhilt.hiltMvvm.network.model.RecipeMainModelView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mListAdapter: RecipeListAdapter
    private lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val languages = resources.getStringArray(R.array.RecipeTime)
        val adapter = ArrayAdapter(
            this,
            R.layout.sample_spinner_item, languages
        )
        binding.menuSpinner.adapter = adapter

        binding.searchBtn.setOnClickListener {
            if (isRecipeNameNotEmpty()){
                initRecyclerData()
            }
        }
    }

        private fun initRecyclerData() {
            binding.progressBar.visibility = View.VISIBLE
            mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
            mainActivityViewModel.callData(binding.etName.text.toString().trim(),
            binding.menuSpinner.selectedItem.toString().trim())
            mainActivityViewModel.mRecipeListData.observe(this,mainObserverDataTranslator)
        }

    private var mainObserverDataTranslator=object: Observer<RecipeMainModelView> {
        override fun onChanged(it: RecipeMainModelView?) {
            if (it!=null){
                binding.progressBar.visibility = View.GONE
                binding.searchImage.visibility = View.GONE
                Log.d("mainObserverData", "onChanged: ${it.hits.size}")
                showData(it.hits as ArrayList<Hit>)
            }else{
                binding.progressBar.visibility = View.GONE
                binding.searchImage.visibility = View.VISIBLE
                Toast.makeText(this@MainActivity,"Result Not Found", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showData(hits: ArrayList<Hit>) {
        mListAdapter = RecipeListAdapter(hits,this){ hit, _->
            RecipeAssistance.recipeIntentModel = hit.recipe
            startActivity(Intent(this,RecipeDetailsViewActivity::class.java))
        }

        binding.listRecyclerView.apply {
            layoutManager = GridLayoutManager(this@MainActivity,2)
            adapter = mListAdapter
            mListAdapter.notifyDataSetChanged()
        }
    }



    private fun isRecipeNameNotEmpty(): Boolean {
        if (binding.etName.text.isEmpty()) {
            binding.etName.error = "This field is required"
            Toast.makeText(this, "Please enter recipe name",Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }


}