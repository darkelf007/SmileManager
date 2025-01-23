package com.anaraliev.smilemanager.guide.tasks.new_task

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.anaraliev.smilemanager.R

class NewTaskFragment : Fragment() {

    companion object {
        fun newInstance() = NewTaskFragment()
    }

    private val viewModel: NewTaskViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_new_task, container, false)
    }
}