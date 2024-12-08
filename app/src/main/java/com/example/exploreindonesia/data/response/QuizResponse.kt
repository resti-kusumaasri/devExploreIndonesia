package com.example.exploreindonesia.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuizResponse(

    @field:SerializedName("rightAnswer")
    val rightAnswer: String,

    @field:SerializedName("languageType")
    val languageType: String,

    @field:SerializedName("question")
    val question: String,

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("category")
    val category: String,

    @field:SerializedName("type")
    val type: String,

    @field:SerializedName("answerOptions")
    val answerOptions: List<String>
) : Parcelable
