package com.example.exploreindonesia.ui.main_ui.search.sub_ui.daerah.sulawesi_selatan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exploreindonesia.R
import com.example.exploreindonesia.data.adapter.SulawesiSelatanAdapter
import com.example.exploreindonesia.data.model.Kategorimodel

// TODO: Rename parameter arguments, choose names that match


class SulawesiSelatanFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sulawesi_selatan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvSulawesiSelatan = view.findViewById<RecyclerView>(R.id.rv_sulawesi_selatan)
        rvSulawesiSelatan.adapter = SulawesiSelatanAdapter(getList())
        rvSulawesiSelatan.layoutManager = GridLayoutManager(requireContext(), 3)


    }


    private fun getList(): ArrayList<Kategorimodel> {
        val image = resources.obtainTypedArray(R.array.gambar_kategori)
        val name = resources.getStringArray(R.array.nama_kategori)
        val list = ArrayList<Kategorimodel>()
        for (i in name.indices) {
            val kategori = Kategorimodel(image.getResourceId(i, -1), name[i])
            list.add(kategori)
        }
        return list
    }
}