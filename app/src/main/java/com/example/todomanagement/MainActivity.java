/*
This is our MainActivity. It is the first screen you see when you open this application. It shows a List of all
Tasks that you have created. From here on you can add new tasks. You can check a task if it is complete or
see further details when you click on it.
 */

package com.example.todomanagement;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.OnTaskListener {
    public static final String Extra_ID = "com.example.todomanagement.ID";
    public static final String Extra_TITLE = "com.example.todomanagement.TITLE";
    public static final String Extra_DESC = "com.example.todomanagement.DESC";
    public static final String Extra_DATE = "com.example.todomanagement.DATE";
    public static final String Extra_STATUS = "com.example.todomanagement.STATUS";
    private final DatabaseHandler db = new DatabaseHandler(this);
    private RecyclerView recyclerView;
    private ArrayList<Task> tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton button = findViewById(R.id.button_new_task);
        recyclerView = findViewById(R.id.recyclerView);

        db.openDatabase();
        tasks = db.getAllTasks();

        setAdapter();

        button.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), AddTaskActivity.class);
            startActivity(intent);
        });
    }

    //Sets the adapter of the recyclerView
    public void setAdapter() {
        RecyclerAdapter adapter = new RecyclerAdapter(this,this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onTaskClick(int position) {
        Intent intent = new Intent(this, TaskDetailsActivity.class);
        String id = String.valueOf(tasks.get(position).getId());
        String title = tasks.get(position).getTitle();
        String desc = tasks.get(position).getDescription() + " ";
        String date = tasks.get(position).getDueDate();
        //String status = String.valueOf(tasks.get(position).getStatus());
        CheckBox checkbox = (CheckBox) findViewById(R.id.checkbox_view);

        String status = String.valueOf(checkbox.isChecked());
        Log.d("Status..", status);

        intent.putExtra(Extra_ID, id);
        intent.putExtra(Extra_TITLE, title);
        intent.putExtra(Extra_DESC, desc);
        intent.putExtra(Extra_DATE, date);
        intent.putExtra(Extra_STATUS, status);

        startActivity(intent);
    }
}