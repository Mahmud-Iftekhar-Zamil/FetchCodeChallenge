package com.example.fetchcodechallenge.webservice

import com.example.fetchcodechallenge.model.data.Item
import retrofit2.http.GET

interface ApiClient {

    @GET("/hiring.json")
    suspend fun getItems(): ArrayList<Item>
}