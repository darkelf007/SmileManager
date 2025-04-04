package com.anaraliev.smilemanager.new_task

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.anaraliev.smilemanager.databinding.NewTaskCardBinding

interface PriceItemListener {
    fun onPriceChanged(position: Int, newPrice: Double)
}

class PriceItemAdapter(private val listener: PriceItemListener) :
    ListAdapter<PriceItemDisplay, PriceItemAdapter.PriceItemViewHolder>(PriceItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PriceItemViewHolder {
        val binding = NewTaskCardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PriceItemViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: PriceItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class PriceItemViewHolder(
        val binding: NewTaskCardBinding,
        val listener: PriceItemListener
    ) : RecyclerView.ViewHolder(binding.root) {

        private var currentItem: PriceItemDisplay? = null
        var priceTextWatcher: TextWatcher? = null

        fun bind(item: PriceItemDisplay) {
            currentItem = item

            binding.orderName.setText(item.workName)
            binding.baseCost.setText(item.basePriceForReference?.toString() ?: "-")

            priceTextWatcher?.let { binding.cost.removeTextChangedListener(it) }

            binding.cost.setText(item.price.toString())

            priceTextWatcher = object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable?) {
                    val position = bindingAdapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val newPrice = s?.toString()?.toDoubleOrNull()
                        if (newPrice != null && newPrice >= 0) {
                            if (newPrice != currentItem?.price) {
                                listener.onPriceChanged(position, newPrice)
                            }
                        } else {
                            // Handle invalid input if needed
                        }
                    }
                }
            }
            binding.cost.addTextChangedListener(priceTextWatcher)

            binding.orderName.isEnabled = false
            binding.baseCost.isEnabled = false
            binding.cost.isEnabled = true
        }
    }

    override fun onViewRecycled(holder: PriceItemViewHolder) {
        super.onViewRecycled(holder)
        holder.priceTextWatcher?.let { holder.binding.cost.removeTextChangedListener(it) }
        holder.priceTextWatcher = null
    }
}

class PriceItemDiffCallback : DiffUtil.ItemCallback<PriceItemDisplay>() {
    override fun areItemsTheSame(oldItem: PriceItemDisplay, newItem: PriceItemDisplay): Boolean {
        return oldItem.id == newItem.id && oldItem.isBaseItem == newItem.isBaseItem && oldItem.customerId == newItem.customerId
    }

    override fun areContentsTheSame(oldItem: PriceItemDisplay, newItem: PriceItemDisplay): Boolean {
        return oldItem == newItem
    }
}