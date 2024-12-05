package com.example.exploreindonesia.ui.main_ui.Histori

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exploreindonesia.data.response.RiwayatResponse
import com.example.exploreindonesia.data.retrofit.ApiConfig
import kotlinx.coroutines.launch
import retrofit2.HttpException

class HistoriViewModel : ViewModel() {



    private val _historiList = MutableLiveData<RiwayatResponse>()
    val historiList: LiveData<RiwayatResponse> get() = _historiList

    private val errorMessage = MutableLiveData<String>()
    val error: LiveData<String> get() = errorMessage





    fun getRiwayat(id: String) {
        viewModelScope.launch {
            try {
                val response = ApiConfig.getApiService().getRiwayat(id)
                _historiList.postValue(response)
            } catch (e: HttpException) {
                Log.e("error", e.message.toString())
                if (e.code() == 500) {
                    errorMessage.postValue("Tidak ada riwayat")
                }
            } catch (e: NullPointerException) {
                Log.e("error", e.message.toString())
            }
            catch (e: Exception) {
                Log.e("error", e.message.toString())
            }
        }
    }
}