package com.anaraliev.smilemanager.list.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.anaraliev.smilemanager.R


class MyListAdapter(private val dataList: List<ItemData>) : RecyclerView.Adapter<MyListAdapter.ListViewHolder>() {

    // Класс ViewHolder, содержащий ссылки на элементы макета
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val idTextView: TextView = itemView.findViewById(R.id.ID)
        val dateTextView: TextView = itemView.findViewById(R.id.date)
        val orderTextView: TextView = itemView.findViewById(R.id.order)
        val dentistTextView: TextView = itemView.findViewById(R.id.dentist)
        val patienceTextView: TextView = itemView.findViewById(R.id.patience)
        val dateExpirationTextView: TextView = itemView.findViewById(R.id.date_expiration)
    }

    // Создание ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_card, parent, false)
        return ListViewHolder(view)
    }

    // Привязка данных к ViewHolder
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val itemData = dataList[position]
        holder.idTextView.text = itemData.id
        holder.dateTextView.text = itemData.date
        holder.orderTextView.text = itemData.order
        holder.dentistTextView.text = itemData.dentist
        holder.patienceTextView.text = itemData.patience
        holder.dateExpirationTextView.text = itemData.dateExpiration
    }

    // Возвращает количество элементов в списке
    override fun getItemCount(): Int {
        return dataList.size
    }
}

