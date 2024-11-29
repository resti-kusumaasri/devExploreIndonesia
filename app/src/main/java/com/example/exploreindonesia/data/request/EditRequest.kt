package com.example.exploreindonesia.data.request

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class EditRequest(

	@field:SerializedName("fullname")
	val fullname: String? = null,

	@field:SerializedName("username")
	val username: String? = null
) : Parcelable
