package com.example.exploreindonesia.data.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.exploreindonesia.R
import com.example.exploreindonesia.data.model.Kategorimodel
import com.example.exploreindonesia.ui.main_ui.search.sub_ui.daerah.papua.PapuaFlashcardActivity
import com.example.exploreindonesia.ui.main_ui.search.sub_ui.daerah.quiz.QuizActivity

class PapuaAdater(
    private val papuaList: List<Kategorimodel>
) : RecyclerView.Adapter<PapuaAdater.PapuaViewHolder>() {
    class PapuaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.image_item)
        val textView: TextView? = view.findViewById(R.id.teks_item)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PapuaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_daerah_kategori, parent, false)
        return PapuaViewHolder(view)
    }

    override fun getItemCount() = papuaList.size

    override fun onBindViewHolder(holder: PapuaViewHolder, position: Int) {
        val daerah = papuaList[position]
        holder.imageView.setImageResource(daerah.gambar)
        holder.textView?.text = daerah.nama

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, PapuaFlashcardActivity::class.java)
            intent.putExtra("kategori", daerah.nama)
            holder.itemView.context.startActivity(intent)

            if (daerah.nama == "Quiz Akhir") {
                val intent = Intent(holder.itemView.context, QuizActivity::class.java)
                intent.putExtra("Daerah", "Papua")
                intent.putExtra("a", true)
                holder.itemView.context.startActivity(intent)
            }
        }
    }
}