package com.example.exploreindonesia.ui.main_ui.profile

import android.app.Activity
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.exploreindonesia.AuthActivity
import com.example.exploreindonesia.R
import com.example.exploreindonesia.data.request.EditRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
        val btnEditProfile = view.findViewById<Button>(R.id.btn_edit_profile)

        val akunSharedPreferences = requireActivity().getSharedPreferences("akun", MODE_PRIVATE)
        var userId = akunSharedPreferences.getString("userId", null).toString()

        viewModel.getProfile(userId)

        val editName = view.findViewById<EditText>(R.id.name_edit)
        val editUsername = view.findViewById<EditText>(R.id.username_edit)




        childFragmentManager.beginTransaction()
            .replace(R.id.profile_container, ViewProfileFragment())
            .commitNow()





        btnEditProfile.setOnClickListener {
            if (btnEditProfile.text == "Edit Profile") {
                btnEditProfile.text = "Simpan"
                btnLogout.text = "Batal"
                childFragmentManager.beginTransaction()
                    .replace(R.id.profile_container, EditProfileFragment())
                    .commitNow()
            } else if (btnEditProfile.text == "Simpan") {

                var bundle = Bundle()
                bundle.putBoolean("edit", true)
                var name = viewModel.newName.value
                var username = viewModel.newUsername.value

                val request = EditRequest(name  , username)

                viewModel.editProfile(userId, request)

                btnEditProfile.text = "Edit Profile"
                btnLogout.text = "Logout"
                childFragmentManager.beginTransaction()
                    .replace(R.id.profile_container, ViewProfileFragment())
                    .commitNow()
            }

        }



        btnLogout.setOnClickListener {
            if (btnLogout.text == "Logout") {
                akunSharedPreferences.edit().clear().apply()

                val intent = Intent(requireContext(), AuthActivity::class.java)
                startActivity(intent)
            } else if (btnLogout.text == "Batal") {
                btnEditProfile.text = "Edit Profile"
                btnLogout.text = "Logout"
               childFragmentManager.beginTransaction()
                   .replace(R.id.profile_container, ViewProfileFragment())
                   .commitNow()
            }
        }
    }
}