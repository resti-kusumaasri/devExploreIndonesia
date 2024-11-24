package com.example.exploreindonesia

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.exploreindonesia.R.id.edt_login_password
import com.example.exploreindonesia.databinding.ActivityAuthBinding
import com.example.exploreindonesia.databinding.FragmentLoginBinding
import com.example.exploreindonesia.ui.auth_ui.AuthViewModel
import com.example.exploreindonesia.ui.auth_ui.LoginFragment
import com.example.exploreindonesia.ui.auth_ui.RegisterFragment

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding
    private lateinit var loginBinding: FragmentLoginBinding
    private lateinit var btnlogin: ImageView
    private lateinit var btnregister: ImageView
    private lateinit var btnsubmit: Button
    private lateinit var authViewModel: AuthViewModel


    private lateinit var edt_login_email: EditText
    private lateinit var edt_login_password: EditText

    private lateinit var edt_register_name: EditText
    private lateinit var edt_register_username: EditText
    private lateinit var edt_register_email: EditText
    private lateinit var edt_register_password: EditText
    private lateinit var edt_register_confirm_password: EditText







    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, LoginFragment.newInstance())
                .commitNow()
        }

        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)

        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)


        btnlogin = binding.btnLogin
        btnregister = binding.btnRegister
        btnsubmit = binding.btnSubmit


        btnregister.setOnClickListener {
            btnsubmit.setText(R.string.register)
            btnlogin.setImageResource(R.drawable.btn_login_inactive)
            btnregister.setImageResource(R.drawable.btn_register_active)
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, RegisterFragment.newInstance())
                .commitNow()
        }

        btnlogin.setOnClickListener {
            btnsubmit.setText(R.string.login)
            btnlogin.setImageResource(R.drawable.btn_login_active)
            btnregister.setImageResource(R.drawable.btn_register_inactive)
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, LoginFragment.newInstance())
                .commitNow()
        }

        btnsubmit.setOnClickListener {
            if (btnsubmit.text == "Login") {
                var email = findViewById<EditText>(R.id.edt_login_email).text.toString()
                var password = findViewById<EditText>(R.id.edt_login_password).text.toString()
                Toast.makeText(this, "email: $email, password: $password", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }  else {
                var name = findViewById<EditText>(R.id.edt_nama_lengkap).text.toString()
                var username = findViewById<EditText>(R.id.edt_username).text.toString()
                var email = findViewById<EditText>(R.id.edt_register_email).text.toString()
                var password = findViewById<EditText>(R.id.edt_register_password).text.toString()
                var confirm_password = findViewById<EditText>(R.id.edt_konfirmasi_password).text.toString()
                Toast.makeText(this, "name: $name", Toast.LENGTH_SHORT).show()
            }
        }


        setButton()
        supportActionBar?.show()
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

    public fun setVisiblebutton(isTrue:Boolean) {
        if (isTrue) {
            btnsubmit.visibility = View.VISIBLE
        } else {
            btnsubmit.visibility = View.GONE
        }
    }
}