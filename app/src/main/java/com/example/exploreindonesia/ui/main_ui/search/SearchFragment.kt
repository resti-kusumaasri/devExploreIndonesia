package com.example.exploreindonesia.ui.main_ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.exploreindonesia.R
import com.example.exploreindonesia.databinding.FragmentSearchBinding
import com.example.exploreindonesia.ui.main_ui.search.sub_ui.daerah.DaerahFragment
import com.example.exploreindonesia.ui.main_ui.search.sub_ui.kategori.KategoriFragment

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            childFragmentManager.beginTransaction()
                .replace(R.id.container_search, DaerahFragment.newInstance())
                .commitNow()
        }

        binding.btnDaerah.setOnClickListener {
            binding.btnDaerah.setImageResource(R.drawable.daerah_enable)
            binding.btnKategori.setImageResource(R.drawable.kategori_disable)


            childFragmentManager.beginTransaction()
                .replace(R.id.container_search, DaerahFragment.newInstance())
                .commitNow()
        }

        binding.btnKategori.setOnClickListener {
            binding.btnDaerah.setImageResource(R.drawable.daerah_disable)
            binding.btnKategori.setImageResource(R.drawable.kategori_enable)

            childFragmentManager.beginTransaction()
                .replace(R.id.container_search, KategoriFragment.newInstance())
                .commitNow()

        }

    }



}