package com.example.exploreindonesia.data.adapter

import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.exploreindonesia.R
import com.example.exploreindonesia.data.response.FlashcardResponse
import com.example.exploreindonesia.data.response.FlashcardResponseItem


class FlashcardAdapter : RecyclerView.Adapter<FlashcardAdapter.FlashcardViewHolder>() {

    // Menggunakan mutable list yang dapat diubah
    private var flashcards: List<FlashcardResponseItem> = listOf()

    // Method untuk memperbarui data flashcards
    fun updateFlashcards(newFlashcards: List<FlashcardResponseItem>) {
        this.flashcards = newFlashcards
        notifyDataSetChanged()  // Memberitahukan adapter untuk memperbarui data
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
        private val idTextView: TextView = itemView.findViewById(R.id.id_flashcard)

        fun bind(flashcard: FlashcardResponseItem) {
            KategoriTextView.text = flashcard.category
            titleTextView.text = flashcard.title
            descriptionTextView.text = flashcard.description
            areaTextView.text = flashcard.category
            idTextView.text = flashcard.id

            flashcard.mediaURL?.let {
                Glide.with(itemView.context)
                    .load(it)
                    .placeholder(R.drawable.baseline_image_24)
                    .error(R.drawable.baseline_image_24)
                    .into(imageView)
            }
        }
    }
}
