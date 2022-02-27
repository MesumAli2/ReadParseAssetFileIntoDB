package com.mesum.readparseassetfiledb

import androidx.room.*
import com.mesum.readparseassetfiledb.model.Item
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao{

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Item)

    @Update
    suspend fun update(item: Item)

    @Delete
    suspend fun delete(item: Item)

    @Query("SELECT * from item where id = :id")
    fun getItem(id:Int) : Flow<Item>

    @Query("SELECT * from item ORDER by id DESC")
    fun getItems(): Flow<List<Item>>

}