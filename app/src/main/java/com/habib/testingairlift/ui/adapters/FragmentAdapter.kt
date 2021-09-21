package com.habib.testingairlift.ui.adapters

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.habib.testingairlift.ui.fragments.ConversionCryptoCurrenciesFragment
import com.habib.testingairlift.ui.fragments.CryptoCurrenciesFragment

class FragmentAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity){
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> CryptoCurrenciesFragment.newInstance()
            1 -> ConversionCryptoCurrenciesFragment.newInstance()
            else -> CryptoCurrenciesFragment.newInstance()
        }
    }
}