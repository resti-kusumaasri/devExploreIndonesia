package com.example.exploreindonesia.data.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class Response(

	@field:SerializedName("message")
	val message: String? = null
) : Parcelable
