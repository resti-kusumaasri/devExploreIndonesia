package com.example.exploreindonesia.ui.main_ui.search.sub_ui.kategori

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exploreindonesia.R
import com.example.exploreindonesia.data.adapter.DaerahAdapter

class KategoriFragment : Fragment() {

    companion object {
        fun newInstance() = KategoriFragment()
    }

    private val viewModel: KategoriViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_kategori, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val daerah = resources.obtainTypedArray(R.array.gambar_kategori)
        val daerahList = mutableListOf<Int>()
        for (i in 0 until daerah.length()) {
            daerahList.add(daerah.getResourceId(i, -1))
        }
        daerah.recycle()


        val kategoriRecyclerView = view.findViewById<RecyclerView>(R.id.recyclerView_kategori)
        val adapter = DaerahAdapter(daerahList)
        kategoriRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        kategoriRecyclerView.adapter = adapter
    }
}