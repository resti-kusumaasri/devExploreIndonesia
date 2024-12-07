package com.example.exploreindonesia.ui.main_ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.exploreindonesia.R
import com.example.exploreindonesia.databinding.FragmentHomeBinding
import com.example.exploreindonesia.ui.main_ui.ocr.OCRActivity
import com.example.exploreindonesia.ui.main_ui.search.SearchFragment
import com.example.exploreindonesia.ui.main_ui.search.sub_ui.kategori.KategoriFlashCardActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    lateinit var button_home_first: LinearLayout
    lateinit var button_home_second: LinearLayout
    lateinit var button_home_third: LinearLayout

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        button_home_first = binding.buttonHomeFirst
        button_home_second = binding.buttonHomeSecond
        button_home_third = binding.buttonHomeThird

        button_home_first.setOnClickListener {
            val intent = Intent(context, OCRActivity::class.java)
            startActivity(intent)
        }
        button_home_second.setOnClickListener {
            val intent = Intent(context, KategoriFlashCardActivity::class.java)
            intent.putExtra("kategori", "Kuliner Nusantara")
            startActivity(intent)
        }

        button_home_third.setOnClickListener {

            val bundle = Bundle()
            bundle.putBoolean("s", true)

            SearchFragment().arguments = bundle

            val navView = requireActivity().findViewById<BottomNavigationView>(R.id.nav_view)
            navView.selectedItemId = R.id.navigation_search
            findNavController().navigate(R.id.navigation_search, bundle)

        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        View.GONE
    }
}