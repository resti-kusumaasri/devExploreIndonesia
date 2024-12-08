package com.example.exploreindonesia.data.request

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AddRiwayatRequest(
    @field:SerializedName("userId")
    val userId: String,
    @field:SerializedName("flashcardId")
    val flashcardId: String
) : Parcelable
