package com.example.exploreindonesia.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.exploreindonesia.R
import com.example.exploreindonesia.data.request.AddRiwayatRequest
import com.example.exploreindonesia.data.response.FlashcardResponseItem
import com.example.exploreindonesia.ui.main_ui.search.sub_ui.daerah.DaerahViewModel


class FlashcardAdapter(
) : RecyclerView.Adapter<FlashcardAdapter.FlashcardViewHolder>() {

    var flashcards: List<FlashcardResponseItem> = listOf()

    fun updateFlashcards(newFlashcards: List<FlashcardResponseItem>) {
        this.flashcards = newFlashcards
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlashcardViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.flashcard_layout, parent, false)
        return FlashcardViewHolder(view)
    }

    override fun onBindViewHolder(holder: FlashcardViewHolder, position: Int) {
        val flashcard = flashcards[position]
        holder.bind(flashcard)
    }

    override fun getItemCount(): Int = flashcards.size

    inner class FlashcardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val KategoriTextView: TextView = itemView.findViewById(R.id.kategori_flashcard)
        private val imageView: ImageView = itemView.findViewById(R.id.image_flashcard)
        private val titleTextView: TextView = itemView.findViewById(R.id.title_flashcard)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.description_flashcard)
        private val areaTextView: TextView = itemView.findViewById(R.id.daerah)

        fun bind(flashcard: FlashcardResponseItem) {
            KategoriTextView.text = flashcard.category
            titleTextView.text = flashcard.title
            descriptionTextView.text = flashcard.description
            areaTextView.text = flashcard.languageType

            flashcard.mediaURL?.let {
                if (it.isNotEmpty()) {
                    Glide.with(itemView.context)
                        .load(it)
                        .placeholder(R.drawable.baseline_image_24)
                        .error(R.drawable.baseline_image_24)
                        .centerCrop()
                        .into(imageView)
                }
                else {
                    imageView.setImageResource(R.drawable.baseline_image_24)
                }
            }
        }
    }
}
