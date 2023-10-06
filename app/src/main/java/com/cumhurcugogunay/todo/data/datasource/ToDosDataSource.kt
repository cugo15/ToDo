package com.cumhurcugogunay.todo.data.datasource

import com.cumhurcugogunay.todo.data.entity.ToDos
import com.cumhurcugogunay.todo.room.ToDosDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ToDosDataSource(var toDosDao: ToDosDao) {

    suspend fun save(todo_title:String,todo_description:String,
                     todo_priority:String,todo_category:String){

        val newToDo = ToDos(0,todo_title,todo_description,
            todo_priority,todo_category,"inprogress")

        toDosDao.save(newToDo)
    }
    suspend fun update(todo_id:Int,todo_title:String,
                       todo_description:String,
                       todo_priority:String,todo_category:String){

        val updatedToDo = ToDos(todo_id,todo_title,todo_description,
            todo_priority,todo_category,"inprogress")

        toDosDao.update(updatedToDo)
    }


    suspend fun updateCheckBox(todoId: Int, newCheckBox: String){
        toDosDao.updateCheckBox(todoId,newCheckBox)
    }

    suspend fun delete(todo_id:Int){

        val deletedToDo = ToDos(todo_id,"","",
            "","","")

        toDosDao.delete(deletedToDo)
    }

    suspend fun showToDos() : List<ToDos> = withContext(Dispatchers.IO){
        return@withContext toDosDao.showToDos()
    }

    suspend fun search(searchingWord:String) : List<ToDos> = withContext(Dispatchers.IO){
        return@withContext toDosDao.search(searchingWord)
    }
}