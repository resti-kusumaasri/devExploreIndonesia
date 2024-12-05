package com.example.exploreindonesia.data.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class RiwayatResponse(

	@field:SerializedName("languageType")
	val languageType: String,

	@field:SerializedName("category")
	val category: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("flashcardId")
	val flashcardId: String,

	@field:SerializedName("timestamp")
	val timestamp: String
) : Parcelable
