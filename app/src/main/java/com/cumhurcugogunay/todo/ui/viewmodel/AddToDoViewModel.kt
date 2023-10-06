package com.cumhurcugogunay.todo.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.cumhurcugogunay.todo.data.repo.ToDosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddToDoViewModel @Inject constructor(var toDosRepository: ToDosRepository) : ViewModel(){

    fun save(todo_title:String,
             todo_description:String,
             todo_priority:String,todo_category:String){

        CoroutineScope(Dispatchers.Main).launch {
            toDosRepository.save(todo_title, todo_description,
                todo_priority,todo_category)
        }
    }
}