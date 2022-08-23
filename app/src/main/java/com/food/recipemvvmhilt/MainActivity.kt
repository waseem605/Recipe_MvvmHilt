package com.food.recipemvvmhilt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.food.recipemvvmhilt.databinding.ActivityMainBinding
import com.food.recipemvvmhilt.hiltMvvm.MainActivityViewModel
import com.food.recipemvvmhilt.hiltMvvm.network.model.RecipeMainModelView
import com.food.recipemvvmhilt.hiltMvvm.weatherModels.WeatherMainModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
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
        initRecyclerData()

    }

        private fun initRecyclerData() {

            mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
            val name = "chicken"
            val mealType = "Dinner"
            val appId = "8de29aae743926dea65823c2c79f136a"
            mainActivityViewModel.callData(binding.etName.text.toString().trim(),
            binding.menuSpinner.selectedItem.toString().trim())
            mainActivityViewModel.mWeatherData.observe(this,mainObserverDataTranslator)
        }

    private var mainObserverDataTranslator=object: Observer<RecipeMainModelView> {
        override fun onChanged(it: RecipeMainModelView?) {
            if (it!=null){
                binding.searchImage.visibility = View.GONE
                Log.d("mainObserverData", "onChanged: ${it.hits.size}")
                //showData(it.hits as ArrayList<Hit>)
            }else{
                Toast.makeText(this@MainActivity,"Result Not Found", Toast.LENGTH_SHORT).show()
            }
        }
    }


}