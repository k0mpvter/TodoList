package com.example.todomanagement;

public class Task{
    private int id;
    private String title;
    private String description;
    private String dueDate;
    private int status = 0;
    //private boolean isChecked;

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

    public int getStatus() { return status;}
    public void setStatus(int status) { this.status = status;}

    public boolean getIfChecked(){
        return (status==1) ? true : false;
    }
}
