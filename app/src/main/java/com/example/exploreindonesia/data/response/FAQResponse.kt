package com.example.exploreindonesia.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class FAQResponse(

    @field:SerializedName("question")
    val question: String? = null,

    @field:SerializedName("answer")
    val answer: String? = null,

    @field:SerializedName("id")
    val id: String? = null
) : Parcelable
