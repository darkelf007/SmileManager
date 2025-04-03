package com.anaraliev.smilemanager.new_task

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.anaraliev.smilemanager.databinding.NewTaskCardBinding
// Import your specific binding class generated from task_operation_card.xml

interface PriceItemListener {
    // Position might be unreliable if list changes frequently during edits, consider using ID
    fun onPriceChanged(position: Int, newPrice: Double)
}

class PriceItemAdapter(private val listener: PriceItemListener) :
    ListAdapter<PriceItemDisplay, PriceItemAdapter.PriceItemViewHolder>(PriceItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PriceItemViewHolder {
        // Inflate your existing layout
        val binding = NewTaskCardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        // Pass the binding AND the listener to the ViewHolder constructor
        return PriceItemViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: PriceItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    // ViewHolder using your TaskOperationCardBinding
    inner class PriceItemViewHolder(
         val binding: NewTaskCardBinding,
         val listener: PriceItemListener // Pass listener here
    ) : RecyclerView.ViewHolder(binding.root) {

        private var currentItem: PriceItemDisplay? = null
        // Store the watcher to remove it later
         var priceTextWatcher: TextWatcher? = null

        fun bind(item: PriceItemDisplay) {
            currentItem = item

            // --- Bind data to views ---
            binding.orderName.setText(item.workName)
            // Display the reference base price (read-only)
            binding.baseCost.setText(item.basePriceForReference?.toString() ?: "-") // Use "-" if no base price

            // --- Handle the editable price field ('cost') ---
            // Remove previous watcher *before* setting text
            priceTextWatcher?.let { binding.cost.removeTextChangedListener(it) }

            // Set the current price (base or customer)
            binding.cost.setText(item.price.toString())

            // Add new watcher *after* setting text
            priceTextWatcher = object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable?) {
                    // Avoid infinite loops or premature updates if possible
                    val position = bindingAdapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val newPrice = s?.toString()?.toDoubleOrNull()
                        if (newPrice != null && newPrice >= 0) {
                            // Only notify if the parsed price is different from the item's current price
                            // This helps prevent loops if setText triggers the watcher again
                            if (newPrice != currentItem?.price) {
                                Log.d("Adapter", "TextWatcher: Price changed at pos $position to $newPrice")
                                listener.onPriceChanged(position, newPrice)
                            }
                        } else {
                            // Handle invalid input maybe? Or just let the listener decide.
                            // Potentially revert the text or show an error?
                            Log.w("Adapter", "TextWatcher: Invalid price input '$s' at pos $position")
                            // listener.onPriceChanged(position, currentItem?.price ?: 0.0) // Revert?
                        }
                    }
                }
            }
            binding.cost.addTextChangedListener(priceTextWatcher)

            // --- Set Editability ---
            binding.orderName.isEnabled = false // Name is not editable here
            binding.baseCost.isEnabled = false  // Reference base cost is not editable
            binding.cost.isEnabled = true       // The current price is editable
        }
    }

    // Clean up watcher when view is recycled
    override fun onViewRecycled(holder: PriceItemViewHolder) {
        super.onViewRecycled(holder)
        holder.priceTextWatcher?.let { holder.binding.cost.removeTextChangedListener(it) }
        holder.priceTextWatcher = null
    }
}

// DiffUtil comparing the updated PriceItemDisplay
class PriceItemDiffCallback : DiffUtil.ItemCallback<PriceItemDisplay>() {
    override fun areItemsTheSame(oldItem: PriceItemDisplay, newItem: PriceItemDisplay): Boolean {
        // Use a combination that uniquely identifies the item in its context
        return oldItem.id == newItem.id && oldItem.isBaseItem == newItem.isBaseItem && oldItem.customerId == newItem.customerId
    }

    override fun areContentsTheSame(oldItem: PriceItemDisplay, newItem: PriceItemDisplay): Boolean {
        // Compare all fields that affect display
        return oldItem == newItem
    }
}