package com.example.exploreindonesia.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.exploreindonesia.R
import com.example.exploreindonesia.data.model.kategori_model

class PapuaAdater(
    private val PapuaLiat : List<kategori_model>
) : RecyclerView.Adapter<PapuaAdater.PapuaViewHolder>() {
    class PapuaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView = view.findViewById<ImageView>(R.id.image_item)
        val textView = view.findViewById<TextView>(R.id.teks_item)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PapuaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_daerah_kategori, parent, false)
        return PapuaViewHolder(view)
    }

    override fun getItemCount() = PapuaLiat.size

    override fun onBindViewHolder(holder: PapuaViewHolder, position: Int) {
       val daerah = PapuaLiat[position]
        holder.imageView.setImageResource(daerah.gambar)
        holder.textView.text = daerah.nama
    }
}