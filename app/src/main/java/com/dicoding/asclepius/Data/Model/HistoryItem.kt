package com.dicoding.asclepius.Data.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class HistoryItem(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo(name = "imageUri")
    val imageUri: String,
    @ColumnInfo(name = "prediction")
    val prediction: String,
    @ColumnInfo(name = "confidenceScore")
    val confidenceScore: Float
)