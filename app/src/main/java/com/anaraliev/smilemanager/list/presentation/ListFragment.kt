package com.anaraliev.smilemanager.list.presentation

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anaraliev.smilemanager.R

class ListFragment : Fragment() {

    private val viewModel: ListFragmentViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        recyclerView = view.findViewById(R.id.card_list)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val dataList = listOf(
            ItemData("123", "2023-08-25", "A123", "Dr. Smith", "John Doe", "2024-08-25"),
            ItemData("123", "2023-08-25", "A123", "Dr. Smith", "John Doe", "2024-08-25"),
            ItemData("123", "2023-08-25", "A123", "Dr. Smith", "John Doe", "2024-08-25"),
            ItemData("123", "2023-08-25", "A123", "Dr. Smith", "John Doe", "2024-08-25"),
            ItemData("123", "2023-08-25", "A123", "Dr. Smith", "John Doe", "2024-08-25"),
            ItemData("123", "2023-08-25", "A123", "Dr. Smith", "John Doe", "2024-08-25"),
            ItemData("123", "2023-08-25", "A123", "Dr. Smith", "John Doe", "2024-08-25"),
            ItemData("123", "2023-08-25", "A123", "Dr. Smith", "John Doe", "2024-08-25"),
            ItemData("123", "2023-08-25", "A123", "Dr. Smith", "John Doe", "2024-08-25"),
            ItemData("123", "2023-08-25", "A123", "Dr. Smith", "John Doe", "2024-08-25"),
            ItemData("123", "2023-08-25", "A123", "Dr. Smith", "John Doe", "2024-08-25"),
            ItemData("123", "2023-08-25", "A123", "Dr. Smith", "John Doe", "2024-08-25"),
            ItemData("123", "2023-08-25", "A123", "Dr. Smith", "John Doe", "2024-08-25"),
            ItemData("123", "2023-08-25", "A123", "Dr. Smith", "John Doe", "2024-08-25"),
            ItemData("123", "2023-08-25", "A123", "Dr. Smith", "John Doe", "2024-08-25"),
            ItemData("123", "2023-08-25", "A123", "Dr. Smith", "John Doe", "2024-08-25"),
            ItemData("123", "2023-08-25", "A123", "Dr. Smith", "John Doe", "2024-08-25"),
            ItemData("123", "2023-08-25", "A123", "Dr. Smith", "John Doe", "2024-08-25"),
            ItemData("123", "2023-08-25", "A123", "Dr. Smith", "John Doe", "2024-08-25"),
            ItemData("123", "2023-08-25", "A123", "Dr. Smith", "John Doe", "2024-08-25"),
            ItemData("124", "2023-08-26", "A124", "Dr. Doe", "Jane Doe", "2024-08-26")
        )


        adapter = MyListAdapter(dataList)

        recyclerView.adapter = adapter

        return view
    }
}