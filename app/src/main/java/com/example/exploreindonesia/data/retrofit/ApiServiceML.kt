package com.example.exploreindonesia.data.retrofit

import com.example.exploreindonesia.data.response.OCRResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiServiceML {


    @Multipart
    @POST("api/translate")
    suspend fun translateText(
        @Part image: MultipartBody.Part,
        @Part("typeLanguage") typeLanguage: RequestBody
    ): OCRResponse

}