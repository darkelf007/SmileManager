package com.anaraliev.smilemanager.guide

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.anaraliev.smilemanager.R
import com.anaraliev.smilemanager.databinding.FragmentGuideBinding
import com.anaraliev.smilemanager.utils.REQUEST_KEY
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
        childFragmentManager.setFragmentResultListener(REQUEST_KEY, this) { _, _ ->
            val navController = requireActivity().findNavController(R.id.nav_host_fragment)
            navController.navigate(R.id.action_guideFragment_to_newCustomerFragment)
        }

        val adapter = GuidePagerAdapter(this)
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