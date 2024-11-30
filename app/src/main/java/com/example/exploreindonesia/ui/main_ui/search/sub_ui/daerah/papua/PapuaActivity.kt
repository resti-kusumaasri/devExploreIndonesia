package com.example.exploreindonesia.ui.main_ui.search.sub_ui.daerah.papua

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.exploreindonesia.MainActivity
import com.example.exploreindonesia.R
import com.example.exploreindonesia.ui.main_ui.search.sub_ui.daerah.sumatra_utara.SumateraUtaraFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

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