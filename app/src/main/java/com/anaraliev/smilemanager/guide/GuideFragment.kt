package com.anaraliev.smilemanager.guide

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.anaraliev.smilemanager.R
import com.anaraliev.smilemanager.databinding.FragmentGuideBinding
import com.google.android.material.tabs.TabLayoutMediator

class GuideFragment : Fragment() {

    private var _binding: FragmentGuideBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGuideBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = GuidePagerAdapter(requireActivity())
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Заказчик"
                    tab.icon = resources.getDrawable(R.drawable.customer, null)
                }
                1 -> {
                    tab.text = "Врач"
                    tab.icon = resources.getDrawable(R.drawable.medicaldoctor, null)
                }
                2 -> {
                    tab.text = "Операции"
                    tab.icon = resources.getDrawable(R.drawable.tasks, null)
                }
                else -> throw IllegalArgumentException("Invalid position: $position")
            }
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}