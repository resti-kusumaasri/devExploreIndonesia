package com.example.exploreindonesia.data.retrofit

import com.example.exploreindonesia.data.request.LoginRequest
import com.example.exploreindonesia.data.request.RegisterRequest
import com.example.exploreindonesia.data.response.LoginResponse
import com.example.exploreindonesia.data.response.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
        @POST("api/auth/register")
        suspend fun registerUser(
            @Body request: RegisterRequest
        ): RegisterResponse

        @POST("api/auth/login")
        suspend fun loginUser(
            @Body request: LoginRequest
        ): LoginResponse

}