package com.example.exploreindonesia.ui.auth_ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AuthViewModel : ViewModel() {

    private val _registerResult = MutableLiveData<String>()
    val registerResult: LiveData<String> get() = _registerResult

    fun registerAccount(
        fullname: String,
        username: String,
        email: String,
        password: String,
        confirmPassword: String
    ) {
        // Validasi sederhana
        if (fullname.isBlank() || username.isBlank() || email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
            _registerResult.value = "All fields must be filled"
            return
        }
        if (password != confirmPassword) {
            _registerResult.value = "Password does not match"
            return
        }

        // Proses registrasi (bisa ditambahkan logika API atau database di sini)
        // Misalnya simulasi registrasi berhasil:
        _registerResult.value = "Registration successful for user $username"
    }
}