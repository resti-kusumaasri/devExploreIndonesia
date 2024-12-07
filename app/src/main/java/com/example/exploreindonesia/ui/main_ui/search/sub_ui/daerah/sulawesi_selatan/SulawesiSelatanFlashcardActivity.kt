package com.example.exploreindonesia.ui.main_ui.search.sub_ui.daerah.sulawesi_selatan

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.exploreindonesia.R
import com.example.exploreindonesia.data.adapter.FlashcardAdapter
import com.example.exploreindonesia.data.adapter.KamusAdapter
import com.example.exploreindonesia.data.request.AddRiwayatRequest
import com.example.exploreindonesia.ui.main_ui.search.sub_ui.daerah.DaerahViewModel
import com.example.exploreindonesia.ui.main_ui.search.sub_ui.daerah.Quiz.QuizActivity
import com.example.exploreindonesia.ui.main_ui.search.sub_ui.daerah.sumatra_utara.SumateraUtaraFlashcardActivity.Companion.Medan

class SulawesiSelatanFlashcardActivity : AppCompatActivity() {

    private var lastVisibleItemId: String? = null

    companion object {
        const val Makassar = "Makassar"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
   
        setContentView(R.layout.activity_sulawesi_selatan_flashcard)
        val quizButton = findViewById<Button>(R.id.btn_quiz_sulawesi_selatan)

        val viewModel = ViewModelProvider(this)[DaerahViewModel::class.java]

        val kategori = intent.getStringExtra("kategori")
        val rvSulawesiSelatan = findViewById<RecyclerView>(R.id.rv_sulawesi_selatan_flashcard)
        rvSulawesiSelatan.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(rvSulawesiSelatan)

        val adapter = FlashcardAdapter()
        rvSulawesiSelatan.adapter = adapter

        if (kategori == "Bahasa Nusantara") {
            val rvSulawesiSelatan = findViewById<RecyclerView>(R.id.rv_sulawesi_selatan_flashcard)
            rvSulawesiSelatan.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            viewModel.flashcards.observe(this, Observer { flashcards ->
                rvSulawesiSelatan.adapter = KamusAdapter(flashcards)
            })
        }

        viewModel.flashcards.observe(this, Observer { flashcards ->

            adapter.updateFlashcards(flashcards)
        })

        if (!isInternetAvailable()) {
            Toast.makeText(this, "Tidak ada koneksi internet", Toast.LENGTH_SHORT).show()
            return
        }else {
            Toast.makeText(this, "Data Sedang Diproses, Mohon Tunggu", Toast.LENGTH_SHORT).show()
        }


        viewModel.getFlashCards(Makassar, kategori ?: "")

        rvSulawesiSelatan.addOnScrollListener(object : RecyclerView.OnScrollListener() {
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

        quizButton.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            intent.putExtra("Daerah", "Makassar")
            intent.putExtra("kategori", kategori)
            startActivity(intent)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Sulawesi Selatan"
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
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
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