package com.cumhurcugogunay.todo.ui.fragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.cumhurcugogunay.todo.R
import com.cumhurcugogunay.todo.databinding.FragmentAddToDoBinding
import com.cumhurcugogunay.todo.ui.viewmodel.AddToDoViewModel
import com.cumhurcugogunay.todo.ui.viewmodel.DetailsToDoViewModel
import com.cumhurcugogunay.todo.utils.goTo
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddToDoFragment : Fragment() {
    private lateinit var binding: FragmentAddToDoBinding
    private lateinit var viewModel: AddToDoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddToDoBinding.inflate(inflater, container, false)

        binding.buttonSave.setOnClickListener {
            val todo_title = binding.editTextTitle.text.toString()
            val todo_description = binding.editTextDescription.text.toString()
            val todo_priority = binding.autoCompleteTextViewPriority.text.toString()
            val todo_category = binding.autoCompleteTextViewCategory.text.toString()

            save(todo_title, todo_description, todo_priority, todo_category)

            Navigation.goTo(it,R.id.createToHome)
        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val statusBarColor = ContextCompat.getColor(requireContext(), R.color.inprogress)
        activity?.window?.statusBarColor = statusBarColor
    }

    fun save(todo_title:String,todo_description:String,
             todo_priority:String,todo_category:String){
        viewModel.save(todo_title,todo_description,todo_priority,todo_category)


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: AddToDoViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onResume() {
        super.onResume()
        val priorities = resources.getStringArray(R.array.priority)
        val arrayAdapter = ArrayAdapter(requireContext(),R.layout.dropdown_item,priorities)
        binding.autoCompleteTextViewPriority.setAdapter(arrayAdapter)

        val category = resources.getStringArray(R.array.category)
        val arrayAdapter2 = ArrayAdapter(requireContext(),R.layout.dropdown_item,category)
        binding.autoCompleteTextViewCategory.setAdapter(arrayAdapter2)
    }
}