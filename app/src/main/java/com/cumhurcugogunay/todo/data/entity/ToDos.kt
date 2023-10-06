package com.cumhurcugogunay.todo.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Entity(tableName = "todos")
data class ToDos(@PrimaryKey(autoGenerate = true)
                   @ColumnInfo(name = "todo_id")@NotNull var todo_id:Int,
                   @ColumnInfo(name = "todo_title")@NotNull var todo_title:String,
                   @ColumnInfo(name = "todo_description")@NotNull var todo_description:String,
                   @ColumnInfo(name = "todo_priority")@NotNull var todo_priority:String,
                   @ColumnInfo(name = "todo_category")@NotNull var todo_category:String,
                   @ColumnInfo(name = "todo_done")@NotNull var todo_done:String) : Serializable {
}