package com.dicoding.asclepius.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.asclepius.Data.Model.HistoryItem
import com.dicoding.asclepius.R

class HistoryAdapter(private val historyList: List<HistoryItem>) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_history, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = historyList[position]
        holder.tvPrediction.text = currentItem.prediction
        holder.tvConfidence.text = currentItem.confidenceScore.toString()
    }

    override fun getItemCount() = historyList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvPrediction: TextView = itemView.findViewById(R.id.tvPrediction)
        val tvConfidence: TextView = itemView.findViewById(R.id.tvConfidence)
    }
}