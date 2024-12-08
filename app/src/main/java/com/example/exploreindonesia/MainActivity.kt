package com.example.exploreindonesia

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.exploreindonesia.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val akunSharedPreferences = getSharedPreferences("akun", MODE_PRIVATE)
        if (akunSharedPreferences.getString("userId", null) == null) {
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        supportActionBar?.setBackgroundDrawable(
            ContextCompat.getDrawable(
                this,
                R.drawable.actionbarcorner
            )
        )

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        if (!isInternetAvailable()) {
            Toast.makeText(this, "Tidak ada koneksi internet", Toast.LENGTH_SHORT).show()
        }
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_search,
                R.id.navigation_history,
                R.id.navigation_profile
            )
        )


        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }


    override fun onResume() {
        super.onResume()
        val intent = intent.getStringExtra("daerah")

        if (intent == "true") {
            val navGraph =
                findNavController(R.id.nav_host_fragment_activity_main).navInflater.inflate(R.navigation.mobile_navigation)
            navGraph.setStartDestination(R.id.navigation_search)
            findNavController(R.id.nav_host_fragment_activity_main).graph = navGraph
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        val akunsharedPreferences = getSharedPreferences("akun", MODE_PRIVATE)
        if (akunsharedPreferences.getString("userId", null) == null) {
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun isInternetAvailable(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
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