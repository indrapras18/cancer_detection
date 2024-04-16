package com.dicoding.asclepius.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.asclepius.Adapter.InformationAdapter
import com.dicoding.asclepius.Data.Model.ArticlesItem
import com.dicoding.asclepius.R
import com.dicoding.asclepius.Utils.ResultUser
import com.dicoding.asclepius.ViewModel.HomeViewModel
import com.dicoding.asclepius.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val homeViewModel by viewModels<HomeViewModel>()
    private val adapter by lazy {
        InformationAdapter()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val button = findViewById<Button>(R.id.btn_diagnose)
        val btnHistory = findViewById<Button>(R.id.btn_history)

        button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btnHistory.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }

        binding.recyclerInformation.layoutManager = LinearLayoutManager(this)
        binding.recyclerInformation.setHasFixedSize(true)
        binding.recyclerInformation.adapter = adapter

        homeViewModel.resultInformationListItem.observe(this) {
            when (it) {
                is ResultUser.Success<*> -> {
                    adapter.setUsersData(it.data as MutableList<ArticlesItem>)
                }
                is ResultUser.Error -> {
                    Toast.makeText(this, it.exception.message.toString(), Toast.LENGTH_SHORT).show()

                }
                is ResultUser.Loading -> {
                    binding.progressInformation.isVisible = it.isLoading

                }
            }
        }
        homeViewModel.getListInformation()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding!= null
    }
}