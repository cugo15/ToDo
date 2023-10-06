package com.cumhurcugogunay.todo.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.cumhurcugogunay.todo.R
import com.cumhurcugogunay.todo.databinding.FragmentHomeBinding
import com.cumhurcugogunay.todo.ui.adapter.ToDoAdapter
import com.cumhurcugogunay.todo.ui.viewmodel.HomeViewModel
import com.cumhurcugogunay.todo.utils.goTo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.rv.layoutManager = LinearLayoutManager(requireContext())
        viewModel.toDosList.observe(viewLifecycleOwner){
            val toDoAdapter = ToDoAdapter(requireContext(),it,viewModel)
            binding.rv.adapter = toDoAdapter
        }

        binding.fab.setOnClickListener {
            Navigation.goTo(it,R.id.homeToCreate)
        }

        binding.searchToDo.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {//Harf girdikçe ve sildikçe çalışır
                search(newText!!)
                return true
            }
            override fun onQueryTextSubmit(query: String?): Boolean {//Arama iconuna basınca
                search(query!!)
                return false
            }
        })

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:HomeViewModel by viewModels()
        viewModel = tempViewModel
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val statusBarColor = ContextCompat.getColor(requireContext(), R.color.background)
        activity?.window?.statusBarColor = statusBarColor
    }

    override fun onResume() {
        super.onResume()
        viewModel.showToDos()
    }

    fun search(searchingWord:String){
        viewModel.search(searchingWord)
    }


}