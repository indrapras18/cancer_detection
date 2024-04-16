package com.dicoding.asclepius.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.dicoding.asclepius.Data.Model.ArticlesItem
import com.dicoding.asclepius.databinding.ListInfoBinding

class InformationAdapter(private val dataInformation : MutableList<ArticlesItem> = mutableListOf()) : RecyclerView.Adapter<InformationAdapter.InformationViewHolder>() {

    fun setUsersData(data: MutableList<ArticlesItem>) {
        this.dataInformation.clear()
        this.dataInformation.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InformationViewHolder =
        InformationViewHolder(ListInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: InformationViewHolder, position: Int) {
        val item = dataInformation[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = dataInformation.size



    class InformationViewHolder(private val binding : ListInfoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : ArticlesItem) {

            binding.circleProfile.load(item.urlToImage) {
                transformations(CircleCropTransformation())
            }
            binding.txtTittle.text = item.title
            binding.txtDescription.text = item.description

        }
    }

}