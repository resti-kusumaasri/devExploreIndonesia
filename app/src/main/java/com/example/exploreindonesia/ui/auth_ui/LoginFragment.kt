package com.example.exploreindonesia.ui.auth_ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.exploreindonesia.R
import com.example.exploreindonesia.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private lateinit var edtloginemail: EditText
    private lateinit var edtloginpassword: EditText

    companion object {
        fun newInstance() = LoginFragment()
    }

    lateinit var button: ImageView



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        edtloginemail = binding.edtLoginEmail
        edtloginpassword = binding.edtLoginPassword

        button = binding.imageView2

        button.setOnClickListener {
            Toast.makeText(context, "Halo", Toast.LENGTH_SHORT).show()
        }



        return inflater.inflate(R.layout.fragment_login, container, false)
    }

}