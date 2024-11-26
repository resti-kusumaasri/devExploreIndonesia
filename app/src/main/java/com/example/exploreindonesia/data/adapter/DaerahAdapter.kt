package com.example.exploreindonesia.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.exploreindonesia.R

class DaerahAdapter(private var list: MutableList<Int>) : RecyclerView.Adapter<DaerahAdapter.DaerahViewholder>() {

    class DaerahViewholder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.image_item_daerah)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DaerahViewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_daerah, parent, false)
        return DaerahViewholder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: DaerahViewholder, position: Int) {
        holder.imageView.setImageResource(list[position])
    }

    fun updateData(newList: List<Int>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}
