package com.example.exploreindonesia.data.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class EditResponse(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null
) : Parcelable


@Parcelize
data class Data(

	@field:SerializedName("created")
	val created: Created? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("fullname")
	val fullname: String? = null,

	@field:SerializedName("lastSignedIn")
	val lastSignedIn: LastSignedIn? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null,

	@field:SerializedName("username")
	val username: String? = null
) : Parcelable
