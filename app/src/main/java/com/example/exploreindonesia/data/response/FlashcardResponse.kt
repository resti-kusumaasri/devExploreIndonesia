package com.example.exploreindonesia.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class FlashcardResponse(

    @field:SerializedName("FlashcardResponse")
    val flashcardResponse: List<FlashcardResponseItem> = listOf()
) : Parcelable

@Parcelize
data class FlashcardResponseItem(

    @field:SerializedName("languageType")
    val languageType: String? = null,

    @field:SerializedName("mediaURL")
    val mediaURL: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("category")
    val category: String? = null
) : Parcelable
