package com.example.exploreindonesia.data.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class FAQResponse(

	@field:SerializedName("question")
	val question: String? = null,

	@field:SerializedName("answer")
	val answer: String? = null,

	@field:SerializedName("id")
	val id: String? = null
) : Parcelable
