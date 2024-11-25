package com.example.exploreindonesia.data.request

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class RegisterRequest(
	@field:SerializedName("fullname")
	val fullname: String,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("username")
	val username: String,

	@field:SerializedName("password")
	val password: String,

	@field:SerializedName("confirmPassword")
	val confirmPassword: String
) : Parcelable

