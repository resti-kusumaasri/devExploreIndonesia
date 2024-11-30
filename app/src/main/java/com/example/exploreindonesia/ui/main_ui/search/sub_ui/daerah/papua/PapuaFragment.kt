package com.example.exploreindonesia.ui.main_ui.search.sub_ui.daerah.papua

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exploreindonesia.R
import com.example.exploreindonesia.data.adapter.PapuaAdater
import com.example.exploreindonesia.data.model.kategori_model


class PapuaFragment : Fragment() {
    // TODO: Rename and change types of parameters




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

    fun getList(): ArrayList<kategori_model> {
        val image = resources.obtainTypedArray(R.array.gambar_kategori)
        val name = resources.getStringArray(R.array.nama_kategori)
        val list = ArrayList<kategori_model>()
        for (i in name.indices) {
            val kategori = kategori_model(image.getResourceId(i, -1), name[i])
            list.add(kategori)
        }
        return list
    }
}