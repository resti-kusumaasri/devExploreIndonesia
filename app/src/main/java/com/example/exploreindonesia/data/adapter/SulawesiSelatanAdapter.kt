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
import com.example.exploreindonesia.ui.main_ui.search.sub_ui.daerah.sulawesi_selatan.SulawesiSelatanFlashcardActivity

class SulawesiSelatanAdapter(
    private val SulawesiSelatanlist : List<kategori_model>
) : RecyclerView.Adapter<SulawesiSelatanAdapter.SulawesiSelatanViwholder>()  {

    class SulawesiSelatanViwholder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView = view.findViewById<ImageView>(R.id.image_item)
        val textView = view.findViewById<TextView>(R.id.teks_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SulawesiSelatanViwholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_daerah_kategori, parent, false)
        return SulawesiSelatanViwholder(view)
    }

    override fun getItemCount() = SulawesiSelatanlist.size

    override fun onBindViewHolder(holder: SulawesiSelatanViwholder, position: Int) {

        val daerah = SulawesiSelatanlist[position]
        holder.imageView.setImageResource(daerah.gambar)
        holder.textView.text = daerah.nama

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, SulawesiSelatanFlashcardActivity::class.java)
            intent.putExtra("kategori", daerah.nama)
            holder.itemView.context.startActivity(intent)

            if (daerah.nama == "Quiz Akhir") {
                val intent = Intent(holder.itemView.context, QuizActivity::class.java)
                intent.putExtra("Daerah", "Makassar")
                intent.putExtra("a", true)
                holder.itemView.context.startActivity(intent)
            }
        }
    }
}