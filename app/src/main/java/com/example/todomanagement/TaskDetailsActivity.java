package com.example.todomanagement;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.widget.CheckBox;
import android.widget.TextView;

public class TaskDetailsActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String title = intent.getStringExtra(MainActivity.Extra_TITLE);
        //String desc = intent.getStringExtra(MainActivity.Extra_DESC);
        //String date = intent.getStringExtra(MainActivity.Extra_DATE);
        //Boolean checked = getIntent().getExtras().getBoolean(String.valueOf(MainActivity.Extra_CHECKED));

        // Capture the layout's TextView and set the string as its text
        TextView titleView = findViewById(R.id.title_view);
        //TextView descView = findViewById(R.id.description_view);
        //TextView dateView = findViewById(R.id.date_view);
        //CheckBox checkedView = findViewById(R.id.task_checkBox);

        titleView.setText(title);
        //descView.setText(desc);
        //dateView.setText(date);
        //checkedView.setChecked(checked);
    }

}

