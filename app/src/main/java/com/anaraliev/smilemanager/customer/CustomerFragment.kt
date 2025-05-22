package com.anaraliev.smilemanager.customer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.bundle.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anaraliev.smilemanager.R
import com.anaraliev.smilemanager.database.entity.CustomerEntity
import com.anaraliev.smilemanager.utils.BUNDLE_KEY_CUSTOMER_ID
import com.anaraliev.smilemanager.utils.REQUEST_KEY_EDIT_CUSTOMER
import com.anaraliev.smilemanager.utils.REQUEST_KEY_NEW_CUSTOMER
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CustomerFragment : Fragment(), OnCustomerClickListener {

    private lateinit var customerRecyclerView: RecyclerView
    private lateinit var customerAdapter: CustomerAdapter

    private val viewModel: CustomerViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_customer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        customerRecyclerView = view.findViewById(R.id.recyclerview_customer)
        customerAdapter = CustomerAdapter(this)
        customerRecyclerView.layoutManager = LinearLayoutManager(context)
        customerRecyclerView.adapter = customerAdapter

        viewModel.customers.collectFlow {
            customerAdapter.submitList(it)
        }

        val buttonNewCustomer = view.findViewById<Button>(R.id.button_new_customer)
        buttonNewCustomer.setOnClickListener {
            requireParentFragment().childFragmentManager.setFragmentResult(REQUEST_KEY_NEW_CUSTOMER, Bundle())
        }
    }

    override fun onCustomerClick(customer: CustomerEntity) {

        val bundle = bundleOf(BUNDLE_KEY_CUSTOMER_ID to customer.uid)
        requireParentFragment().childFragmentManager.setFragmentResult(REQUEST_KEY_EDIT_CUSTOMER, bundle)
    }

    private inline fun <T> Flow<T>.collectFlow(crossinline action: (T) -> Unit) {
        viewLifecycleOwner.lifecycleScope.launch {
            collect {
                action.invoke(it)
            }
        }
    }
}