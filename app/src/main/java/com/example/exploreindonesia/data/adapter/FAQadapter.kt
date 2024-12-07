package com.example.exploreindonesia.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.exploreindonesia.R
import com.example.exploreindonesia.data.response.FAQResponse

class FAQadapter (private val listFAQ: ArrayList<FAQResponse>) : RecyclerView.Adapter<FAQadapter.ListViewHolder>()  {


    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val pertanyaan = itemView.findViewById<TextView>(R.id.faq_pertanyaan)
        val jawaban = itemView.findViewById<TextView>(R.id.faq_jawaban)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.faq_item, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount() = listFAQ.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (question, answer) = listFAQ[position]
        holder.pertanyaan.text = question
        holder.jawaban.text = answer
    }
}