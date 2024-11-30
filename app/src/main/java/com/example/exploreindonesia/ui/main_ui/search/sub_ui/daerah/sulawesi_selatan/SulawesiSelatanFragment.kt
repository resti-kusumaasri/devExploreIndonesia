package com.example.exploreindonesia.ui.main_ui.search.sub_ui.daerah.sulawesi_selatan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exploreindonesia.R
import com.example.exploreindonesia.data.adapter.SulawesiSelatanAdapter
import com.example.exploreindonesia.data.model.kategori_model

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SulawesiSelatanFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
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