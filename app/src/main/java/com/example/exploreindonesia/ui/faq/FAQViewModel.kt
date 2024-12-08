package com.example.exploreindonesia.ui.faq

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exploreindonesia.data.response.FAQResponse
import com.example.exploreindonesia.data.retrofit.ApiConfig
import kotlinx.coroutines.launch
import retrofit2.HttpException

class FAQViewModel : ViewModel() {

    private val _listFAQ = MutableLiveData<List<FAQResponse>>()
    val listFAQ: LiveData<List<FAQResponse>> = _listFAQ

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error


    fun fetchFAQ() {
        viewModelScope.launch {
            try {
                val response = ApiConfig.getApiService().getFAQ()
                _listFAQ.value = response
            } catch (e: HttpException) {
                _error.value = e.message()
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }
}