package com.example.exploreindonesia.data.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class OCRResponse(

	@field:SerializedName("Hasil deteksi")
	val hasilDeteksi: String? = null,

	@field:SerializedName("Hasil translate")
	val hasilTranslate: String? = null
) : Parcelable
