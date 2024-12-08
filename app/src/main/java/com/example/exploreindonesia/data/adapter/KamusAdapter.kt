package com.example.exploreindonesia.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.exploreindonesia.R
import com.example.exploreindonesia.data.response.FlashcardResponseItem

class KamusAdapter(private val kamusList: List<FlashcardResponseItem>) :
    RecyclerView.Adapter<KamusAdapter.KamusViewHolder>() {
    class KamusViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bahasaDaerah: TextView = itemView.findViewById(R.id.tv_daerah)
        val bahasaIndo: TextView = itemView.findViewById(R.id.tv_indo)
        val daerahbahasa: TextView = itemView.findViewById(R.id.kamus_daerah)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KamusViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_bahasa, parent, false)
        return KamusViewHolder(view)
    }

    override fun getItemCount() = kamusList.size

    override fun onBindViewHolder(holder: KamusViewHolder, position: Int) {
        val kamus = kamusList[position]
        holder.bahasaDaerah.text = kamus.title
        holder.bahasaIndo.text = kamus.description
        holder.daerahbahasa.text = kamus.languageType
    }

}