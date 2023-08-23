package com.example.todolist
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var taskEditText: EditText
    private lateinit var addButton: Button
    private lateinit var taskListView: ListView

    private lateinit var taskList: MutableList<Task>
    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        taskEditText = findViewById(R.id.taskEditText)
        addButton = findViewById(R.id.addButton)
        taskListView = findViewById(R.id.taskListView)

        taskList = mutableListOf()
        adapter = TaskAdapter(this, taskList)
        taskListView.adapter = adapter

        addButton.setOnClickListener {
            val taskText = taskEditText.text.toString()
            if (taskText.isNotBlank()) {
                val newTask = Task(taskText)
                taskList.add(newTask)
                adapter.notifyDataSetChanged()
                taskEditText.text.clear()
            }
        }

        taskListView.setOnItemClickListener { _, _, position, _ ->
            val task = taskList[position]
            task.isCompleted = !task.isCompleted
            adapter.notifyDataSetChanged()
        }

        taskListView.setOnItemLongClickListener { _, _, position, _ ->
            taskList.removeAt(position)
            adapter.notifyDataSetChanged()
            true
        }
    }
}




