package com.example.exploreindonesia.ui.main_ui.search.sub_ui.daerah.papua

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.exploreindonesia.R
import com.example.exploreindonesia.data.adapter.FlashcardAdapter
import com.example.exploreindonesia.data.adapter.KamusAdapter
import com.example.exploreindonesia.data.request.AddRiwayatRequest
import com.example.exploreindonesia.ui.main_ui.search.sub_ui.daerah.DaerahViewModel
import com.example.exploreindonesia.ui.main_ui.search.sub_ui.daerah.quiz.QuizActivity

class PapuaFlashcardActivity : AppCompatActivity() {

    companion object {
        const val PAPUA = "Papua"
    }

    private var lastVisibleItemId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_papua_flashcard)

        val btnQuizPapua = findViewById<Button>(R.id.btn_quiz_papua)

        val kategori = intent.getStringExtra("kategori")
        val rvPapua = findViewById<RecyclerView>(R.id.rv_papua_flashcard)
        rvPapua.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val viewModel = ViewModelProvider(this)[DaerahViewModel::class.java]

        val pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(rvPapua)

        val adapter = FlashcardAdapter()
        rvPapua.adapter = adapter

        if (kategori == "Bahasa Nusantara") {
            val rvPapua = findViewById<RecyclerView>(R.id.rv_papua_flashcard)
            rvPapua.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            viewModel.flashcards.observe(this) { flashcards ->
                rvPapua.adapter = KamusAdapter(flashcards)
            }
        }

        viewModel.flashcards.observe(this) { flashcards ->
            adapter.updateFlashcards(flashcards)
        }

        if (!isInternetAvailable()) {
            Toast.makeText(this, "Tidak ada koneksi internet", Toast.LENGTH_SHORT).show()
            return
        } else {
            Toast.makeText(this, "Data Sedang Diproses, Mohon Tunggu", Toast.LENGTH_SHORT).show()
        }


        viewModel.getFlashCards(PAPUA, kategori ?: "")

        rvPapua.addOnScrollListener(object : RecyclerView.OnScrollListener() {
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

        btnQuizPapua.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            intent.putExtra("Daerah", "Papua")
            intent.putExtra("kategori", kategori)
            startActivity(intent)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Papua"
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

    private fun isInternetAvailable(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}