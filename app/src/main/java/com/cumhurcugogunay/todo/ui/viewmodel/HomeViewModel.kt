package com.cumhurcugogunay.todo.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cumhurcugogunay.todo.data.entity.ToDos
import com.cumhurcugogunay.todo.data.repo.ToDosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(var toDosRepository: ToDosRepository) : ViewModel(){
    var toDosList = MutableLiveData<List<ToDos>>()

    init {
        showToDos()
    }

    fun delete(todo_id:Int){
        CoroutineScope(Dispatchers.Main).launch {
            toDosRepository.delete(todo_id)
            showToDos()
        }
    }
    fun updateCheckBox(todoId: Int, newCheckBox: String){
        CoroutineScope(Dispatchers.Main).launch {
            toDosRepository.updateCheckBox(todoId, newCheckBox)
            showToDos()
        }
    }
    fun showToDos(){
        CoroutineScope(Dispatchers.Main).launch{
            toDosList.value = toDosRepository.showToDos()
        }
    }

    fun search(searchingWord:String){
        CoroutineScope(Dispatchers.Main).launch{
            toDosList.value = toDosRepository.search(searchingWord)
        }
    }
}