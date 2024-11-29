package com.example.exploreindonesia.ui.main_ui.profile

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.example.exploreindonesia.AuthActivity
import com.example.exploreindonesia.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ViewProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
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

        var username = view.findViewById<TextView>(R.id.username_teks)
        var email = view.findViewById<TextView>(R.id.email)
        var name = view.findViewById<TextView>(R.id.name)

        val akunSharedPreferences = requireActivity().getSharedPreferences("akun", MODE_PRIVATE)
        var userId = akunSharedPreferences.getString("userId", null).toString()

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