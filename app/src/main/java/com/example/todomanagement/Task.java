/*
This class describes the attributes of each task element. You can get and set these values with the given methods.
 */

package com.example.todomanagement;

public class Task{
    private int id;
    private final String title;
    private String description;
    private final String dueDate;
    private int status = 0;

    public Task(String name, String dueDate){
        this.title = name;
        this.dueDate = dueDate;
    }

    public int getId(){ return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle(){ return title; }
    public String getDueDate() { return dueDate; }

    public String getDescription() { return description;}
    public void setDescription(String description){
        this.description = description;
    }

    public void setStatus(int status) { this.status = status;}

    public boolean getIfChecked(){
        return status == 1;
    }
}
