package com.example.todomanagement;

import java.time.LocalDate;
import java.util.Date;

public class Task{
    private String name;
    private String description;
    private Date dueDate;
    private boolean isChecked;

    public Task(String name, Date dueDate){
        this.name = name;
        this.dueDate = dueDate;
    }

    public String getName(){ return name; }
    public String getDescription() { return description;}
    public Date getDueDate() { return dueDate; }
    public boolean getIfChecked() { return isChecked; }

    public void setDescription(String description){
        this.description = description;
    }

    public void setChecked(boolean checked) {
        this.isChecked = checked;
    }
}
