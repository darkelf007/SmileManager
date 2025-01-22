package com.anaraliev.smilemanager.guide.doctor

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.anaraliev.smilemanager.R

class DoctorFragment : Fragment() {

    companion object {
        fun newInstance() = DoctorFragment()
    }

    private val viewModel: DoctorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_doctor, container, false)
    }
}