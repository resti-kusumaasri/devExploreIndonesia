package com.example.exploreindonesia.ui.main_ui.search.sub_ui.daerah

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

class DaerahFragment : Fragment() {

    companion object {
        fun newInstance() = DaerahFragment()
    }

    private val viewModel: DaerahViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_daerah, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val daerah = resources.obtainTypedArray(R.array.gambar_daerah)
        val daerahList = mutableListOf<Int>()
        for (i in 0 until daerah.length()) {
            daerahList.add(daerah.getResourceId(i, -1))
        }
        daerah.recycle()


        val daerahRecyclerView = view.findViewById<RecyclerView>(R.id.recyclerView_daerah)
        val adapter = DaerahAdapter(daerahList)
        daerahRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        daerahRecyclerView.adapter = adapter


    }
}