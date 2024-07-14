package com.tolib.charges.ui.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.tolib.charges.R
import com.tolib.charges.data.model.UiState
import com.tolib.charges.databinding.FragmentCityBinding
import com.tolib.charges.ui.viewmodel.MainViewModel
import kotlinx.coroutines.launch

class CityFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentCityBinding
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCityBinding.inflate(layoutInflater)
        configureFlow()
        setupViews()
        return binding.root
    }

    private fun setupViews() {
        adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = adapter

        binding.btnNext.setOnClickListener {
            viewModel.selectedCity = binding.spinner.selectedItem.toString()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, ChargesFragment())
                .commitAllowingStateLoss()
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getCharges()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.isVisible = isLoading
        binding.layout.isVisible = !isLoading
    }

    private fun configureFlow() =
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    when (state) {
                        UiState.Loading -> showLoading(true)
                        UiState.Success -> {
                            showLoading(false)
                            val cities = viewModel.data.map { it.city }.distinct()
                            updateSpinnerData(cities)
                            selectSavedCity()
                        }
                    }
                }
            }
        }


    private fun updateSpinnerData(cities: List<String>) {
        adapter.clear()
        adapter.addAll(cities)
    }

    private fun selectSavedCity() {
        if (viewModel.selectedCity.isNotBlank()) {
            val position = adapter.getPosition(viewModel.selectedCity)
            if (position != -1) {
                binding.spinner.setSelection(position)
            }
        }
    }
}