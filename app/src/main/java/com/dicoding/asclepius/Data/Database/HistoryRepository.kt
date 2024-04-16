package com.dicoding.asclepius.Data.Database

import androidx.annotation.WorkerThread
import com.dicoding.asclepius.Data.Model.HistoryItem

class HistoryRepository(private val historyDao: HistoryDao) {

    @WorkerThread
    fun insert(historyItem: HistoryItem) {
        historyDao.insertHistory(historyItem)
    }

    fun getAllHistory(): List<HistoryItem> {
        return historyDao.getAllHistory()
    }
}