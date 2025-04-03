package com.anaraliev.smilemanager.new_task

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.anaraliev.smilemanager.R


class TaskOperationAdapter : RecyclerView.Adapter<TaskOperationViewHolder>() {

    private var operations: List<TaskOperation> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskOperationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.new_task_card, parent, false)
        return TaskOperationViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskOperationViewHolder, position: Int) {
        val operation = operations[position]
        holder.bind(operation)
    }

    override fun getItemCount(): Int {
        return operations.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(newOperations: List<TaskOperation>) {
        operations = newOperations
        notifyDataSetChanged()
    }
}

class TaskOperationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val nameEditText: EditText = itemView.findViewById(R.id.order_name)
    private val costEditText: EditText = itemView.findViewById(R.id.cost)
    private val baseCostEditText: EditText = itemView.findViewById(R.id.base_cost)

    fun bind(taskOperation: TaskOperation) {
        nameEditText.setText(taskOperation.name)
        costEditText.setText(taskOperation.cost)
        baseCostEditText.setText(taskOperation.baseType)
    }
}