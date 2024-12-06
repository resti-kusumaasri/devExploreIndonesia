package com.example.exploreindonesia.ui.main_ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.exploreindonesia.MainActivity
import com.example.exploreindonesia.R

class ScoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)
        val nilai  = intent.getIntExtra("score", 0)
        val jml_soal = intent.getIntExtra("jml_soal", 0)
        val nilaiAkhir = (nilai.toDouble() / jml_soal.toDouble()) * 100
        val decimal = String.format(getString(R.string.dua_mantis), nilaiAkhir)
        val textViewScore = findViewById<TextView>(R.id.Score)
        textViewScore.text = "$decimal"

        val btnHome = findViewById<Button>(R.id.btn_home)

        btnHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}