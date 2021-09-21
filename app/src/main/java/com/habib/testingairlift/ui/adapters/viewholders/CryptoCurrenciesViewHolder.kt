package com.habib.testingairlift.ui.adapters.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.habib.testingairlift.data.local.models.CryptoCurrenciesDomainModel
import com.habib.testingairlift.databinding.CryptoCurrenciesItemLayoutBinding
import com.habib.testingairlift.utils.CurrencyClick

//import com.habib.testingairlift.utils.ArticleClick

class CryptoCurrenciesViewHolder(
    private val binding: CryptoCurrenciesItemLayoutBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: CryptoCurrenciesDomainModel, clickListener: CurrencyClick) {
        binding.apply {
            itemData= item
            currencyCallback = clickListener
            binding.executePendingBindings()
        }
    }
}