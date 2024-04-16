package com.dicoding.asclepius.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.asclepius.Adapter.HistoryAdapter
import com.dicoding.asclepius.Data.Database.DatabaseBuilder
import com.dicoding.asclepius.Data.Database.HistoryRepository
import com.dicoding.asclepius.Data.Model.HistoryItem
import com.dicoding.asclepius.databinding.ActivityHistoryBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding
    private lateinit var historyAdapter: HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadHistory()
    }

    private fun loadHistory() {
        CoroutineScope(Dispatchers.IO).launch {
            val historyRepository = HistoryRepository(DatabaseBuilder.getDatabase(application).historyDao())
            val historyList = historyRepository.getAllHistory()

            withContext(Dispatchers.Main) {
                setUpRecyclerView(historyList)
            }
        }
    }

    private fun setUpRecyclerView(historyList: List<HistoryItem>) {
        historyAdapter = HistoryAdapter(historyList)
        binding.recyclerViewHistory.apply {
            adapter = historyAdapter
            layoutManager = LinearLayoutManager(this@HistoryActivity)
            setHasFixedSize(true)
        }
    }
}