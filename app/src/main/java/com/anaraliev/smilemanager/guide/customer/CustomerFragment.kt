package com.anaraliev.smilemanager.guide.customer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.anaraliev.smilemanager.R
import com.anaraliev.smilemanager.utils.REQUEST_KEY_NEW_CUSTOMER

class CustomerFragment : Fragment() {


    private val viewModel: CustomerViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_customer, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonNewCustomer = view.findViewById<Button>(R.id.button_new_customer)
        buttonNewCustomer.setOnClickListener {
            requireParentFragment().childFragmentManager.setFragmentResult(REQUEST_KEY_NEW_CUSTOMER, Bundle())
        }
    }

}