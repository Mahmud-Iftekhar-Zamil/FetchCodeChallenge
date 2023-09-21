package com.example.fetchcodechallenge.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetchcodechallenge.model.data.Item
import com.example.fetchcodechallenge.repository.ItemRepository
import com.example.fetchcodechallenge.utils.ResultInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(
    private val itemRepository: ItemRepository
):ViewModel() {
    private val TAG = "ItemViewModel"

    init {
        fetchData()
    }

    private val _channel = Channel<ResultInfo<List<Item>>>()
    val channel = _channel.receiveAsFlow()

    private val _itemData = MutableLiveData<List<Item>>()
    val itemData: LiveData<List<Item>>
        get() = _itemData

    fun fetchData() = viewModelScope.launch(Dispatchers.IO) {
        val res = itemRepository.fetchData()
        res.data?.let {
            _itemData.postValue(it)
        }
        _channel.send(res)
    }
}