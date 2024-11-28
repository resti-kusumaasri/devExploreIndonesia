package com.example.exploreindonesia.ui.main_ui.profile

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import com.example.exploreindonesia.AuthActivity
import com.example.exploreindonesia.R

class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnLogout = view.findViewById<Button>(R.id.btn_logout)

        val akunSharedPreferences = requireActivity().getSharedPreferences("akun", MODE_PRIVATE)
        var userId = akunSharedPreferences.getString("userId", null).toString()

        var username = view.findViewById<TextView>(R.id.username_teks)
        var email = view.findViewById<TextView>(R.id.email)
        var name = view.findViewById<TextView>(R.id.name)

        viewModel.getProfile(userId)

       viewModel.username.observe(viewLifecycleOwner) {
           username.text = it
       }
       viewModel.email.observe(viewLifecycleOwner) {
           email.text = it
       }

        viewModel.fullname.observe(viewLifecycleOwner) {
            name.text = it
       }



        btnLogout.setOnClickListener {
            akunSharedPreferences.edit().clear().apply()

            val intent = Intent(requireContext(), AuthActivity::class.java)
            startActivity(intent)
        }
    }


}