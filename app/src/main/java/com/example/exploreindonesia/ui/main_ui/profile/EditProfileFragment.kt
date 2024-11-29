package com.example.exploreindonesia.ui.main_ui.profile

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.example.exploreindonesia.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

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
        var userId = akunSharedPreferences.getString("userId", null).toString()

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

        val newNama = editName.text.toString()
        val newUsername = editUsername.text.toString()

        viewModel.newName.value = newNama
        viewModel.newUsername.value = newUsername

    }
}