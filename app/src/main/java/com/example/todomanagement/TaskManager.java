package com.example.todomanagement;

import android.util.Log;

import java.text.ParseException;
import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> tasks;
    //private val tasks = arrayListOf<Task>()

    public TaskManager(){
        tasks = new ArrayList<>();
    }

    public void showAllTasks(){
        System.out.println("HERERERERE");
        Log.d("HEREREEEE"," STHIL");
        for (Task task:tasks) {
            Log.d("HEREREEEE",task.getTitle());
        }
    }

    public Task createNewTask(String title, String description, String dueDate) throws ParseException {

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
    public Task get(int idx){ return tasks.get(idx); }
    public int size(){return tasks.size(); }
}
