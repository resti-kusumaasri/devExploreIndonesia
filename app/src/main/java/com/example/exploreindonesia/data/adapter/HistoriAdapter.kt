package com.example.exploreindonesia.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.exploreindonesia.R
import com.example.exploreindonesia.data.response.RiwayatResponse

class HistoriAdapter(private val historiList: RiwayatResponse) :
    RecyclerView.Adapter<HistoriAdapter.HistoriViewHolder>() {
    class HistoriViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val activityName: TextView = itemView.findViewById(R.id.nama_item_histori)
        val timestamp: TextView = itemView.findViewById(R.id.waktu_item_hostori)
        val location: TextView = itemView.findViewById(R.id.daerah_item_histori)
        val category: TextView = itemView.findViewById(R.id.kategori_flashcard)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoriViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_histori, parent, false)
        return HistoriViewHolder(view)
    }

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: HistoriViewHolder, position: Int) {
        val histori = historiList
        holder.activityName.text = histori.title
        holder.timestamp.text = histori.timestamp
        holder.location.text = histori.languageType
        holder.category.text = histori.category


    }
}