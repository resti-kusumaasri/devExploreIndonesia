package com.example.exploreindonesia.ui.main_ui.Histori

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exploreindonesia.R
import com.example.exploreindonesia.data.adapter.HistoriAdapter
import com.example.exploreindonesia.data.model.histori_model
import com.example.exploreindonesia.databinding.FragmentHistoriBinding


class HistoriFragment : Fragment() {

    private var _binding: FragmentHistoriBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val historiViewModel =
            ViewModelProvider(this).get(HistoriViewModel::class.java)

        _binding = FragmentHistoriBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView_histori)

        val historyData = listOf(
            histori_model("Sejarah Nusantara", "2024-11-27 10:00 AM"),
            histori_model("Kuliner Nusantara", "2024-11-27 10:30 AM"),
            histori_model("Quiz Nusantara", "2024-11-27 10:30 AM")
        )

        recyclerView.adapter = HistoriAdapter(historyData)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}