package com.example.exploreindonesia.data.request

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginRequest(
    @field:SerializedName("email")
    val email: String,
    val password: String,
) : Parcelable
