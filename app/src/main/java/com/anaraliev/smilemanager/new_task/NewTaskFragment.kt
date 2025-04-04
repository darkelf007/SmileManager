package com.anaraliev.smilemanager.new_task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anaraliev.smilemanager.R
import com.anaraliev.smilemanager.database.entity.CustomerEntity
import com.anaraliev.smilemanager.databinding.FragmentNewTaskBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewTaskFragment : Fragment(), PriceItemListener, CustomerSelectionListener {

    private val viewModel: NewTaskViewModel by viewModel()

    private var _binding: FragmentNewTaskBinding? = null
    private val binding get() = _binding!!

    private lateinit var priceItemAdapter: PriceItemAdapter
    private lateinit var customerAdapter: NewTaskCustomerAdapter

    private var navRecyclerView: RecyclerView? = null
    private var basePriceButton: Button? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupPriceItemsRecyclerView()
        setupCustomersRecyclerViewAndNavHeader()
        setupClickListeners()
        observeViewModel()
    }

    private fun setupPriceItemsRecyclerView() {
        priceItemAdapter = PriceItemAdapter(this)
        binding.appBarTask.recyclerViewOperations.layoutManager = LinearLayoutManager(requireContext())
        binding.appBarTask.recyclerViewOperations.adapter = priceItemAdapter
    }

    private fun setupCustomersRecyclerViewAndNavHeader() {
        customerAdapter = NewTaskCustomerAdapter(this)

        val headerView = binding.navView.getHeaderView(0)
        if (headerView != null) {
            navRecyclerView = headerView.findViewById(R.id.recycler_view_menu)
            basePriceButton = headerView.findViewById(R.id.button_base_price)

            if (navRecyclerView != null) {
                navRecyclerView?.layoutManager = LinearLayoutManager(requireContext())
                navRecyclerView?.adapter = customerAdapter
            }

            if (basePriceButton != null) {
                basePriceButton?.setOnClickListener {
                    viewModel.loadBasePrice()
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                }
            }
        }
    }

    private fun setupClickListeners() {
        binding.appBarTask.backButton.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.appBarTask.buttonSavePrice.setOnClickListener {
            viewModel.saveCurrentPriceList()
        }

        val drawerLayout = binding.drawerLayout
        binding.appBarTask.buttonPriceList.setOnClickListener {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.customers.collectLatest { customers ->
                        if (::customerAdapter.isInitialized && navRecyclerView?.adapter != null) {
                            customerAdapter.submitList(customers)
                        }
                    }
                }

                launch {
                    viewModel.currentPriceItems.collectLatest { priceItems ->
                        if (::priceItemAdapter.isInitialized) {
                            priceItemAdapter.submitList(priceItems)
                        }
                    }
                }

                launch {
                    viewModel.currentTitle.collectLatest { title ->
                        binding.appBarTask.textView.text = title
                    }
                }
            }
        }
    }

    override fun onPriceChanged(position: Int, newPrice: Double) {
        viewModel.updatePriceItem(position, newPrice)
    }

    override fun onCustomerSelected(customer: CustomerEntity) {
        viewModel.loadCustomerPrice(customer)
        binding.drawerLayout.closeDrawer(GravityCompat.START)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.appBarTask.recyclerViewOperations.adapter = null
        navRecyclerView?.adapter = null
        navRecyclerView = null
        basePriceButton = null
        _binding = null
    }
}