package com.example.fetchcodechallenge.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("tbl_item")
data class Item(
    @PrimaryKey val id: Int,
    val listId: Int,
    val name: String?
)
