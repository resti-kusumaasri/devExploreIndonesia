package com.example.exploreindonesia.data.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class ResponseProfile(

	@field:SerializedName("profileURL")
	val profileURL: String,

	@field:SerializedName("created")
	val created: Created,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("fullname")
	val fullname: String,

	@field:SerializedName("lastSignedIn")
	val lastSignedIn: LastSignedIn,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("updatedAt")
	val updatedAt: String,

	@field:SerializedName("username")
	val username: String
) : Parcelable

@Parcelize
data class Created(

	@field:SerializedName("_nanoseconds")
	val nanoseconds: Int,

	@field:SerializedName("_seconds")
	val seconds: Int
) : Parcelable

@Parcelize
data class LastSignedIn(

	@field:SerializedName("_nanoseconds")
	val nanoseconds: Int,

	@field:SerializedName("_seconds")
	val seconds: Int
) : Parcelable
