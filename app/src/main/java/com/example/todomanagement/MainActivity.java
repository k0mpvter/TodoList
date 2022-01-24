package com.example.todomanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.OnTaskListener {

    //private ArrayList<User> taskList;

    public static final String Extra_TITLE = "com.example.todomanagement.TITLE";
    public static final String Extra_DESC = "com.example.todomanagement.DESC";
    public static final String Extra_DATE = "com.example.todomanagement.DATE";
    public static final boolean Extra_CHECKED = false;
    private TaskManager tasks;
    private RecyclerView recyclerView;
    FloatingActionButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        //taskList = new ArrayList<>();
        tasks = new TaskManager();

        setUserInfo();
        setAdapter();

        button = findViewById(R.id.button_new_task);

        button.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), AddTaskActivity.class);
            startActivity(intent);
        });

        tasks.showAllTasks();
    }

    public void setAdapter() {
        RecyclerAdapter adapter = new RecyclerAdapter(tasks, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        tasks.showAllTasks();
    }

    private void setUserInfo() {
        Task task1 = new Task("task1", "22/12/2022");
        tasks.addNewTask(task1);

        Task task2 = new Task("task2", "22/12/2022");
        task2.setDescription("halli hallo");
        tasks.addNewTask(task2);

        Task task3 = new Task("task3", "22/12/2022");
        tasks.addNewTask(task3);
    }

    @Override
    public void onTaskClick(int position) {
        Intent intent = new Intent(this, TaskDetailsActivity.class);
        String title = tasks.get(position).getTitle();
        String desc = tasks.get(position).getDescription() + " ";
        String date = tasks.get(position).getDueDate();
        //boolean checked = tasks.get(position).getIfChecked();

        intent.putExtra(Extra_TITLE, title);
        intent.putExtra(Extra_DESC, desc);
        intent.putExtra(Extra_DATE, date.toString());

        //intent.putExtra(String.valueOf(Extra_CHECKED), checked);

        startActivity(intent);
    }

    public void addTask(Task task){
        tasks.addNewTask(task);
    }
}