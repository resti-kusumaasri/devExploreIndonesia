package com.example.exploreindonesia.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.exploreindonesia.R
import com.example.exploreindonesia.data.response.FlashcardResponseItem

class FlashCardCategoryAdapter : RecyclerView.Adapter<FlashCardCategoryAdapter.FlashCardCategoryViewHolder>() {

    var flashcardsCategory: List<FlashcardResponseItem> = listOf()

    class FlashCardCategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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

    fun updateFlashcards(newFlashcards: List<FlashcardResponseItem>) {
        this.flashcardsCategory = newFlashcards
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlashCardCategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.flashcard_layout, parent, false)
        return FlashCardCategoryViewHolder(view)
    }

    override fun getItemCount() =  flashcardsCategory.size

    override fun onBindViewHolder(holder: FlashCardCategoryViewHolder, position: Int) {
        val flashcard = flashcardsCategory[position]
        holder.bind(flashcard)


    }
}