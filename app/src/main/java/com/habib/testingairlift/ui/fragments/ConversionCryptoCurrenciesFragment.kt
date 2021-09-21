package com.habib.testingairlift.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.habib.testingairlift.R
import com.habib.testingairlift.data.local.models.CryptoCurrenciesDomainModel
import com.habib.testingairlift.databinding.CryptoCurrenciesFragmentBinding
import com.habib.testingairlift.databinding.FragmentConversionCryptoCurrenciesBinding
import com.habib.testingairlift.utils.Constants
import java.util.*

/**
 * A simple [Fragment] subclass.
 * Use the [ConversionCryptoCurrenciesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ConversionCryptoCurrenciesFragment : Fragment() {

    private val args: ConversionCryptoCurrenciesFragmentArgs by navArgs()
    private val data: CryptoCurrenciesDomainModel by lazy { args.data!! }
    private lateinit var binding: FragmentConversionCryptoCurrenciesBinding
    private val viewModel: ConversionCryptoCurrenciesViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProvider(activity).get(ConversionCryptoCurrenciesViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        binding = FragmentConversionCryptoCurrenciesBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.tvFromCurrency.text = data.symbol

        binding.btnConvert.setOnClickListener {
            viewModel.convert(
                data.symbol,
                binding.etFrom.text.toString(),
                binding.spToCurrency.selectedItem.toString()
            )

        }

        return binding.root
    }

    companion object {
        fun newInstance() = ConversionCryptoCurrenciesFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.eventMessage.observe(viewLifecycleOwner, {
            when (it) {
                Constants.SUCCESS->{
                    binding.shimmerViewContainer.visibility= View.GONE
                    binding.shimmerViewContainer.stopShimmerAnimation()
                }
                Constants.LOADING -> {
                    binding.shimmerViewContainer.visibility= View.VISIBLE
                    binding.shimmerViewContainer.startShimmerAnimation()
                }
                else -> {
                    binding.shimmerViewContainer.visibility= View.GONE
                    binding.shimmerViewContainer.stopShimmerAnimation()
                    Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        })

        viewModel.convertedResult.observe(viewLifecycleOwner,{
            binding.tvResult.text = it
        })
    }
}