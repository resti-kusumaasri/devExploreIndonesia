package com.example.exploreindonesia.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Historimodel(

    val activityName: String,
    val timestamp: String

) : Parcelable
