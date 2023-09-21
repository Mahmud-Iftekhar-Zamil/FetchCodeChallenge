package com.example.fetchcodechallenge.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fetchcodechallenge.model.data.Item

@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(itemList: List<Item>)

    @Query(""" 
            SELECT * FROM tbl_item WHERE tbl_item.name!=null OR tbl_item.name != "" ORDER BY tbl_item.listId ASC, tbl_item.name 
            """)
    suspend fun getItems():List<Item>

    @Query("SELECT COUNT(*) AS CNT FROM tbl_item")
    suspend fun getRecordCount(): Int
}