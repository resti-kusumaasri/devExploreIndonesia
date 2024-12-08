package com.example.exploreindonesia.ui.main_ui.histori

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exploreindonesia.R
import com.example.exploreindonesia.data.adapter.HistoriAdapter
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

        _binding = FragmentHistoriBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val akunsharedpreferenches =
            requireContext().getSharedPreferences("akun", Activity.MODE_PRIVATE)
        val id = akunsharedpreferenches.getString("userId", "") ?: ""

        val viewModel = ViewModelProvider(this)[HistoriViewModel::class.java]

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView_histori)

        viewModel.historiList.observe(viewLifecycleOwner) { historiList ->
            val historiAdapter = HistoriAdapter(historiList)
            recyclerView.adapter = historiAdapter
        }

        viewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
        }

        viewModel.getRiwayat(id)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}