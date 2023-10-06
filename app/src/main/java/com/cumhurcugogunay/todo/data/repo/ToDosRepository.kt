package com.cumhurcugogunay.todo.data.repo

import com.cumhurcugogunay.todo.data.datasource.ToDosDataSource
import com.cumhurcugogunay.todo.data.entity.ToDos

class ToDosRepository(var toDosDataSource: ToDosDataSource) {

    suspend fun save(todo_title:String,
                     todo_description:String,
                     todo_priority:String,todo_category:String)
    = toDosDataSource.save(todo_title, todo_description,
        todo_priority,todo_category)

    suspend fun update(todo_id:Int,todo_title:String,
                       todo_description:String,
                       todo_priority:String,todo_category:String)
    = toDosDataSource.update(todo_id, todo_title ,
      todo_description,todo_priority,todo_category)

    suspend fun updateCheckBox(todoId: Int, newCheckBox: String) = toDosDataSource.updateCheckBox(todoId, newCheckBox)

    suspend fun delete(todo_id:Int) = toDosDataSource.delete(todo_id)

    suspend fun showToDos() : List<ToDos> = toDosDataSource.showToDos()

    suspend fun search(searchingWord:String) : List<ToDos> = toDosDataSource.search(searchingWord)
}