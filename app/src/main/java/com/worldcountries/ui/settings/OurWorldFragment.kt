package com.worldcountries.ui.settings

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
import com.worldcountries.databinding.FragmentOurWorldBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class OurWorldFragment : Fragment() {

    private var _binding: FragmentOurWorldBinding? = null
    val binding: FragmentOurWorldBinding get() = _binding!!

    private val viewModel: OurWorldViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOurWorldBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getWorldData(requireContext())

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    binding.apply {
                        isLoading = uiState.isLoading
                        tvOurWorldIntroduction.text = uiState.worldData?.geography?.overview

                    }

                    if (uiState.errorMessage.isNotEmpty()) {
                        Toast.makeText(
                            context,
                            uiState.errorMessage.first(),
                            Toast.LENGTH_LONG
                        ).show()
                        viewModel.consumedError()
                    }
                }
            }
        }
    }
}