package com.example.exploreindonesia.ui.main_ui.ocr

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProvider
import com.example.exploreindonesia.MainActivity
import com.example.exploreindonesia.R
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class OCRActivity : AppCompatActivity() {
    private lateinit var selectedRadioButton: RadioButton
    private lateinit var selectedText: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_ocractivity)

        var asal = findViewById<TextView>(R.id.txt_asal)
        var translasi = findViewById<TextView>(R.id.txt_translasi)

        var bahasa = ""
        var selectedImage : Uri? = null
        val btn_gallery = findViewById<Button>(R.id.btn_gallery)
        val btn_camera = findViewById<Button>(R.id.btn_camera)
        val ocrImage = findViewById<ImageView>(R.id.imageOCR)
        val btn_scan = findViewById<Button>(R.id.btn_scan)
        val viewModel = ViewModelProvider(this).get(OCRviewModel::class.java)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroupBahasa)
        var selectedRadioButtonId: Int

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            selectedRadioButtonId = checkedId

            if (selectedRadioButtonId != -1) {
                selectedRadioButton = findViewById(selectedRadioButtonId)
                selectedText = selectedRadioButton.text.toString()

                bahasa = when (selectedText) {
                    "Medan" -> "btx"
                    "Makassar" -> "mak"
                    else -> "tpi"
                }

                println("Bahasa yang dipilih: $bahasa")
            } else {

                println("Tidak ada bahasa yang dipilih")
            }
        }
        viewModel.ocrResponse.observe(this) { response ->
            asal.text = response.hasilDeteksi
            translasi.text = response.hasilTranslate
        }





        btn_scan.setOnClickListener(){
            if (selectedImage == null || bahasa.isEmpty()) {
                Toast.makeText(this, "Pilih gambar terlebih dahulu dan pilih bahasa terlebih dahulu", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Sedang melakukan OCR...", Toast.LENGTH_SHORT).show()
                btn_scan.isEnabled = false
                asal.text = ""
                translasi.text = ""
                selectedImage?.let { translateText(it, bahasa) }
                btn_scan.isEnabled = true
                selectedRadioButton.isChecked = false
                selectedRadioButton.isChecked = true
            }
        }




        val launcherIntentGallery = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                selectedImage = result.data?.data as Uri
                ocrImage.setImageURI(selectedImage)
                btn_scan.visibility = View.VISIBLE

            }
        }

        val launcherIntentCamera = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                ocrImage.setImageURI(selectedImage)
                btn_scan.visibility = View.VISIBLE
            } else {
                selectedImage = null
            }
        }



        btn_gallery.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            val chooser = Intent.createChooser(intent, "Select Image")
            launcherIntentGallery.launch(chooser)
        }

        btn_camera.setOnClickListener {
            val photoFile = createImageFile()
            photoFile?.also {
                val photoURI = FileProvider.getUriForFile(
                    this,
                    "${applicationContext.packageName}.fileprovider",
                    it
                )
                selectedImage = photoURI
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
                    putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                }
                launcherIntentCamera.launch(intent)
            }
        }
        if (!isInternetAvailable()) {
            Toast.makeText(this, "Tidak ada koneksi internet", Toast.LENGTH_SHORT).show()
        }

        supportActionBar?.apply {
            title = "OCR"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    private fun createImageFile(): File? {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return try {
            File.createTempFile(
                "JPEG_${timeStamp}_",
                ".jpg",
                storageDir
            )
        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun translateText(image: Uri, bahasa: String) {
        val viewModel = ViewModelProvider(this).get(OCRviewModel::class.java)
        val inputStream: InputStream? = contentResolver.openInputStream(image)

        val requestBody = bahasa.toRequestBody("text/plain".toMediaType())
        val requestImage = inputStream?.readBytes()?.toRequestBody("image/jpeg".toMediaType())
        val multipartBody = MultipartBody.Part.createFormData(
            "image",
            "image.jpg",
            requestImage ?: return
        )

        viewModel.translateText(multipartBody, requestBody)

        viewModel.error.observe(this) { error ->
            if (error == "timeout") {
                var i = 1
                while (i>0) {
                    Toast.makeText(this, "gagal terdeteksi", Toast.LENGTH_SHORT).show()
                    i--
                }
            }
        }
    }

    private fun isInternetAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}