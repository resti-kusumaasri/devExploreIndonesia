package com.example.exploreindonesia.ui.main_ui.search.sub_ui.daerah

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exploreindonesia.R
import com.example.exploreindonesia.data.adapter.DaerahAdapter
import com.example.exploreindonesia.data.model.daerah_model
import com.example.exploreindonesia.ui.main_ui.search.sub_ui.daerah.sulawesi_selatan.SulawesiSelatanActivity
import com.example.exploreindonesia.ui.main_ui.search.sub_ui.daerah.sumatra_utara.SumatraUtaraActivity

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


        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView_daerah)
        val adapter = DaerahAdapter(getList(), onItemClick = {
            when (it.nama) {
                "Sumatra Utara" -> {
                    val intent = Intent(requireContext(), SumatraUtaraActivity::class.java)
                    startActivity(intent)
                }

                "Sulawesi Selatan" -> {
                    val intent = Intent(requireContext(), SulawesiSelatanActivity::class.java)
                    startActivity(intent)
                }
                else -> {

                }
            }
        })
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        val search = view.findViewById<SearchView>(R.id.search_bar_daerah)




        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                adapter.filter.filter(p0)
                return true
            }
        })
    }

    fun getList(): ArrayList<daerah_model> {
        val image = resources.obtainTypedArray(R.array.gambar_daerah)
        val name = resources.getStringArray(R.array.nama_daerah)
        val list = ArrayList<daerah_model>()
        for (i in name.indices) {
            val daerah = daerah_model(image.getResourceId(i, -1), name[i])
            list.add(daerah)
        }
        return list
    }
}

