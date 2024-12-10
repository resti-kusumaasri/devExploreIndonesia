package com.example.exploreindonesia.ui.main_ui.profile

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.exploreindonesia.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


class ViewProfileFragment : Fragment() {
    val viewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val username = view.findViewById<TextView>(R.id.username_teks)
        val email = view.findViewById<TextView>(R.id.email)
        val name = view.findViewById<TextView>(R.id.name)

        val akunSharedPreferences = requireActivity().getSharedPreferences("akun", MODE_PRIVATE)
        val userId = akunSharedPreferences.getString("userId", null).toString()

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
    }
}