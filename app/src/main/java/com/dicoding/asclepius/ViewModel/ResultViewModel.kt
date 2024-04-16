package com.dicoding.asclepius.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.asclepius.Data.Database.HistoryRepository
import com.dicoding.asclepius.Data.Model.HistoryItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ResultViewModel(private val historyRepository: HistoryRepository) : ViewModel() {

    fun saveHistory(imageUri: String, prediction: String, confidenceScore: Float) {
        viewModelScope.launch(Dispatchers.IO) {
            val currentTime = System.currentTimeMillis()
            val historyItem = HistoryItem(
                id = currentTime,
                imageUri = imageUri,
                prediction = prediction,
                confidenceScore = confidenceScore
            )
            historyRepository.insert(historyItem)
        }
    }
}