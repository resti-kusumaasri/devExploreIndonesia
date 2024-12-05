package com.example.exploreindonesia.ui.main_ui.search.sub_ui.kategori

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.MenuItem
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
import com.example.exploreindonesia.R
import com.example.exploreindonesia.data.adapter.FlashCardCategoryAdapter
import com.example.exploreindonesia.data.adapter.FlashcardAdapter
import com.example.exploreindonesia.data.request.AddRiwayatRequest
import com.example.exploreindonesia.ui.main_ui.search.sub_ui.daerah.DaerahViewModel

class KategoriFlashCardActivity : AppCompatActivity() {

    private var lastVisibleItemId: String? = null


 override fun onCreate(savedInstanceState: Bundle?) {
     super.onCreate(savedInstanceState)
     setContentView(R.layout.activity_kategori_flash_card)


     val viewModel = ViewModelProvider(this)[KategoriViewModel::class.java]

     val kategori = intent.getStringExtra("kategori").toString()
     val rvKategori = findViewById<RecyclerView>(R.id.rv_kategori_flashcard)

     rvKategori.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
     val pagerSnapHelper = PagerSnapHelper()
     pagerSnapHelper.attachToRecyclerView(rvKategori)

     val adapter = FlashCardCategoryAdapter()
     rvKategori.adapter = adapter

     viewModel.flashcards.observe(this, Observer { flashcards ->
         adapter.updateFlashcards(flashcards)
     })

     if (!isInternetAvailable()) {
         Toast.makeText(this, "Tidak ada koneksi internet", Toast.LENGTH_SHORT).show()
         return
     }else {
         Toast.makeText(this, "Data Sedang Diproses, Mohon Tunggu", Toast.LENGTH_SHORT).show()
     }
     viewModel.getFlashCards(kategori)

     supportActionBar?.setDisplayHomeAsUpEnabled(true)
     supportActionBar?.setDisplayShowHomeEnabled(true)
     supportActionBar?.title = "Kategori"

     rvKategori.addOnScrollListener(object : RecyclerView.OnScrollListener() {
         override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
             super.onScrolled(recyclerView, dx, dy)

             super.onScrolled(recyclerView, dx, dy)
             val layoutManager = recyclerView.layoutManager as LinearLayoutManager
             val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()

             if (lastVisibleItemPosition != RecyclerView.NO_POSITION) {
                 val lastVisibleItem = adapter.flashcardsCategory[lastVisibleItemPosition]
                 lastVisibleItemId = lastVisibleItem.id
             }
         }
     })
 }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            val akunSharedPreferences = getSharedPreferences("akun", MODE_PRIVATE)
            val userId = akunSharedPreferences.getString("userId", null).toString()
            val viewModel = ViewModelProvider(this)[KategoriViewModel::class.java]

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