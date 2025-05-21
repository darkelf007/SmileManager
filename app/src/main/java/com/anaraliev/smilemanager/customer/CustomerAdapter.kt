package com.anaraliev.smilemanager.customer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.anaraliev.smilemanager.R
import com.anaraliev.smilemanager.database.entity.CustomerEntity

// Define a click listener interface
interface OnCustomerClickListener {
    fun onCustomerClick(customer: CustomerEntity)
}

class CustomerAdapter(private val listener: OnCustomerClickListener) : // Pass listener in constructor
    ListAdapter<CustomerEntity, CustomerViewHolder>(DiffCallback()) {

    private class DiffCallback : DiffUtil.ItemCallback<CustomerEntity>() {
        override fun areItemsTheSame(oldItem: CustomerEntity, newItem: CustomerEntity): Boolean {
            return oldItem.uid == newItem.uid
        }

        override fun areContentsTheSame(oldItem: CustomerEntity, newItem: CustomerEntity): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_customer_card, parent, false)
        return CustomerViewHolder(view, listener) // Pass listener to ViewHolder
    }


    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
        val customer = getItem(position)
        holder.bind(customer)
    }
}

class CustomerViewHolder(
    itemView: View,
    private val listener: OnCustomerClickListener // Receive listener
) : RecyclerView.ViewHolder(itemView) {
    private val customerName: TextView = itemView.findViewById(R.id.customer_name)
    private val contactNumberComments: TextView =
        itemView.findViewById(R.id.contact_number_comments)
    private val address: TextView = itemView.findViewById(R.id.address)
    private val email: TextView = itemView.findViewById(R.id.email)
    private val percentage: TextView = itemView.findViewById(R.id.percentage)

    private var currentCustomer: CustomerEntity? = null

    init {
        itemView.setOnClickListener {
            currentCustomer?.let { customer ->
                listener.onCustomerClick(customer) // Trigger listener on item click
            }
        }
    }

    fun bind(customer: CustomerEntity) {
        currentCustomer = customer // Store current customer
        customerName.text = customer.customerName
        contactNumberComments.text = customer.contactNumberComments ?: "N/A"
        address.text = customer.address ?: "N/A"
        email.text = customer.email ?: "N/A"
        percentage.text = when {
            customer.percentage != null -> "${customer.percentage}%"
            else -> "N/A"
        }
    }
}