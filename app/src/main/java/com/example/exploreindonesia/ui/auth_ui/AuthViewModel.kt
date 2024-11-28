package com.example.exploreindonesia.ui.auth_ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exploreindonesia.data.request.LoginRequest
import com.example.exploreindonesia.data.request.RegisterRequest
import com.example.exploreindonesia.data.response.RegisterResponse
import com.example.exploreindonesia.data.retrofit.ApiConfig
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class AuthViewModel : ViewModel() {

    private val _registerResult = MutableLiveData<String?>()
    val registerResult: MutableLiveData<String?> get() = _registerResult

    fun registerUser(
        request: RegisterRequest
    ) {
        viewModelScope.launch {
            try {
                val response = ApiConfig.getApiService().registerUser(request)
                _registerResult.value = response.message
            } catch (e: HttpException) {
                val erroBody = e.response()?.errorBody()?.string()
                val errorRegisterResponse = Gson().fromJson(erroBody, RegisterResponse::class.java)
                _registerResult.value = errorRegisterResponse.error
            } catch (e: Exception) {
                _registerResult.value = e.message
            }
        }
    }

    fun loginUser(
        request: LoginRequest
    ) {
        viewModelScope.launch {
            try {
                val response = ApiConfig.getApiService().loginUser(request)
                _registerResult.value = response.userId
            } catch (e: HttpException) {
                val erroBody = e.response()?.errorBody()?.string()
                val errorRegisterResponse = Gson().fromJson(erroBody, RegisterResponse::class.java)
                _registerResult.value = errorRegisterResponse.error
            } catch (e: Exception) {
                _registerResult.value = e.message
            }
        }
    }
}