package com.cumhurcugogunay.todo.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.cumhurcugogunay.todo.data.entity.ToDos

@Dao
interface ToDosDao {
    @Query("SELECT * FROM todos")
    suspend fun showToDos() : List<ToDos>

    @Insert
    suspend fun save(kisiler: ToDos)

    @Update
    suspend fun update(kisiler: ToDos)

    @Delete
    suspend fun delete(kisiler: ToDos)

    @Query("SELECT * FROM todos WHERE todo_title like '%' || :searchingWord || '%'")
    suspend fun search(searchingWord:String) : List<ToDos>

    @Query("UPDATE todos SET todo_done = :newCheckBox WHERE todo_id = :todoId")
    suspend fun updateCheckBox(todoId: Int, newCheckBox: String)
}