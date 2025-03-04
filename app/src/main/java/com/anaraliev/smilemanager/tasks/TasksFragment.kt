package com.anaraliev.smilemanager.tasks

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.anaraliev.smilemanager.R
import com.anaraliev.smilemanager.utils.REQUEST_KEY_NEW_TASK

class TasksFragment : Fragment() {


    private val viewModel: TasksViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_tasks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonNewCustomer = view.findViewById<Button>(R.id.button_new_task)
        buttonNewCustomer.setOnClickListener {
            requireParentFragment().childFragmentManager.setFragmentResult(REQUEST_KEY_NEW_TASK, Bundle())
        }

    }
}