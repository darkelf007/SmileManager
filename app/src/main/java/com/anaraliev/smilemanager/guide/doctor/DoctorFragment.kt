package com.anaraliev.smilemanager.guide.doctor

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.anaraliev.smilemanager.R
import com.anaraliev.smilemanager.utils.REQUEST_KEY_NEW_DOCTOR

class DoctorFragment : Fragment() {

    private val viewModel: DoctorViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_doctor, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonNewCustomer = view.findViewById<Button>(R.id.button_new_doctor)
        buttonNewCustomer.setOnClickListener {
            parentFragmentManager.setFragmentResult(REQUEST_KEY_NEW_DOCTOR, Bundle())
        }

    }
}