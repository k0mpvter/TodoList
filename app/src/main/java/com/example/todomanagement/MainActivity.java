package com.example.todomanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<User> taskList;
    private RecyclerView recyclerView;
    FloatingActionButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        taskList = new ArrayList<>();

        setUserInfo();
        setAdapter();



        button = findViewById(R.id.button_new_task);

        button.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), AddTaskActivity.class);
            startActivity(intent);
        });
    }

    private void setAdapter() {
        RecyclerAdapter adapter = new RecyclerAdapter(taskList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

    }

    private void setUserInfo() {
        taskList.add(new User("John"));
        taskList.add(new User("Mike"));
        taskList.add(new User("Loredana"));
        taskList.add(new User("Trevor"));
        taskList.add(new User("SomeDad"));
        taskList.add(new User("John"));
        taskList.add(new User("Mike"));
        taskList.add(new User("Loredana"));
        taskList.add(new User("Trevor"));
        taskList.add(new User("SomeDad"));
        taskList.add(new User("John"));
        taskList.add(new User("Mike"));
        taskList.add(new User("Loredana"));
        taskList.add(new User("Trevor"));
        taskList.add(new User("SomeDad"));
        taskList.add(new User("John"));
        taskList.add(new User("Mike"));
        taskList.add(new User("Loredana"));
        taskList.add(new User("Trevor"));
        taskList.add(new User("SomeDad"));
        taskList.add(new User("John"));
        taskList.add(new User("Mike"));
        taskList.add(new User("Loredana"));
        taskList.add(new User("Trevor"));
        taskList.add(new User("SomeDad"));
        taskList.add(new User("John"));
        taskList.add(new User("Mike"));
        taskList.add(new User("Loredana"));
        taskList.add(new User("Trevor"));
        taskList.add(new User("SomeDad"));
        taskList.add(new User("John"));
        taskList.add(new User("Mike"));
        taskList.add(new User("Loredana"));
        taskList.add(new User("Trevor"));
        taskList.add(new User("SomeDad"));
        taskList.add(new User("John"));
        taskList.add(new User("Mike"));
        taskList.add(new User("Loredana"));
        taskList.add(new User("Trevor"));
        taskList.add(new User("SomeDad"));
    }
}