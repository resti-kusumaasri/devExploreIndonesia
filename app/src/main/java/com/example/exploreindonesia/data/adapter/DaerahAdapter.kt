package com.example.exploreindonesia.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.exploreindonesia.R
import com.example.exploreindonesia.data.model.daerah_model

class DaerahAdapter(private var list: List<daerah_model>) : RecyclerView.Adapter<DaerahAdapter.DaerahViewholder>(),
    Filterable {

    private var listFull: List<daerah_model> = list

    class DaerahViewholder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.image_item)
        val textView: TextView = view.findViewById(R.id.teks_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DaerahViewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_daerah, parent, false)
        return DaerahViewholder(view)
    }


    override fun getItemCount() = list.size


    override fun onBindViewHolder(holder: DaerahViewholder, position: Int) {
        val daerah = list[position]
        holder.imageView.setImageResource(daerah.gambar)
        holder.textView.text = daerah.nama
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredList: List<daerah_model> = if (constraint.isNullOrEmpty()) {
                    listFull
                } else {
                    listFull.filter {
                        it.nama.contains(constraint, ignoreCase = true)
                    }
                }

                val results = FilterResults()
                results.values = filteredList
                return results
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                list = results?.values as List<daerah_model>
                notifyDataSetChanged()
            }
        }
    }
}
