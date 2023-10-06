package com.cumhurcugogunay.todo.utils

import android.view.View
import androidx.navigation.NavDirections
import androidx.navigation.Navigation

fun Navigation.goTo(view: View, id: Int){
    findNavController(view).navigate(id)
}

fun Navigation.goTo(view: View, id: NavDirections){
    findNavController(view).navigate(id)
}