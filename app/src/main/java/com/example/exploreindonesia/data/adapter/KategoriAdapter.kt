package com.example.exploreindonesia.data.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.exploreindonesia.R
import com.example.exploreindonesia.data.model.kategori_model
import com.example.exploreindonesia.ui.main_ui.search.sub_ui.kategori.KategoriFlashCardActivity

class KategoriAdapter(private var list: List<kategori_model>) :
    RecyclerView.Adapter<KategoriAdapter.KategoriViewholder>(),
    Filterable {

    private var listFull: List<kategori_model> = list

    class KategoriViewholder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.image_item)
        val textView: TextView = view.findViewById(R.id.teks_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KategoriViewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_daerah, parent, false)
        return KategoriViewholder(view)
    }


    override fun getItemCount() = list.size


    override fun onBindViewHolder(holder: KategoriViewholder, position: Int) {
        val daerah = list[position]
        holder.imageView.setImageResource(daerah.gambar)
        holder.textView.text = daerah.nama

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, KategoriFlashCardActivity::class.java)
            intent.putExtra("kategori", daerah.nama)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredList: List<kategori_model> = if (constraint.isNullOrEmpty()) {
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
                list = results?.values as List<kategori_model>
                notifyDataSetChanged()
            }
        }
    }
}
