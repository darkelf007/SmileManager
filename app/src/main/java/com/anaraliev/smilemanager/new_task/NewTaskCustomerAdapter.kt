package com.anaraliev.smilemanager.new_task

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anaraliev.smilemanager.R
import com.anaraliev.smilemanager.database.entity.CustomerEntity

// Modify the constructor to accept the listener
class NewTaskCustomerAdapter(
    private val listener: CustomerSelectionListener // Accept the listener here
) : RecyclerView.Adapter<NewTaskCustomerViewHolder>() {

    private var customers: List<CustomerEntity> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewTaskCustomerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_customer_name_card, parent, false)
        // Pass the listener to the ViewHolder
        return NewTaskCustomerViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: NewTaskCustomerViewHolder, position: Int) {
        val customer = customers[position]
        holder.bind(customer)
    }

    override fun getItemCount(): Int {
        return customers.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(newCustomers: List<CustomerEntity>) {
        customers = newCustomers
        notifyDataSetChanged() // Consider using DiffUtil for better performance later
    }
}

// Modify the ViewHolder to handle clicks
class NewTaskCustomerViewHolder(
    itemView: View,
    private val listener: CustomerSelectionListener // Receive listener
) : RecyclerView.ViewHolder(itemView), View.OnClickListener { // Implement OnClickListener

    private val nameTextView: TextView = itemView.findViewById(R.id.customer_name_text_view)
    private var currentCustomer: CustomerEntity? = null // Hold the current customer

    init {
        itemView.setOnClickListener(this) // Set the click listener for the whole item view
    }

    fun bind(customer: CustomerEntity) {
        currentCustomer = customer // Store the customer bound to this view
        nameTextView.text = customer.customerName
    }

    override fun onClick(v: View?) {
        // When clicked, notify the listener with the current customer
        currentCustomer?.let {
            listener.onCustomerSelected(it)
        }
    }
}