package com.example.todomanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView welcomeText = findViewById(R.id.textView);
        Button newTaskButton = findViewById(R.id.button_new_task);

        newTaskButton.setOnClickListener(view -> {
            Intent intent = new Intent(this.getApplicationContext(), AddTaskActivity.class);
            startActivity(intent);
        });
    }
}