package com.anaraliev.smilemanager.new_task
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anaraliev.smilemanager.R
import com.anaraliev.smilemanager.database.entity.CustomerEntity


class NewTaskCustomerAdapter(

) : RecyclerView.Adapter<NewTaskCustomerViewHolder>() {

    private var customers: List<CustomerEntity> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewTaskCustomerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_customer_name_card, parent, false)
        return NewTaskCustomerViewHolder(view)
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
        notifyDataSetChanged()
    }
}

class NewTaskCustomerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val nameTextView: TextView = itemView.findViewById(R.id.customer_name_text_view)

    fun bind(customer: CustomerEntity) {
        nameTextView.text = customer.customerName
    }
}