package com.example.exploreindonesia.data.retrofit

import com.example.exploreindonesia.data.request.AddRiwayatRequest
import com.example.exploreindonesia.data.request.EditRequest
import com.example.exploreindonesia.data.request.LoginRequest
import com.example.exploreindonesia.data.request.RegisterRequest
import com.example.exploreindonesia.data.response.AddRiwayatResponse
import com.example.exploreindonesia.data.response.EditResponse
import com.example.exploreindonesia.data.response.FAQResponse
import com.example.exploreindonesia.data.response.FlashcardResponseItem
import com.example.exploreindonesia.data.response.LoginResponse
import com.example.exploreindonesia.data.response.QuizResponse
import com.example.exploreindonesia.data.response.RegisterResponse
import com.example.exploreindonesia.data.response.ResponseProfile
import com.example.exploreindonesia.data.response.RiwayatResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @POST("api/auth/register")
    suspend fun registerUser(
        @Body request: RegisterRequest
    ): RegisterResponse

    @POST("api/auth/login")
    suspend fun loginUser(
        @Body request: LoginRequest
    ): LoginResponse

    @GET("api/profile/{id}")
    suspend fun getProfile(
        @Path("id") id: String
    ): ResponseProfile


    @PUT("api/profile/edit/{id}")
    suspend fun editProfile(
        @Path("id") id: String,
        @Body request: EditRequest
    ): EditResponse

    @GET("api/flashcards/{daerah}/{kategori}")
    suspend fun getFlashCards(
        @Path("daerah") daerah: String,
        @Path("kategori") kategori: String
    ): List<FlashcardResponseItem>

    @POST("api/riwayat")
    suspend fun addRiwayat(
        @Body request: AddRiwayatRequest
    ): AddRiwayatResponse


    @GET("api/quiz/latihan/{daerah}/{kategori}")
    suspend fun getQuiz(
        @Path("daerah") daerah: String,
        @Path("kategori") kategori: String
    ): List<QuizResponse>

    @GET("api/riwayat/{id}")
    suspend fun getRiwayat(
        @Path("id") id: String
    ): RiwayatResponse

    @GET("api/quiz/quizAkhir/{daerah}")
    suspend fun getQuizAkhir(
        @Path("daerah") daerah: String
    ): List<QuizResponse>

    @GET("api/FAQ")
    suspend fun getFAQ(): List<FAQResponse>


}