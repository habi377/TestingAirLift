package com.habib.testingairlift.ui.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.SyncStateContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.habib.testingairlift.data.local.models.CryptoCurrenciesDomainModel
import com.habib.testingairlift.databinding.CryptoCurrenciesFragmentBinding
import com.habib.testingairlift.ui.adapters.CryptoCurrenciesAdapter
import com.habib.testingairlift.utils.Constants
import com.habib.testingairlift.utils.CurrencyClick

class CryptoCurrenciesFragment : Fragment() {

    private lateinit var binding: CryptoCurrenciesFragmentBinding
    private lateinit var viewModelAdapter: CryptoCurrenciesAdapter

    private val viewModel: CryptoCurrenciesViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProvider(activity).get(CryptoCurrenciesViewModel::class.java)
    }

    companion object {
        fun newInstance() = CryptoCurrenciesFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = CryptoCurrenciesFragmentBinding.inflate(inflater, container, false)

        // Set the lifecycleOwner so DataBinding can observe LiveData
        binding.lifecycleOwner = viewLifecycleOwner

        binding.viewModel = viewModel

        binding.shimmerViewContainer.startShimmerAnimation()

        viewModelAdapter = CryptoCurrenciesAdapter(CurrencyClick { addItemClicked(it) })

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewModelAdapter
        }

        return binding.root
    }

    private fun addItemClicked(cryptoCurrenciesDomainModel: CryptoCurrenciesDomainModel) {
        val action =
            CryptoCurrenciesFragmentDirections.actionCryptoCurrenciesFragmentToConversionCryptoCurrenciesFragment(
                cryptoCurrenciesDomainModel
            )
        findNavController().navigate(action)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.eventMessage.observe(viewLifecycleOwner, {
            when (it) {
                Constants.SUCCESS,
                Constants.LOADING -> {
                }
                else -> {
                    Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        })

        viewModel.cryptoCurrenciesList.observe(viewLifecycleOwner,{
            viewModelAdapter.cryptoCurrencies = it
            viewModelAdapter.notifyDataSetChanged()
        })
    }

}