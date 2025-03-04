package com.anaraliev.smilemanager.new_customer.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.anaraliev.smilemanager.databinding.FragmentNewCustomerBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewCustomerFragment : Fragment() {

    private val viewModel: NewCustomerViewModel by viewModel()

    private var _binding: FragmentNewCustomerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewCustomerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()
    }

    private fun setupListeners() {
        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.buttonOk.setOnClickListener {
            saveCustomer()
        }
    }

    private fun saveCustomer() {
        val name = binding.editTextCustomer.text.toString().trim()

        if (name.isNotEmpty()) {
            viewModel.addCustomer(name)
            findNavController().navigateUp()
        } else {
            binding.editTextCustomer.error = "Введите заказчика"
        }
    }

    private fun saveComments() {
        val name = binding.editTextCustomer.text.toString().trim()

        if (name.isNotEmpty()) {
            viewModel.addCustomer(name)
            findNavController().navigateUp()
        } else {
            binding.editTextCustomer.error = "Введите заказчика"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}