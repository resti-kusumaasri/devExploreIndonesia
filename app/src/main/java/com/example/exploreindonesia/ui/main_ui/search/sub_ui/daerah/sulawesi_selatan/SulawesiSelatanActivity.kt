package com.example.exploreindonesia.ui.main_ui.search.sub_ui.daerah.sulawesi_selatan

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.exploreindonesia.MainActivity
import com.example.exploreindonesia.R

class SulawesiSelatanActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sulawesi_selatan)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Sulawesi Selatan"
        supportFragmentManager.beginTransaction()
            .add(R.id.container_sulawesi_selatan, SulawesiSelatanFragment())
            .commitNow()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("daerah", "true")
                startActivity(intent)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}