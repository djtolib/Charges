package com.tolib.charges.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.tolib.charges.R
import com.tolib.charges.ui.viewmodel.MainViewModel
import com.tolib.charges.databinding.FragmentChargesBinding


class ChargesFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentChargesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChargesBinding.inflate(layoutInflater)
        configureList()
        configureListener()
        return binding.root
    }

    private fun configureListener() {
        binding.btnBack.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, CityFragment())
                .commitAllowingStateLoss()
        }
    }

    private fun configureList() {
        binding.list.layoutManager = LinearLayoutManager(requireContext())
        val data = viewModel.data.filter { it.city == viewModel.selectedCity }
        val adapter = ChargesRecyclerViewAdapter(data)
        binding.list.adapter = adapter
    }
}