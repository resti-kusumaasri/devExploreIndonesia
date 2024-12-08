package com.example.exploreindonesia.ui.main_ui.search.sub_ui.daerah

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

class DaerahViewModel : ViewModel() {

    private val _flashcards = MutableLiveData<List<FlashcardResponseItem>>()
    val flashcards: LiveData<List<FlashcardResponseItem>> = _flashcards

    fun getFlashCards(daerah: String, kategori: String) {
        viewModelScope.launch {
            try {
                val response = ApiConfig.getApiService().getFlashCards(daerah, kategori)
                _flashcards.value = response
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
