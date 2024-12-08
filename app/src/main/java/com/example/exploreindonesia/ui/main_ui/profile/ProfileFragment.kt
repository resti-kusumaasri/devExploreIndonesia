package com.example.exploreindonesia.ui.main_ui.profile

import android.annotation.SuppressLint
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.exploreindonesia.AuthActivity
import com.example.exploreindonesia.R
import com.example.exploreindonesia.data.request.EditRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {


    private val viewModel: ProfileViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    @SuppressLint("CutPasteId")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnLogout = view.findViewById<Button>(R.id.btn_logout)
        val btnEditProfile = view.findViewById<Button>(R.id.btn_edit_profile)

        val akunSharedPreferences = requireActivity().getSharedPreferences("akun", MODE_PRIVATE)
        val userId = akunSharedPreferences.getString("userId", null).toString()

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

                val newName = view.findViewById<EditText>(R.id.name_edit)
                val newUsername = view.findViewById<EditText>(R.id.username_edit)


                val bundle = Bundle()
                bundle.putBoolean("edit", true)
                val name = newName.text.toString()
                val username = newUsername.text.toString()

                val request = EditRequest(name, username)

                Toast.makeText(
                    requireContext(),
                    "Ubah Profile Sedang di Proses",
                    Toast.LENGTH_SHORT
                ).show()
                viewModel.editProfile(userId, request)
                btnEditProfile.text = "Edit Profile"
                btnLogout.text = "Logout"
                Toast.makeText(requireContext(), "Ubah Profile Berhasil", Toast.LENGTH_SHORT).show()
                CoroutineScope(Dispatchers.Main).launch {
                    delay(1000)
                    childFragmentManager.beginTransaction()
                        .replace(R.id.profile_container, ViewProfileFragment())
                        .commitNow()
                }
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