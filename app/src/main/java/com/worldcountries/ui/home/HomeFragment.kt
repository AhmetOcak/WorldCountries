package com.worldcountries.ui.home

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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.worldcountries.R
import com.worldcountries.common.getGridSpan
import com.worldcountries.design.MarginItemDecoration
import com.worldcountries.databinding.FragmentHomeBinding
import com.worldcountries.databinding.SortBottomSheetLayoutBinding
import com.worldcountries.model.filter.Filter
import com.worldcountries.ui.adapter.CountryListAdapter
import com.worldcountries.ui.adapter.Screen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!

    private var _dialogBinding: SortBottomSheetLayoutBinding? = null
    private val dialogBinding: SortBottomSheetLayoutBinding get() = _dialogBinding!!

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

        findNavController().currentBackStackEntry?.savedStateHandle
            ?.getLiveData<ArrayList<Filter>>("filters")?.observe(viewLifecycleOwner) { filters ->
                viewModel.filterCountryList(filters)
            }

        val adapter = CountryListAdapter(findNavController(), Screen.HOME)
        binding.rvHomeCountryList.apply {
            this.adapter = adapter
            layoutManager = GridLayoutManager(context, getGridSpan(requireContext()))
            addItemDecoration(
                MarginItemDecoration(resources.getDimensionPixelSize(R.dimen.two_level_margin))
            )
        }

        binding.btnHomeFilter.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToFilterCountryListFragment()
            )
        }

        binding.btnHomeSort.setOnClickListener {
            showSortDialog()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    binding.apply {
                        isLoading = uiState.isLoading
                        isFilteredListEmpty = uiState.isFilteredListEmpty
                    }

                    if (uiState.isListFiltered) {
                        adapter.submitList(uiState.filteredList)
                    } else {
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

    private fun showSortDialog() {
        val dialog = BottomSheetDialog(requireContext())
        _dialogBinding = SortBottomSheetLayoutBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)

        dialogBinding.apply {
            when (viewModel.uiState.value.sortType) {
                SortType.HIGHEST_POP -> rbtnHighestPop.isChecked = true
                SortType.LOWEST_POP -> rbtnLowestPop.isChecked = true
                else -> {}
            }

            rbtnHighestPop.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    isAnyRadioBtnSelected = true
                    viewModel.setSortType(SortType.HIGHEST_POP)
                }
            }

            rbtnLowestPop.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    isAnyRadioBtnSelected = true
                    viewModel.setSortType(SortType.LOWEST_POP)
                }
            }

            btnHomeApplySort.setOnClickListener {
                if (dialogBinding.isAnyRadioBtnSelected == true) {
                    viewModel.sortCountryList()
                    binding.rvHomeCountryList.smoothScrollToPosition(0)
                    dialog.dismiss()
                } else {
                    dialog.dismiss()
                }
            }
        }

        dialog.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        _dialogBinding = null
    }
}