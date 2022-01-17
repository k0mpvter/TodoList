package com.example.todomanagement

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import com.example.todomanagement.databinding.ActivityMainBinding
import com.example.todomanagement.databinding.NewTaskActivityBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.time.measureTimedValue

class MainActivity : AppCompatActivity() {

    private lateinit var taskmanager: TaskManager
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var tas_binding: NewTaskActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        tas_binding = NewTaskActivityBinding.inflate(layoutInflater)

        binding.buttonNewTask.setOnClickListener { view -> openNewTaskDisplay()}
        tas_binding.buttonAddNewTask.setOnClickListener{ view -> addNewTask()}
    }

    fun showAllTasks(){
        taskmanager.showAllTasks()
    }

    fun addNewTask(){
        val title: String = tas_binding.taskTitle.toString()
        val description: String = tas_binding.taskDescription.toString()
        val dueDate: String = tas_binding.taskDueDate.toString()

        //val task: Task = taskmanager.createNewTask(title, description, dueDate)
        //taskmanager.addNewTask(task)

        showAllTasks()
        setContentView(R.layout.activity_main)
    }

    fun openNewTaskDisplay(){
        setContentView(R.layout.new_task_activity)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when(item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
    val navController = findNavController(R.id.nav_host_fragment_content_main)
    return navController.navigateUp(appBarConfiguration)
            || super.onSupportNavigateUp()
    }
}