package com.example.easyfood.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.easyfood.R
import com.example.easyfood.databinding.ActivityMealBinding
import com.example.easyfood.databinding.FragmentHomeBinding
import com.example.easyfood.ui.fragment.HomeFragment
import com.example.easyfood.viewmodel.MealViewModel

class MealActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMealBinding
    private lateinit var mealId: String
    private lateinit var mealName: String
    private lateinit var mealThumb: String
    private lateinit var mealMVVM: MealViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMealBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mealMVVM = ViewModelProvider(this)[MealViewModel::class.java]
        getMealInformationFromIntent()
        setInformationInViews()
        mealMVVM.getMealDetails(mealId)
        observerMealDetailsLiveData()
    }

    private fun observerMealDetailsLiveData() {
        mealMVVM.observeMealDetailLiveData().observe(this
        ) {
            val meal = it
           binding.tvCategory.text= "Category: ${meal!!.strCategory} "
            binding.tvArea.text= "Area: ${meal!!.strArea} "
            binding.tvInstruction.text=  meal.strInstructions
        }

    }

    private fun setInformationInViews() {
        Glide.with(applicationContext).load(mealThumb).into(binding.imgMealDetail)
        binding.collapsingToolbar.title= mealName
        binding.collapsingToolbar.setCollapsedTitleTextColor(resources.getColor(R.color.white))
        binding.collapsingToolbar.setExpandedTitleColor(resources.getColor(R.color.white))
    }

    private fun getMealInformationFromIntent() {
        val intent= intent
        mealId= intent.getStringExtra(HomeFragment.MEAL_ID).toString()
        mealName= intent.getStringExtra(HomeFragment.MEAL_NAME).toString()
        mealThumb= intent.getStringExtra(HomeFragment.MEAL_THUMB).toString()

    }
}