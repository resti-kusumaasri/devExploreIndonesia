package com.example.exploreindonesia.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.exploreindonesia.R
import com.example.exploreindonesia.data.model.histori_model

class HistoriAdapter(private val historiList: List<histori_model>) :
    RecyclerView.Adapter<HistoriAdapter.HistoriViewHolder>() {
    class HistoriViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val activityName: TextView = itemView.findViewById(R.id.nama_item_histori)
        val timestamp: TextView = itemView.findViewById(R.id.waktu_item_hostori)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoriViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_histori, parent, false)
        return HistoriViewHolder(view)
    }

    override fun getItemCount() = historiList.size

    override fun onBindViewHolder(holder: HistoriViewHolder, position: Int) {
        val histori = historiList[position]
        holder.activityName.text = histori.activityName
        holder.timestamp.text = histori.timestamp
    }
}