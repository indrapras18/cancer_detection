package com.dicoding.asclepius.Data.Database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dicoding.asclepius.Data.Model.HistoryItem

@Dao
interface HistoryDao {
    @Insert
    fun insertHistory(historyItem: HistoryItem)

    @Query("SELECT * FROM history")
    fun getAllHistory(): List<HistoryItem>
}