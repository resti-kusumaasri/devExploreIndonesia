package com.example.exploreindonesia.ui.main_ui.profile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exploreindonesia.data.request.EditRequest
import com.example.exploreindonesia.data.response.ResponseProfile
import com.example.exploreindonesia.data.retrofit.ApiConfig
import kotlinx.coroutines.launch
import retrofit2.HttpException

class ProfileViewModel : ViewModel() {

    private val _fullname = MutableLiveData<String>()
    val fullname: MutableLiveData<String> = _fullname

    private val _username = MutableLiveData<String>()
    val username: MutableLiveData<String> = _username

    private val _email = MutableLiveData<String>()
    val email: MutableLiveData<String> = _email

    private val _profileResult = MutableLiveData<ResponseProfile?>()
    val profileResult: MutableLiveData<ResponseProfile?> = _profileResult

    val newUsername = MutableLiveData<String>()
    val newName = MutableLiveData<String>()




    fun getProfile(
        id: String
    ) {
        viewModelScope.launch {
            try {
                val response = ApiConfig.getApiService().getProfile(id)
                _fullname.value = response.fullname
                _username.value = response.username
                _email.value = response.email
                _profileResult.value = response
            } catch (e: HttpException) {
                _profileResult.value = null
                println("HttpException: ${e.message}")
            } catch (e: NullPointerException) {
                Log.d("ProfileViewModel", "NullPointerException: ${e.message}")
            }
            catch (e: Exception) {
                _profileResult.value = null
                println("Exception: ${e.message}")
            }
        }
    }

    fun editProfile(
        id: String,
        request: EditRequest
    ) {
        viewModelScope.launch {
            try {
                val response = ApiConfig.getApiService()
                    .editProfile(id, request)
                Log.d("ProfileViewModel", "Response: $response")
                _fullname.value = response.data?.fullname.toString()
                _username.value = response.data?.fullname.toString()
            } catch (e: HttpException) {
                _profileResult.value = null

            } catch (e: Exception) {
                _profileResult.value = null
                println("Exception: ${e.message}")
            }
        }
    }
}


