package com.example.exploreindonesia.ui.main_ui.search.sub_ui.daerah.sumatra_utara

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.exploreindonesia.R
import com.example.exploreindonesia.data.adapter.FlashcardAdapter
import com.example.exploreindonesia.ui.main_ui.search.sub_ui.daerah.DaerahViewModel

class SumateraUtaraFlashcardActivity : AppCompatActivity() {

    companion object {
        const val Medan = "Medan"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sumatera_utara_flashcard)

        val viewModel = ViewModelProvider(this)[DaerahViewModel::class.java]

        var kategori = intent.getStringExtra("kategori")
        val rvSumatraUtara = findViewById<RecyclerView>(R.id.rv_sumatra_utara_flashcard)
        rvSumatraUtara.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(rvSumatraUtara)

        val adapter = FlashcardAdapter()
        rvSumatraUtara.adapter = adapter

        viewModel.flashcards.observe(this, Observer { flashcards ->
            adapter.updateFlashcards(flashcards)
        })

        viewModel.getFlashCards(Medan, kategori.toString())

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Sumatra Utara"
    }

}