package com.example.todomanagement;

import java.time.LocalDate;
import java.util.Date;

public class Task{
    private String name;
    private String description;
    private Date dueDate;

    public Task(String name, Date dueDate){
        this.name = name;
        this.dueDate = dueDate;
    }

    public String getName(){
        return name;
    }

    public void setDescription(String description){
        this.description = description;
    }
}
