package com.example.easyfood.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.easyfood.activity.MealActivity
import com.example.easyfood.databinding.FragmentHomeBinding
import com.example.easyfood.model.Meal
import com.example.easyfood.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    private lateinit var homeMvvm: HomeViewModel
    private lateinit var binding: FragmentHomeBinding
    private lateinit var randomMeal: Meal

    companion object{
        const val MEAL_ID= "com.example.easyfood.ui.fragment.idMeal"
        const val MEAL_NAME= "com.example.easyfood.ui.fragment.nameMeal"
        const val MEAL_THUMB= "com.example.easyfood.ui.fragment.thumbMeal"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeMvvm= ViewModelProvider(this)[HomeViewModel::class.java]

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeMvvm.getRandomMeal()
        observeRandomMeal()
        onRandomMealClick()
    }

    private fun onRandomMealClick() {
        binding.cvRandomMeal.setOnClickListener {
            val intent = Intent(activity, MealActivity::class.java)
            intent.putExtra(MEAL_ID, randomMeal.idMeal)
            intent.putExtra(MEAL_NAME, randomMeal.strMeal)
            intent.putExtra(MEAL_THUMB, randomMeal.strMealThumb)
            startActivity(intent)

        }
    }

    private fun observeRandomMeal() {
        homeMvvm.observeRandomMealLiveData().observe(viewLifecycleOwner
        ) { value ->
            Glide.with(this@HomeFragment).load(value.strMealThumb)
                .into(binding.imgRandomMeal)
            this.randomMeal = value
        }
    }
}