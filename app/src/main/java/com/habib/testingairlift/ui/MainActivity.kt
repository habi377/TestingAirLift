package com.habib.testingairlift.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayoutMediator
import com.habib.testingairlift.R
import com.habib.testingairlift.databinding.ActivityMainBinding
import com.habib.testingairlift.ui.adapters.FragmentAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

//        val titles = arrayOf("CryptoCurrencies", "Conversion")
//
//        binding.viewPager.adapter = FragmentAdapter(this)
//
//        TabLayoutMediator(binding.tabLayout, binding.viewPager){tab, position ->
//            tab.text = titles[position]
//        }.attach()
    }
}