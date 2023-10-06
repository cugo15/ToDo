package com.cumhurcugogunay.todo.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.cumhurcugogunay.todo.R
import com.cumhurcugogunay.todo.data.entity.ToDos
import com.cumhurcugogunay.todo.databinding.TodoRowBinding
import com.cumhurcugogunay.todo.ui.fragment.HomeFragmentDirections
import com.cumhurcugogunay.todo.ui.viewmodel.HomeViewModel
import com.cumhurcugogunay.todo.utils.goTo
import com.google.android.material.snackbar.Snackbar

class ToDoAdapter (var mContext: Context, var todoList: List<ToDos>, var viewModel
: HomeViewModel):RecyclerView.Adapter<ToDoAdapter.ToDoHolder>(){

    inner class ToDoHolder(var binding: TodoRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoHolder {
        val binding = TodoRowBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return ToDoHolder(binding)
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    override fun onBindViewHolder(holder: ToDoHolder, position: Int) {
        val toDo = todoList[position]
        val t = holder.binding

        if(toDo.todo_done == "inprogress"){
            t.checkBoxToDo.isChecked = false
            t.cardRow.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white))
        }
        else if (toDo.todo_done == "done"){
            t.checkBoxToDo.isChecked = true
            t.cardRow.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.completed))
        }

        loadToDo(t,toDo)
        colorCP(t,toDo)
        t.imageViewDelete.setOnClickListener {
            Snackbar.make(it,"Do you want to delete ${toDo.todo_title}?", Snackbar.LENGTH_SHORT)
                .setAction("Yes"){
                    delete(toDo.todo_id)
                }.setActionTextColor(ContextCompat.getColor(mContext, R.color.background)).show()
        }
        t.cardRow.setOnClickListener{
            val toDetails = HomeFragmentDirections.homeToDetails(todo = toDo)
            Navigation.goTo(it,toDetails)
        }

        t.checkBoxToDo.setOnClickListener {
            if(toDo.todo_done == "inprogress"){
                updateCheckBox(toDo.todo_id,"done")
                t.cardRow.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.completed))
            }
            else if (toDo.todo_done == "done"){
                updateCheckBox(toDo.todo_id,"inprogress")
                t.cardRow.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white))
            }
        }




    }

    fun delete(todo_id:Int){
        viewModel.delete(todo_id)
    }
    fun loadToDo(binding: TodoRowBinding, toDo: ToDos){
        binding.textViewTitle.text = toDo.todo_title
        binding.textViewDescription.text = toDo.todo_description
        binding.textViewCategory.text = toDo.todo_category
        binding.textViewPriority.text = toDo.todo_priority
    }
    fun colorCP(binding: TodoRowBinding, toDo: ToDos){

        when (toDo.todo_priority) {
            "Low Priority" -> {
                binding.cardViewPriority.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.lowpriority))
            }
            "Medium Priority" -> {
                binding.cardViewPriority.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.mediumpriority))
            }
            else -> {
                binding.cardViewPriority.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.highpriority))
            }
        }

        when (toDo.todo_category) {
            "Home" -> {
                binding.cardViewCategory.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.home))
            }
            "Personal" -> {
                binding.cardViewCategory.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.personal))
            }
            "Other" -> {
                binding.cardViewCategory.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.other))
            }
            else -> {
                binding.cardViewCategory.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.work))
            }
        }
    }

    fun changeBox(binding: TodoRowBinding,toDo: ToDos){
        if(toDo.todo_done == "inprogress"){
            updateCheckBox(toDo.todo_id,"done")
            binding.cardRow.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.completed))
        }
        else if (toDo.todo_done == "done"){
            updateCheckBox(toDo.todo_id,"inprogress")
            binding.cardRow.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white))
        }
    }

    fun updateCheckBox(todo_id:Int,newCheckBox:String) {
        viewModel.updateCheckBox(todo_id,newCheckBox)
    }

}