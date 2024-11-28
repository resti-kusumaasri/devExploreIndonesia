package com.example.exploreindonesia.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class daerah_model(
    val gambar: Int,
    val nama: String
) : Parcelable
