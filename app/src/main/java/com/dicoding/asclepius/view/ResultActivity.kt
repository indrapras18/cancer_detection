package com.dicoding.asclepius.view

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.dicoding.asclepius.Data.Database.DatabaseBuilder
import com.dicoding.asclepius.Data.Database.HistoryRepository
import com.dicoding.asclepius.ViewModel.ResultViewModel
import com.dicoding.asclepius.ViewModel.ViewModelFactory
import com.dicoding.asclepius.R
import com.dicoding.asclepius.databinding.ActivityResultBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    private lateinit var currentImageUri: Uri
    private lateinit var prediction: String
    private var confidenceScore: Float = 0f

    private val viewModel: ResultViewModel by viewModels {
        ViewModelFactory(HistoryRepository(DatabaseBuilder.getDatabase(this).historyDao()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        currentImageUri = Uri.parse(intent.getStringExtra(EXTRA_IMAGE_URI))
        prediction = intent.getStringExtra(EXTRA_PREDICTION) ?: ""
        confidenceScore = intent.getFloatExtra(EXTRA_CONFIDENCE, 0f)

        binding.resultImage.setImageURI(currentImageUri)

        val resultText = getString(R.string.result_text, prediction, confidenceScore)
        binding.resultText.text = resultText

        binding.btnSave.setOnClickListener {
            saveHistory()
        }
    }

    private fun saveHistory() {
        GlobalScope.launch(Dispatchers.IO) {
            viewModel.saveHistory(currentImageUri.toString(), prediction, confidenceScore)
            runOnUiThread {
                showToast("History saved")
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val EXTRA_IMAGE_URI = "extra_image_uri"
        const val EXTRA_PREDICTION = "extra_prediction"
        const val EXTRA_CONFIDENCE = "extra_confidence"
    }
}