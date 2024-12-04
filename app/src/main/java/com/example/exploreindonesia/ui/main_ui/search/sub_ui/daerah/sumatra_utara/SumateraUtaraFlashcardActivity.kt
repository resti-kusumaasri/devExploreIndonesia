package com.example.exploreindonesia.ui.main_ui.search.sub_ui.daerah.sumatra_utara

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.exploreindonesia.R
import com.example.exploreindonesia.data.adapter.FlashcardAdapter
import com.example.exploreindonesia.data.request.AddRiwayatRequest
import com.example.exploreindonesia.ui.main_ui.search.sub_ui.daerah.DaerahViewModel

class SumateraUtaraFlashcardActivity : AppCompatActivity() {

    companion object {
        const val Medan = "Medan"
    }

    private var lastVisibleItemId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sumatera_utara_flashcard)

        val viewModel = ViewModelProvider(this)[DaerahViewModel::class.java]

        val kategori = intent.getStringExtra("kategori")
        val rvSumatraUtara = findViewById<RecyclerView>(R.id.rv_sumatra_utara_flashcard)
        rvSumatraUtara.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(rvSumatraUtara)

        val adapter = FlashcardAdapter()
        rvSumatraUtara.adapter = adapter



        viewModel.flashcards.observe(this, Observer { flashcards ->
            Toast.makeText(this, "Data Sedang Diproses, Mohon Tunggu Sebentar Setelah Data diambil", Toast.LENGTH_SHORT).show()
            adapter.updateFlashcards(flashcards)
        })


        viewModel.getFlashCards(Medan, kategori ?: "")


        rvSumatraUtara.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()

                if (lastVisibleItemPosition != RecyclerView.NO_POSITION) {
                    val lastVisibleItem = adapter.flashcards[lastVisibleItemPosition]
                    lastVisibleItemId = lastVisibleItem.id
                }
            }
        })

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Sumatra Utara"
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            val akunSharedPreferences = getSharedPreferences("akun", MODE_PRIVATE)
            val userId = akunSharedPreferences.getString("userId", null).toString()
            val viewModel = ViewModelProvider(this)[DaerahViewModel::class.java]

            lastVisibleItemId?.let { id ->
                val request = AddRiwayatRequest(userId, id)
                viewModel.addRiwayat(request)
            }
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
