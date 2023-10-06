package com.cumhurcugogunay.todo.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cumhurcugogunay.todo.data.entity.ToDos

@Database(entities = [ToDos::class], version = 1)
abstract class ToDosDatabase : RoomDatabase() {
    abstract fun getToDosDao() : ToDosDao
}