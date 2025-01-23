package com.anaraliev.smilemanager.list.presentation

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.anaraliev.smilemanager.R
import com.anaraliev.smilemanager.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private val viewModel: ListFragmentViewModel by viewModels()
    private lateinit var adapter: MyListAdapter
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cardList.layoutManager = LinearLayoutManager(requireContext())

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
            ItemData("124", "2023-08-26", "A124", "Dr. Smith", "Jane Doe", "2024-08-26")
        )

        adapter = MyListAdapter(dataList)
        binding.cardList.adapter = adapter

        binding.buttonNewOrder.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_newOrderFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}