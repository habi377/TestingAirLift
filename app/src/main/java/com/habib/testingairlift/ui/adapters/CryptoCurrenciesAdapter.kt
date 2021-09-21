package com.habib.testingairlift.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.habib.testingairlift.ui.adapters.viewholders.CryptoCurrenciesViewHolder
import com.habib.testingairlift.data.local.models.CryptoCurrenciesDomainModel
import com.habib.testingairlift.databinding.CryptoCurrenciesItemLayoutBinding
import com.habib.testingairlift.utils.CurrencyClick

//import com.habib.testingairlift.utils.ArticleClick

class CryptoCurrenciesAdapter (private val clickListener: CurrencyClick) : RecyclerView.Adapter<CryptoCurrenciesViewHolder>(){

    var cryptoCurrencies : List<CryptoCurrenciesDomainModel> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoCurrenciesViewHolder {
        return CryptoCurrenciesViewHolder(
            CryptoCurrenciesItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: CryptoCurrenciesViewHolder, position: Int) {
        val item  = cryptoCurrencies[position]
        holder.bind(item,clickListener)
    }

    override fun getItemCount(): Int {
        return cryptoCurrencies.size
    }
}