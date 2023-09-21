package com.example.fetchcodechallenge.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fetchcodechallenge.R
import com.example.fetchcodechallenge.databinding.FragmentItemBinding
import com.example.fetchcodechallenge.databinding.FragmentListItemBinding
import com.example.fetchcodechallenge.utils.ResultInfo
import com.example.fetchcodechallenge.view.adapter.ItemAdapter
import com.example.fetchcodechallenge.viewmodel.ItemViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ItemFragment: Fragment(R.layout.fragment_list_item) {

    private val viewModel: ItemViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentListItemBinding.bind(view)
        val itemAdapter = ItemAdapter()

        binding.recyclerViewItem.apply {
            adapter = itemAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }

        viewModel.itemData.observe(viewLifecycleOwner){
            itemAdapter.submitList(it)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.channel.collect { resultInfo ->
                    when(resultInfo) {
                        is ResultInfo.Loading -> {/* Can be used to show progress bar */}
                        is ResultInfo.Success -> {/* Can be used to stop progress bar */}
                        is ResultInfo.Error -> { Snackbar.make(binding.root,resultInfo.error, Snackbar.LENGTH_LONG).show() }
                    }
                }
            }
        }
    }
}