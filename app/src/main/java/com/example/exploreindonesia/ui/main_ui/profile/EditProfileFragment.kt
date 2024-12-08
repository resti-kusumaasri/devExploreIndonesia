package com.example.exploreindonesia.ui.main_ui.profile

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.exploreindonesia.R


/**
 * A simple [Fragment] subclass.
 * Use the [EditProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters

    companion object {
        val newInstance = EditProfileFragment()
    }

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
        return inflater.inflate(R.layout.fragment_edit_profile, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val akunSharedPreferences = requireActivity().getSharedPreferences("akun", MODE_PRIVATE)
        val userId = akunSharedPreferences.getString("userId", null).toString()

        val akunEditprefences = requireActivity().getSharedPreferences("edit", MODE_PRIVATE)


        val viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]

        viewModel.getProfile(userId)

        val editName = view.findViewById<EditText>(R.id.name_edit)
        val editUsername = view.findViewById<EditText>(R.id.username_edit)

        viewModel.fullname.observe(viewLifecycleOwner) {
            editName.setText(it)
        }

        viewModel.username.observe(viewLifecycleOwner) {
            editUsername.setText(it)
        }


    }
}