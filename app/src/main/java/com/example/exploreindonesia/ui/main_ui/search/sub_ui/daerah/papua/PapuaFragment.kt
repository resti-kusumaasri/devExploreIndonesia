package com.example.exploreindonesia.ui.main_ui.search.sub_ui.daerah.papua

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exploreindonesia.R
import com.example.exploreindonesia.data.adapter.PapuaAdater
import com.example.exploreindonesia.data.model.Kategorimodel


class PapuaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_papua, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvPapua = view.findViewById<RecyclerView>(R.id.rv_papua)
        val adapter = PapuaAdater(getList())

        rvPapua.adapter = adapter
        rvPapua.layoutManager = GridLayoutManager(requireContext(), 3)

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