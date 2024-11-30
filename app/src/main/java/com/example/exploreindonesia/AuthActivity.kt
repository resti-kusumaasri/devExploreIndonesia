package com.example.exploreindonesia

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.exploreindonesia.data.request.LoginRequest
import com.example.exploreindonesia.data.request.RegisterRequest
import com.example.exploreindonesia.databinding.ActivityAuthBinding
import com.example.exploreindonesia.ui.auth_ui.AuthViewModel
import com.example.exploreindonesia.ui.auth_ui.LoginFragment
import com.example.exploreindonesia.ui.auth_ui.RegisterFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding
    private lateinit var btnlogin: ImageView
    private lateinit var btnregister: ImageView
    private lateinit var btnsubmit: Button
    private lateinit var authViewModel: AuthViewModel
    private var userId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container_auth, LoginFragment.newInstance())
                .commitNow()
        }

        val akunSharedPreferences = getSharedPreferences("akun", MODE_PRIVATE)

        if (akunSharedPreferences.getString("userId", null) != null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
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
                .replace(R.id.container_auth, RegisterFragment.newInstance())
                .commitNow()
        }

        btnlogin.setOnClickListener {
            btnsubmit.setText(R.string.login)
            btnlogin.setImageResource(R.drawable.btn_login_active)
            btnregister.setImageResource(R.drawable.btn_register_inactive)
            supportFragmentManager.beginTransaction()
                .replace(R.id.container_auth, LoginFragment.newInstance())
                .commitNow()
        }

        btnsubmit.setOnClickListener {
            if (btnsubmit.text == "Login") {
                Toast.makeText(this, "Proses Login Diproses", Toast.LENGTH_SHORT).show()
                val email = findViewById<EditText>(R.id.edt_login_email).text.toString()
                val password = findViewById<EditText>(R.id.edt_login_password).text.toString()
                if (email != "" && password != "") {
                    val loginData = LoginRequest(email, password)
                    authViewModel.loginUser(loginData)
                }
                Toast.makeText(this, "Proses Login Selesai", Toast.LENGTH_SHORT).show()
                authViewModel.registerResult.observe(this) { result ->
                    val intent = Intent(this, MainActivity::class.java)
                    CoroutineScope(Dispatchers.Main).launch {
                        delay(500)
                        userId = result.toString()
                        akunSharedPreferences.edit().putString("userId", userId).apply()
                        if (result != null) {
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText((this@AuthActivity), "Login Gagal", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }

            } else {
                Toast.makeText(this, "Proses Register Diproses", Toast.LENGTH_SHORT).show()

                val name = findViewById<EditText>(R.id.edt_nama_lengkap).text.toString()
                val email = findViewById<EditText>(R.id.edt_register_email).text.toString()
                val username = findViewById<EditText>(R.id.edt_username).text.toString()
                val password = findViewById<EditText>(R.id.edt_register_password).text.toString()
                val confirm_password =
                    findViewById<EditText>(R.id.edt_konfirmasi_password).text.toString()


                if (name != "" && email != "" && username != "" && password != "" && confirm_password != "") {
                    val registerData =
                        RegisterRequest(name, email, username, password, confirm_password)
                    authViewModel.registerUser(registerData)
                }
                authViewModel.registerResult.observe(this) { result ->
                    Toast.makeText(this, " $result dengan email : $email", Toast.LENGTH_SHORT)
                        .show()
                    if (result == "Registrasi berhasil") {
                        CoroutineScope(Dispatchers.Main).launch {
                            delay(1000)
                            btnlogin.setImageResource(R.drawable.btn_login_active)
                            btnregister.setImageResource(R.drawable.btn_register_inactive)
                            supportFragmentManager.beginTransaction()
                                .replace(R.id.container_auth, LoginFragment.newInstance())
                                .commitNow()
                        }
                    }
                }
            }
        }

        setButton()
        supportActionBar?.show()
    }

    fun setButton() {
        if (supportFragmentManager.findFragmentById(R.id.container_auth) is LoginFragment) {
            btnlogin.setImageResource(R.drawable.btn_login_active)
            btnregister.setImageResource(R.drawable.btn_register_inactive)
        } else {
            btnlogin.setImageResource(R.drawable.btn_login_inactive)
            btnregister.setImageResource(R.drawable.btn_register_active)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}