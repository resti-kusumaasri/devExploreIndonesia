package com.example.exploreindonesia.data.request

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class EditRequest(

    @field:SerializedName("fullname")
    val fullname: String? = null,

    @field:SerializedName("username")
    val username: String? = null
) : Parcelable
