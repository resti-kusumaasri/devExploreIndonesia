package com.example.exploreindonesia.data.request

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class AddRiwayatRequest(
	@field:SerializedName("userId")
	val userId: String,
	@field:SerializedName("flashcardId")
	val flashcardId: String
) : Parcelable
