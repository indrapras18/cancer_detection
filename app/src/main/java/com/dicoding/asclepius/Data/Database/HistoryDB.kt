package com.dicoding.asclepius.Data.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dicoding.asclepius.Data.Model.HistoryItem

@Database(entities = [HistoryItem::class], version = 1, exportSchema = false)
abstract class HistoryDB : RoomDatabase() {
    abstract fun historyDao(): HistoryDao
}