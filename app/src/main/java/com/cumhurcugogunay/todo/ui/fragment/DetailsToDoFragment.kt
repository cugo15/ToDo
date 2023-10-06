package com.cumhurcugogunay.todo.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.cumhurcugogunay.todo.R
import com.cumhurcugogunay.todo.databinding.FragmentDetailsToDoBinding
import com.cumhurcugogunay.todo.ui.viewmodel.DetailsToDoViewModel
import com.cumhurcugogunay.todo.utils.goTo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsToDoFragment : Fragment() {

    private lateinit var binding: FragmentDetailsToDoBinding
    private lateinit var viewModel: DetailsToDoViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsToDoBinding.inflate(inflater, container, false)

        val bundle:DetailsToDoFragmentArgs by navArgs()
        val todoFromHome = bundle.todo
        binding.editTextTitle.setText(todoFromHome.todo_title)
        binding.editTextDescription.setText(todoFromHome.todo_description)
        binding.autoCompleteTextViewPriority.setText(todoFromHome.todo_priority)
        binding.autoCompleteTextViewCategory.setText(todoFromHome.todo_category)

        binding.buttonUpdate.setOnClickListener {
            val todo_title = binding.editTextTitle.text.toString()
            val todo_description = binding.editTextDescription.text.toString()
            val todo_priority = binding.autoCompleteTextViewPriority.text.toString()
            val todo_category = binding.autoCompleteTextViewCategory.text.toString()
            update(todoFromHome.todo_id,todo_title, todo_description, todo_priority, todo_category)

            Navigation.goTo(it,R.id.detailsToHome)

        }


        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:DetailsToDoViewModel by viewModels()
        viewModel = tempViewModel
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val statusBarColor = ContextCompat.getColor(requireContext(), R.color.completed)
        activity?.window?.statusBarColor = statusBarColor
    }

    fun update(todo_id:Int,todo_title:String,
               todo_description:String,
               todo_priority:String,todo_category:String){
        viewModel.update(todo_id, todo_title, todo_description, todo_priority, todo_category)


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