package com.anaraliev.smilemanager.new_task

import android.os.Bundle
import android.util.Log // For logging if needed
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anaraliev.smilemanager.R
import com.anaraliev.smilemanager.database.entity.CustomerEntity // Keep this if needed for TaskOperation
import com.anaraliev.smilemanager.databinding.FragmentNewTaskBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel // Koin ViewModel delegate

class NewTaskFragment : Fragment() {

    // Inject ViewModel using Koin
    private val viewModel: NewTaskViewModel by viewModel()

    private var _binding: FragmentNewTaskBinding? = null
    private val binding get() = _binding!!

    private lateinit var taskOperationAdapter: TaskOperationAdapter
    private lateinit var customerAdapter: NewTaskCustomerAdapter

    private var navRecyclerView: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupTaskOperationsRecyclerView()
        setupCustomersRecyclerView()
        setupClickListeners()
        loadInitialTaskData()
        observeViewModel()
    }

    private fun setupTaskOperationsRecyclerView() {
        taskOperationAdapter = TaskOperationAdapter()
        binding.appBarTask.recyclerViewOperations.layoutManager =
            LinearLayoutManager(requireContext())
        binding.appBarTask.recyclerViewOperations.adapter = taskOperationAdapter
    }

    private fun setupCustomersRecyclerView() {
        customerAdapter = NewTaskCustomerAdapter()

        val headerView = binding.navView.getHeaderView(0)

        if (headerView != null) {
            navRecyclerView = headerView.findViewById(R.id.recycler_view_menu)

            if (navRecyclerView != null) {
                navRecyclerView?.layoutManager = LinearLayoutManager(requireContext())
                navRecyclerView?.adapter = customerAdapter
                Log.d("NewTaskFragment", "Customer RecyclerView setup successful in header.")
            } else {
                Log.e(
                    "NewTaskFragment",
                    "RecyclerView (recycler_view_menu) not found INSIDE NavigationView header. Check header layout ID."
                )
            }
        } else {
            Log.e(
                "NewTaskFragment", "NavigationView header view not found. Is app:headerLayout set?"
            )
        }
    }

    private fun setupClickListeners() {
        binding.appBarTask.backButton.setOnClickListener {
            findNavController().navigateUp()
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

    private fun loadInitialTaskData() {
        val sampleOperations = listOf(
            TaskOperation(name = "Цирконий", cost = "400", baseType = "400")
            // Add more sample operations if needed
        )
        taskOperationAdapter.submitList(sampleOperations)
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.customers.collect { customers ->
                    Log.d("NewTaskFragment", "Observed customers: ${customers.size}")
                    if (::customerAdapter.isInitialized && navRecyclerView?.adapter != null) {
                        customerAdapter.submitList(customers)
                        Log.d(
                            "NewTaskFragment", "Submitted ${customers.size} customers to adapter."
                        )
                    } else {
                        Log.w(
                            "NewTaskFragment",
                            "CustomerAdapter or navRecyclerView not ready when data arrived."
                        )
                    }
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.appBarTask.recyclerViewOperations.adapter = null
        navRecyclerView?.adapter = null
        navRecyclerView = null
        _binding = null
        Log.d("NewTaskFragment", "onDestroyView: Binding and adapters cleared.")
    }
}