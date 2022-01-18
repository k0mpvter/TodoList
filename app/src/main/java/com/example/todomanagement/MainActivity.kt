package com.example.todomanagement

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.ViewCompat
import androidx.core.view.ViewCompat.setBackgroundTintList
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

        taskmanager = TaskManager()

        tas_binding = NewTaskActivityBinding.inflate(layoutInflater)

        binding.buttonNewTask.setOnClickListener { view -> openNewTaskDisplay()}
        //tas_binding.buttonAddNewTask.setOnClickListener{ view -> doNowSth()}
    }

    fun showAllTasks(){
        taskmanager.showAllTasks()
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

    fun addNewTask(view: android.view.View) {
        val task_title: TextView = findViewById(R.id.task_title)
        val task_dueDate: TextView = findViewById(R.id.task_due_date)

        val title: String = task_title.text.toString()
        val dueDate: String = task_dueDate.text.toString()
        val description: String = tas_binding.taskDescription.toString()

        if (title.length == 0 || dueDate.length == 0){
            checkTaskDetails(task_title, task_dueDate)
            return
        }

        val task: Task = taskmanager.createNewTask(title, description, dueDate)
        taskmanager.addNewTask(task)

        showAllTasks()
        setContentView(binding.root)
    }

    fun checkTaskDetails(task_title: TextView, task_dueDate: TextView){

        val title = task_title.text.toString()
        val dueDate = task_dueDate.text.toString()

        if(title.length == 0) {
            ViewCompat.setBackgroundTintList(
                task_title,
                ColorStateList.valueOf(Color.parseColor("#FF0000")));
        } else {
            ViewCompat.setBackgroundTintList(
                task_title,
                ColorStateList.valueOf(Color.parseColor("#808080")));
        }

        if(dueDate.length == 0) {
            ViewCompat.setBackgroundTintList(
                task_dueDate,
                ColorStateList.valueOf(Color.parseColor("#FF0000")));
        } else {
            ViewCompat.setBackgroundTintList(
                task_dueDate,
                ColorStateList.valueOf(Color.parseColor("#808080")));
        }

    }
}