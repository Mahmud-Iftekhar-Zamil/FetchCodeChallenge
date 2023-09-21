package com.example.fetchcodechallenge.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.fetchcodechallenge.model.dao.ItemDao
import com.example.fetchcodechallenge.model.data.Item

@Database(entities = [Item::class], version = 2)
abstract class ItemDatabase: RoomDatabase() {
    abstract fun ItemDao(): ItemDao
}