package com.anaraliev.smilemanager.edit_customer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.anaraliev.smilemanager.databinding.FragmentEditCustomerBinding
import com.anaraliev.smilemanager.utils.BUNDLE_KEY_CUSTOMER_ID
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditCustomerFragment : Fragment() {

    private val viewModel: EditCustomerViewModel by viewModel()

    private var _binding: FragmentEditCustomerBinding? = null
    private val binding get() = _binding!!

    private var currentCustomerId: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditCustomerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            currentCustomerId = it.getInt(BUNDLE_KEY_CUSTOMER_ID, -1)
            if (currentCustomerId != -1) {
                viewModel.loadCustomer(currentCustomerId)
            } else {
                Toast.makeText(context, "Ошибка: ID заказчика не найден", Toast.LENGTH_LONG).show()
                findNavController().navigateUp()
            }
        }

        setupObservers()
        setupListeners()
    }

    private fun setupObservers() {
        viewModel.customer.observe(viewLifecycleOwner) { customer ->
            customer?.let {
                binding.editTextCustomer.setText(it.customerName)
                binding.editTextContactInfo.setText(it.contactNumberComments ?: "")
                binding.editTextDeliveryInfo.setText(it.address ?: "")
                binding.editTextEmail.setText(it.email ?: "")
                binding.editTextPercentage.setText(it.percentage?.toString() ?: "")
            }
        }

        viewModel.updateResult.observe(viewLifecycleOwner) { result ->
            result.fold(
                onSuccess = {
                    Toast.makeText(context, "Заказчик обновлен", Toast.LENGTH_SHORT).show()
                    findNavController().navigateUp()
                },
                onFailure = {
                    Toast.makeText(context, "Ошибка обновления: ${it.message}", Toast.LENGTH_LONG).show()
                }
            )
        }
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
        if (currentCustomerId == -1) {
            Toast.makeText(context, "Невозможно сохранить: ID заказчика не определен", Toast.LENGTH_LONG).show()
            return
        }

        val name = binding.editTextCustomer.text.toString().trim()
        val contactInfo = binding.editTextContactInfo.text.toString().trim()
        val address = binding.editTextDeliveryInfo.text.toString().trim()
        val email = binding.editTextEmail.text.toString().trim()
        val percentageText = binding.editTextPercentage.text?.toString()?.trim() ?: ""

        if (name.isEmpty()) {
            binding.editTextCustomer.error = "Введите заказчика"
            return
        }

        val percentage: Int?
        if (percentageText.isNotEmpty()) {
            try {
                val p = percentageText.toInt()
                if (p !in 0..100) {
                    binding.editTextPercentage.error = "Должно быть от 0 до 100"
                    return
                }
                percentage = p
            } catch (e: NumberFormatException) {
                binding.editTextPercentage.error = "Некорректное число"
                return
            }
        } else {
            percentage = null
        }
        binding.editTextPercentage.error = null
        binding.editTextCustomer.error = null


        viewModel.updateCustomer(
            uid = currentCustomerId,
            name = name,
            contactInfo = contactInfo.ifEmpty { null },
            address = address.ifEmpty { null },
            email = email.ifEmpty { null },
            percentage = percentage
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}