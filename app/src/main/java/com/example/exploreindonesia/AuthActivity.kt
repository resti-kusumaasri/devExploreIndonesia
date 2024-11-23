package com.example.exploreindonesia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.exploreindonesia.databinding.ActivityAuthBinding
import com.example.exploreindonesia.ui.auth_ui.LoginFragment
import com.example.exploreindonesia.ui.auth_ui.RegisterFragment

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding
    private lateinit var btnlogin: ImageView
    private lateinit var btnregister: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, LoginFragment.newInstance())
                .commitNow()
        }

        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btnlogin = findViewById(R.id.btn_login)
        btnregister = findViewById(R.id.btn_register)

        btnregister.setOnClickListener {
            btnlogin.setImageResource(R.drawable.btn_login_inactive)
            btnregister.setImageResource(R.drawable.btn_register_active)
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, RegisterFragment.newInstance())
                .commitNow()
        }

        btnlogin.setOnClickListener {
            btnlogin.setImageResource(R.drawable.btn_login_active)
            btnregister.setImageResource(R.drawable.btn_register_inactive)
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, LoginFragment.newInstance())
                .commitNow()
        }


        setButton()


//        supportActionBar?.hide()
    }

    fun setButton(){
        if (supportFragmentManager.findFragmentById(R.id.container) is LoginFragment) {
            btnlogin.setImageResource(R.drawable.btn_login_active)
            btnregister.setImageResource(R.drawable.btn_register_inactive)
        } else {
            btnlogin.setImageResource(R.drawable.btn_login_inactive)
            btnregister.setImageResource(R.drawable.btn_register_active)
        }
    }
}