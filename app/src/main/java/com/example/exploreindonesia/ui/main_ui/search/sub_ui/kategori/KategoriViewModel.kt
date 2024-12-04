package com.example.exploreindonesia.ui.main_ui.search.sub_ui.kategori

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exploreindonesia.data.request.AddRiwayatRequest
import com.example.exploreindonesia.data.response.FlashcardResponseItem
import com.example.exploreindonesia.data.retrofit.ApiConfig
import kotlinx.coroutines.launch
import retrofit2.HttpException

class KategoriViewModel : ViewModel() {

    private val _flashcards = MutableLiveData<List<FlashcardResponseItem>>()
    val flashcards: LiveData<List<FlashcardResponseItem>> = _flashcards




    fun getFlashCards(kategori: String) {
        viewModelScope.launch {
            try {
                val response1 = ApiConfig.getApiService().getFlashCards("Medan", kategori)
                val response2 = ApiConfig.getApiService().getFlashCards("Makassar", kategori)
                val response3 = ApiConfig.getApiService().getFlashCards("Papua", kategori)
                _flashcards.value = response1.plus(response2).plus(response3)
            } catch (e: HttpException) {
                Log.e("DaerahViewModel", "Error: ${e.message()}")
            } catch (e: Exception) {
                Log.e("DaerahViewModel", "Error: ${e.message}")
            }

        }
    }

    fun addRiwayat(request: AddRiwayatRequest) {
        viewModelScope.launch {
            try {
                val response = ApiConfig.getApiService().addRiwayat(request)
                Log.d("DaerahViewModel", "Response: $response")
            } catch (e: HttpException) {
                Log.e("DaerahViewModel", "Error: ${e.message()}")
            }
        }
    }
}