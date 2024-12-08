package com.example.exploreindonesia.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class OCRResponse(

    @field:SerializedName("Hasil deteksi")
    val hasilDeteksi: String? = null,

    @field:SerializedName("Hasil translate")
    val hasilTranslate: String? = null
) : Parcelable
