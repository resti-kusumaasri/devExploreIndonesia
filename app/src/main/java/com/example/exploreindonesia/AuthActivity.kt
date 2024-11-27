package com.example.exploreindonesia

import android.annotation.SuppressLint
import android.content.Intent
import android.media.session.MediaSession.Token
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.exploreindonesia.data.request.LoginRequest
import com.example.exploreindonesia.data.request.RegisterRequest
import com.example.exploreindonesia.databinding.ActivityAuthBinding
import com.example.exploreindonesia.databinding.FragmentLoginBinding
import com.example.exploreindonesia.ui.auth_ui.AuthViewModel
import com.example.exploreindonesia.ui.auth_ui.LoginFragment
import com.example.exploreindonesia.ui.auth_ui.RegisterFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding
    private lateinit var btnlogin: ImageView
    private lateinit var btnregister: ImageView
    private lateinit var btnsubmit: Button
    private lateinit var authViewModel: AuthViewModel
    private lateinit var token: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container_auth, LoginFragment.newInstance())
                .commitNow()
        }

        val akunSharedPreferences = getSharedPreferences("akun", MODE_PRIVATE)

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
                val email = findViewById<EditText>(R.id.edt_login_email).text.toString()
                val password = findViewById<EditText>(R.id.edt_login_password).text.toString()
                if (email!="" && password!="") {
                    val loginData = LoginRequest(email, password)
                    authViewModel.loginUser(loginData)
                }
                binding.containerAuth.visibility = View.VISIBLE
                authViewModel.registerResult.observe(this) { result ->
                    val intent = Intent(this, MainActivity::class.java)
                    CoroutineScope(Dispatchers.Main).launch{
                        delay(500)
                        token = result.toString()
                        akunSharedPreferences.edit().putString("token", token).apply()
                        if (result != null) {
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText((this@AuthActivity), "Login Gagal", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

            }  else {

                val name = findViewById<EditText>(R.id.edt_nama_lengkap).text.toString()
                val email = findViewById<EditText>(R.id.edt_register_email).text.toString()
                val username = findViewById<EditText>(R.id.edt_username).text.toString()
                val password = findViewById<EditText>(R.id.edt_register_password).text.toString()
                val confirm_password = findViewById<EditText>(R.id.edt_konfirmasi_password).text.toString()


                if (name!="" && email!="" && username!="" && password!="" && confirm_password!="") {
                    val registerData =
                        RegisterRequest(name, email, username, password, confirm_password)
                    authViewModel.registerUser(registerData)
                }
                authViewModel.registerResult.observe(this) { result ->
                    Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
                }
            }
        }




        setButton()
        supportActionBar?.show()
    }

    fun setButton(){
        if (supportFragmentManager.findFragmentById(R.id.container_auth) is LoginFragment) {
            btnlogin.setImageResource(R.drawable.btn_login_active)
            btnregister.setImageResource(R.drawable.btn_register_inactive)
        } else {
            btnlogin.setImageResource(R.drawable.btn_login_inactive)
            btnregister.setImageResource(R.drawable.btn_register_active)
        }
    }

}