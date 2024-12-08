package com.example.exploreindonesia.ui.main_ui.search.sub_ui.daerah.papua

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.exploreindonesia.MainActivity
import com.example.exploreindonesia.R

class PapuaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_papua)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Papua"


        supportFragmentManager.beginTransaction()
            .add(R.id.container_papua, PapuaFragment())
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