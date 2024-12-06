package com.example.exploreindonesia.data.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.exploreindonesia.R
import com.example.exploreindonesia.data.model.kategori_model
import com.example.exploreindonesia.ui.main_ui.search.sub_ui.daerah.Quiz.QuizActivity
import com.example.exploreindonesia.ui.main_ui.search.sub_ui.daerah.sumatra_utara.SumateraUtaraFlashcardActivity

class SumatraUtaraAdapter(
    private val SumatraUtaraList: List<kategori_model>
) : RecyclerView.Adapter<SumatraUtaraAdapter.SumatraUtaraViewHolder>() {

    class SumatraUtaraViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.image_item)
        val textView: TextView = view.findViewById(R.id.teks_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SumatraUtaraViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_daerah_kategori, parent, false)
        return SumatraUtaraViewHolder(view)
    }

    override fun getItemCount() = SumatraUtaraList.size

    override fun onBindViewHolder(holder: SumatraUtaraViewHolder, position: Int) {

        val daerah = SumatraUtaraList[position]
        holder.imageView.setImageResource(daerah.gambar)
        holder.textView.text = daerah.nama

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, SumateraUtaraFlashcardActivity::class.java)
            intent.putExtra("kategori", daerah.nama)
            holder.itemView.context.startActivity(intent)

            if (daerah.nama == "Quiz Akhir") {
                val intent = Intent(holder.itemView.context, QuizActivity::class.java)
                intent.putExtra("Daerah", "Medan")
                intent.putExtra("a", true)
                holder.itemView.context.startActivity(intent)
            }
        }
    }
}