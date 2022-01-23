package com.example.todomanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.OnTaskListener {

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
        RecyclerAdapter adapter = new RecyclerAdapter(taskList, this);
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


    public static final String Extra_TITLE = "com.example.todomanagement.TITLE";
    //public static final String Extra_DESC = "com.example.todomanagement.DESC";
    //public static final String Extra_DATE = "com.example.todomanagement.DATE";
    //public static final boolean Extra_CHECKED = false;

    @Override
    public void onTaskClick(int position) {
        Intent intent = new Intent(this, TaskDetailsActivity.class);
        String title = taskList.get(position).getUsername();
        //String desc = taskList.get(position).getUsername();
        //String date = taskList.get(position).getUsername();
        //Boolean checked = true;

        intent.putExtra(Extra_TITLE, title);
        //intent.putExtra(Extra_DESC, desc);
        //intent.putExtra(Extra_DATE, date);
        //intent.putExtra(String.valueOf(Extra_CHECKED), checked);

        startActivity(intent);
    }
}