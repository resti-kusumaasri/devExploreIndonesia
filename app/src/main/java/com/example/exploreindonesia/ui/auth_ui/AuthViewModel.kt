package com.example.exploreindonesia.ui.auth_ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AuthViewModel : ViewModel() {

    private val _email_login = MutableLiveData<String>()
    val email_login: LiveData<String> get() = _email_login


    private val _password_login = MutableLiveData<String>()
    val password_login: LiveData<String> get() = _password_login


    fun setEmail_login(email: String) {
        _email_login.value = email
    }


    fun setPassword_login(password: String) {
        _password_login.value = password
    }
}