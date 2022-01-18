package com.example.todomanagement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TaskManager {
    private ArrayList<Task> tasks;
    //private val tasks = arrayListOf<Task>()

    public TaskManager(){
        tasks = new ArrayList<>();
    }

    public void showAllTasks(){
        System.out.println("HERERERERE");
        for (Task task:tasks) {
            System.out.println(task.getName());
        }
    }

    public Task createNewTask(String title, String description, Date dueDate) throws ParseException {

        Task task = new Task(title, dueDate);
        if(description.length() > 0){
            task.setDescription(description);
        }
        return task;
    }

    public void deleteTask(int idx){ tasks.remove(idx); }
    public void deleteTask(Task task){ tasks.remove(task); }

    public void addNewTask(Task task){
        tasks.add(task);
    }
}
