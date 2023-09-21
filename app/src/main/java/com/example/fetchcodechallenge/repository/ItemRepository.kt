package com.example.fetchcodechallenge.repository

import android.util.Log
import com.example.fetchcodechallenge.model.dao.ItemDao
import com.example.fetchcodechallenge.model.data.Item
import com.example.fetchcodechallenge.utils.ResultInfo
import com.example.fetchcodechallenge.webservice.ApiClient
import javax.inject.Inject

class ItemRepository @Inject constructor(
    private val itemDao: ItemDao,
    private val apiClient: ApiClient
) {
    private val TAG = "ItemRepository"

    suspend fun fetchData(): ResultInfo<List<Item>>  {
        try {
            /*
            If there is no record in Room DB then fetch data from Server.
            As an improvement, we can add an functionality to forcefully fetch data
            from server and then save to Room DB.
             */
            if(itemDao.getRecordCount() < 1) {
                val itemResponse = apiClient.getItems().toList()
                itemDao.insertItem(itemResponse)
            }
            return ResultInfo.Success(itemDao.getItems())
        }catch (e: Exception) {
            Log.d(TAG, "ERROR = ${e.message.toString()}")
            return ResultInfo.Error("${e.message.toString()}")
        }
    }
}