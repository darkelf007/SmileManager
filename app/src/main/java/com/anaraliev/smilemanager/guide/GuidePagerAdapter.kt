package com.anaraliev.smilemanager.guide

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.anaraliev.smilemanager.guide.customer.CustomerFragment
import com.anaraliev.smilemanager.guide.doctor.DoctorFragment
import com.anaraliev.smilemanager.guide.tasks.TasksFragment

class GuidePagerAdapter(
    fragment: Fragment
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CustomerFragment()
            1 -> DoctorFragment()
            2 -> TasksFragment()
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }
}