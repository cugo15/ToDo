package com.cumhurcugogunay.todo.di

import android.content.Context
import androidx.room.Room
import com.cumhurcugogunay.todo.data.datasource.ToDosDataSource
import com.cumhurcugogunay.todo.data.repo.ToDosRepository
import com.cumhurcugogunay.todo.room.ToDosDao
import com.cumhurcugogunay.todo.room.ToDosDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {


    @Provides
    @Singleton
    fun provideToDosRepository(toDosDataSource: ToDosDataSource) : ToDosRepository {
        return ToDosRepository(toDosDataSource)
    }
    @Provides
    @Singleton
    fun provideToDosDataSource(toDosDao: ToDosDao) : ToDosDataSource {
        return ToDosDataSource(toDosDao)
    }

    @Provides
    @Singleton
    fun provideToDosDao(@ApplicationContext context: Context) : ToDosDao {
        val vt = Room.databaseBuilder(context, ToDosDatabase::class.java,"todos.sqlite")
            .createFromAsset("todos.sqlite").build()
        return vt.getToDosDao()
    }
}