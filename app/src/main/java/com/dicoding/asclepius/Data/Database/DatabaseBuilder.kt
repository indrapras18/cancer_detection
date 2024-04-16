package com.dicoding.asclepius.Data.Database

import android.content.Context
import androidx.room.Room

object DatabaseBuilder {

    @Volatile
    private var INSTANCE: HistoryDB? = null

    fun getDatabase(context: Context): HistoryDB {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                HistoryDB::class.java,
                "history_db"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}