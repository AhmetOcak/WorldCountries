package com.worldcountries.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.worldcountries.R
import com.worldcountries.databinding.FragmentSearchBinding
import com.worldcountries.design.MarginItemDecoration
import com.worldcountries.ui.adapter.CountryListAdapter
import com.worldcountries.ui.adapter.Screen
import com.worldcountries.utils.getGridSpan
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding get() = _binding!!

    private val viewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CountryListAdapter(findNavController(), Screen.SEARCH)
        binding.rvSearch.apply {
            this.adapter = adapter
            layoutManager = GridLayoutManager(context, getGridSpan(requireContext()))
            addItemDecoration(MarginItemDecoration(resources.getDimensionPixelSize(R.dimen.two_level_margin)))
        }

        binding.tfSearch.setOnEditorActionListener { _, _, _ ->
            searchCountry()
            return@setOnEditorActionListener true
        }

        binding.tilSearch.setEndIconOnClickListener {
            searchCountry()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    binding.apply {
                        isLoading = uiState.isLoading
                        isError = uiState.isError
                        errorMessage = uiState.errorMessageId?.let { getString(it) }
                        isSearchEmpty = uiState.isSearchResultEmpty
                    }

                    adapter.submitList(uiState.resultList)
                }
            }
        }
    }

    private fun searchCountry() {
        val searchText = binding.tfSearch.text.toString()
        if (searchText.isBlank()) {
            binding.tilSearch.apply {
                isErrorEnabled = true
                error = getString(R.string.fill_field_error)
            }
        } else {
            binding.tilSearch.isErrorEnabled = false
            viewModel.getCountryByName(searchText)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}