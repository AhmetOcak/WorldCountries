package com.worldcountries.ui.home

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.worldcountries.databinding.FragmentHomeBinding
import com.worldcountries.ui.home.adapter.CountryListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CountryListAdapter()
        binding.rvHomeCountryList.apply {
            this.adapter = adapter
            layoutManager = GridLayoutManager(context, getGridSpan())
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    binding.isLoading = uiState.isLoading

                    if (uiState.countryList.isNotEmpty()) {
                        adapter.submitList(uiState.countryList)
                    }

                    if (uiState.errorMessageId != null) {
                        Toast.makeText(
                            context,
                            getString(uiState.errorMessageId),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }

    private fun getGridSpan() =
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) 2 else 3

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}