package com.example.todo_jetpackedition.data

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.example.todo_jetpackedition.Data
import kotlinx.coroutines.flow.Flow


@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(data: Data)

    @Delete
    suspend fun delete(data: Data)

    @Update
    suspend fun update(data: Data)

    @Query("SELECT * FROM  DetailTable ORDER BY id ")
    fun getAllData(): LiveData<List<Data>>

    @Query("SELECT * FROM DetailTable WHERE title=:Title")
    fun getData(Title: String): LiveData<Data>
}