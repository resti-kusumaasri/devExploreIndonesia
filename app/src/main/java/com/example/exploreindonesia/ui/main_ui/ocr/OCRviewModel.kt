package com.example.exploreindonesia.ui.main_ui.ocr

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exploreindonesia.data.response.OCRResponse
import com.example.exploreindonesia.data.retrofit.ApiConfigML
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.HttpException

class OCRviewModel : ViewModel() {

    private val _ocrResponse = MutableLiveData<OCRResponse>()
    val ocrResponse: LiveData<OCRResponse> = _ocrResponse


    private  val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error


    fun translateText(image: MultipartBody.Part, bahasa: RequestBody) {
        viewModelScope.launch {
            try {
                val response = ApiConfigML.getApiserviceML().translateText(image, bahasa)
                _ocrResponse.value = response
            } catch (e: HttpException) {
                Log.e("OCRviewModel", "onFailure: ${e.message.toString()}")
                _error.value = e.message.toString()
            } catch (e: NullPointerException) {
                Log.e("OCRviewModel", "onFailure: ${e.message.toString()}")
                _error.value = e.message.toString()
            } catch (e: Exception) {
                Log.e("OCRviewModel", "onFailure: ${e.message.toString()}")
                _error.value = e.message.toString()
            }
        }
    }
}