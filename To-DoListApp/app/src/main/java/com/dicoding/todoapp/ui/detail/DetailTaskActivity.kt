package com.dicoding.todoapp.ui.detail

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.todoapp.R
import com.dicoding.todoapp.data.Task
import com.dicoding.todoapp.ui.ViewModelFactory
import com.dicoding.todoapp.ui.list.TaskViewModel
import com.dicoding.todoapp.utils.DateConverter
import com.dicoding.todoapp.utils.TASK_ID
import com.google.android.material.textfield.TextInputEditText

class DetailTaskActivity : AppCompatActivity() {

    private val DetailaskViewModel: DetailTaskViewModel by viewModels{
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        //TODO 11 : Show detail task and implement delete action
        val taskExtras = intent.extras
        if(taskExtras!= null){
        val idTask = taskExtras.getInt(TASK_ID)
            DetailaskViewModel.setTaskId(idTask)

            // Implement Detail action
            DetailaskViewModel.task.observe(this){ taskDetail ->
                populateTask(taskDetail)
                findViewById<Button>(R.id.btn_delete_task).setOnClickListener {
                    DetailaskViewModel.deleteTask()
                    finish()
                }
            }
        }
    }

    // Show Detail Task
    private fun populateTask(task: Task?){
    val edtTitleTask = findViewById<TextInputEditText>(R.id.detail_ed_title)
    val edtDescriptionTask = findViewById<TextInputEditText>(R.id.detail_ed_description)
    val edtDueDateTask = findViewById<TextInputEditText>(R.id.detail_ed_due_date)
        if(task != null){
            edtTitleTask .setText(task.title)
            edtDescriptionTask.setText(task.description)
            edtDueDateTask.setText(DateConverter.convertMillisToString(task.dueDateMillis))
        }
    }
}