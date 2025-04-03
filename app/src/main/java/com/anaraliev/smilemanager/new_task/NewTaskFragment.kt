package com.anaraliev.smilemanager.new_task

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.anaraliev.smilemanager.new_task.CustomerSelectionListener
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
        setupCustomersRecyclerViewAndNavHeader() // Combine setup for nav drawer
        setupClickListeners()
        observeViewModel() // Observe ViewModel changes

        // No need for loadInitialTaskData with dummy data now
    }

    private fun setupPriceItemsRecyclerView() {
        // Pass 'this' as the listener
        priceItemAdapter = PriceItemAdapter(this)
        binding.appBarTask.recyclerViewOperations.layoutManager = LinearLayoutManager(requireContext())
        binding.appBarTask.recyclerViewOperations.adapter = priceItemAdapter
    }

    private fun setupCustomersRecyclerViewAndNavHeader() {
        // Pass 'this' as the listener
        customerAdapter = NewTaskCustomerAdapter(this)

        val headerView = binding.navView.getHeaderView(0)
        if (headerView != null) {
            navRecyclerView = headerView.findViewById(R.id.recycler_view_menu)
            basePriceButton = headerView.findViewById(R.id.button_base_price) // Find Base Price button

            if (navRecyclerView != null) {
                navRecyclerView?.layoutManager = LinearLayoutManager(requireContext())
                navRecyclerView?.adapter = customerAdapter
                Log.d("NewTaskFragment", "Customer RecyclerView setup successful in header.")
            } else {
                Log.e("NewTaskFragment", "RecyclerView (recycler_view_menu) not found in header.")
            }

            // Set listener for Base Price button
            if (basePriceButton != null) {
                basePriceButton?.setOnClickListener {
                    viewModel.loadBasePrice() // Tell ViewModel to load base prices
                    binding.drawerLayout.closeDrawer(GravityCompat.START) // Close drawer
                    Log.d("NewTaskFragment", "Base Price button clicked.")
                }
            } else {
                Log.e("NewTaskFragment", "Button (button_base_price) not found in header.")
            }

        } else {
            Log.e("NewTaskFragment", "NavigationView header view not found.")
        }
    }

    private fun setupClickListeners() {
        binding.appBarTask.backButton.setOnClickListener {
            findNavController().navigateUp()
        }

        // Save button listener
        binding.appBarTask.buttonSavePrice.setOnClickListener { // Use the ID of the new save button
            viewModel.saveCurrentPriceList()
            // Optionally: show feedback, navigate back, etc.
            // Toast.makeText(requireContext(), "Сохранено", Toast.LENGTH_SHORT).show()
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
                // Observe customers for the nav drawer
                launch {
                    viewModel.customers.collectLatest { customers ->
                        Log.d("NewTaskFragment", "Observed customers: ${customers.size}")
                        if (::customerAdapter.isInitialized && navRecyclerView?.adapter != null) {
                            customerAdapter.submitList(customers)
                            Log.d("NewTaskFragment", "Submitted ${customers.size} customers to adapter.")
                        } else {
                            Log.w("NewTaskFragment", "CustomerAdapter or navRecyclerView not ready.")
                        }
                    }
                }

                // Observe the currently displayed price items
                launch {
                    viewModel.currentPriceItems.collectLatest { priceItems ->
                        Log.d("NewTaskFragment", "Observed price items: ${priceItems.size}")
                        if (::priceItemAdapter.isInitialized) {
                            priceItemAdapter.submitList(priceItems)
                            Log.d("NewTaskFragment", "Submitted ${priceItems.size} price items.")
                        } else {
                            Log.w("NewTaskFragment", "PriceItemAdapter not ready.")
                        }
                    }
                }

                // Observe the title
                launch {
                    viewModel.currentTitle.collectLatest { title ->
                        binding.appBarTask.textView.text = title
                        Log.d("NewTaskFragment", "AppBar title updated: $title")
                    }
                }
            }
        }

        // Alternative using asLiveData if preferred
        // viewModel.currentPriceItems.asLiveData().observe(viewLifecycleOwner) { priceItems -> ... }
        // viewModel.currentTitle.asLiveData().observe(viewLifecycleOwner) { title -> ... }
    }

    // --- Implementation of Listener Interfaces ---

    // From PriceItemListener
    override fun onPriceChanged(position: Int, newPrice: Double) {
        // Debounce this if performance becomes an issue
        Log.d("NewTaskFragment", "Price changed at position $position to $newPrice")
        viewModel.updatePriceItem(position, newPrice)
    }

    // From CustomerSelectionListener
    override fun onCustomerSelected(customer: CustomerEntity) {
        Log.d("NewTaskFragment", "Customer selected: ${customer.customerName} (ID: ${customer.uid})")
        viewModel.loadCustomerPrice(customer) // Tell ViewModel to load this customer's price
        binding.drawerLayout.closeDrawer(GravityCompat.START) // Close drawer
    }

    // --- Lifecycle ---

    override fun onDestroyView() {
        super.onDestroyView()
        binding.appBarTask.recyclerViewOperations.adapter = null // Clear adapter reference
        navRecyclerView?.adapter = null // Clear adapter reference
        navRecyclerView = null
        basePriceButton = null
        _binding = null
        Log.d("NewTaskFragment", "onDestroyView: Binding and adapters cleared.")
    }
}