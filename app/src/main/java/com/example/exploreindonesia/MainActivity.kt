package com.example.exploreindonesia

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.exploreindonesia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var button_home_first: LinearLayout
    lateinit var button_home_second: LinearLayout
    lateinit var button_home_third: LinearLayout

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        supportActionBar?.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.actionbarcorner))

        button_home_first = findViewById(R.id.button_home_first)
        button_home_second = findViewById(R.id.button_home_second)
        button_home_third = findViewById(R.id.button_home_third)

        button_home_first.setOnClickListener {
            Toast.makeText(this, "Home First", Toast.LENGTH_SHORT).show()
        }

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}