package com.example.exploreindonesia.ui.main_ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.exploreindonesia.R
import com.example.exploreindonesia.databinding.FragmentHomeBinding
import com.example.exploreindonesia.ui.faq.FAQActivity
import com.example.exploreindonesia.ui.main_ui.ocr.OCRActivity
import com.example.exploreindonesia.ui.main_ui.search.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private lateinit var buttonhomefirst: LinearLayout
    private lateinit var buttonhomesecond: LinearLayout
    private lateinit var buttonhomethird: LinearLayout

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        buttonhomefirst = binding.buttonHomeFirst
        buttonhomesecond = binding.buttonHomeSecond
        buttonhomethird = binding.buttonHomeThird

        buttonhomefirst.setOnClickListener {
            val intent = Intent(context, OCRActivity::class.java)
            startActivity(intent)
        }
        buttonhomesecond.setOnClickListener {
            val intent = Intent(context, FAQActivity::class.java)
            startActivity(intent)
        }

        buttonhomethird.setOnClickListener {

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