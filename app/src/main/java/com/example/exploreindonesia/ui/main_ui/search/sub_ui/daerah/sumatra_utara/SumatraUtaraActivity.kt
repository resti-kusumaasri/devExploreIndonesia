package com.example.exploreindonesia.ui.main_ui.search.sub_ui.daerah.sumatra_utara

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.exploreindonesia.MainActivity
import com.example.exploreindonesia.R

class SumatraUtaraActivity : AppCompatActivity() {

    lateinit var container: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_sumatra_utara)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Sumatra Utara"




        supportFragmentManager.beginTransaction()
            .add(R.id.container_sumatra_utara, SumateraUtaraFragment())
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