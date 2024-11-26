package com.example.exploreindonesia.ui.main_ui.dashboard

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.exploreindonesia.R
import com.example.exploreindonesia.databinding.FragmentDashboardBinding
import com.example.exploreindonesia.ui.main_ui.dashboard.sub_ui.daerah.DaerahFragment

class DashBoardFragment : Fragment() {

    companion object {
        fun newInstance() = DashBoardFragment()
    }

    private val viewModel: DashBoardViewModel by viewModels()
    private lateinit var binding: FragmentDashboardBinding
    private lateinit var conatiner: ConstraintLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState==null) {
            childFragmentManager.beginTransaction()
                .replace(R.id.container_dashboard, DaerahFragment.newInstance())
                .commitNow()
        }

        binding.btnDaerah.setOnClickListener {
            binding.btnDaerah.setImageResource(R.drawable.daerah_enable)
            binding.btnKategori.setImageResource(R.drawable.kategori_disable)
            binding.btnDaerah.isClickable = false
            binding.btnKategori.isClickable = true

            childFragmentManager.beginTransaction()
                .replace(R.id.container_dashboard, DaerahFragment.newInstance())
                .commitNow()
        }

        binding.btnKategori.setOnClickListener {
            binding.btnDaerah.setImageResource(R.drawable.daerah_disable)
            binding.btnKategori.setImageResource(R.drawable.kategori_enable)

        }

    }


}