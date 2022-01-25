/*
This Activity shows the user more details of the task that was clicked on the MainActivity. On this screen you can
see the title, the description and the due date of the given task. from here on you can also delete and mark the task
as checked/unchecked.
 */

package com.example.todomanagement;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TaskDetailsActivity extends AppCompatActivity{
    private DatabaseHandler databaseHandler;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);

        this.databaseHandler = new DatabaseHandler(this);
        databaseHandler.openDatabase();

        this.intent = getIntent();
        Button backButton = findViewById(R.id.back_button);
        CheckBox checkBox = (CheckBox) this.findViewById(R.id.checkbox_view);
        Button deleteButton = findViewById(R.id.task_delete_button);
        Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);

        int id = Integer.parseInt(intent.getStringExtra(MainActivity.Extra_ID));

        backButton.setOnClickListener(view -> startActivity(mainActivity));

        deleteButton.setOnClickListener(view -> {
            Log.d("Here", "still" + id);
            databaseHandler.deleteTask(id);
            startActivity(mainActivity);
        });

        setDetailsInformation();
    }

    private void setDetailsInformation(){
        // Get the Intent that started this activity and extract the string
        String id = intent.getStringExtra(MainActivity.Extra_ID);
        String title = intent.getStringExtra(MainActivity.Extra_TITLE);
        String desc = intent.getStringExtra(MainActivity.Extra_DESC);
        String date = intent.getStringExtra(MainActivity.Extra_DATE);
        boolean checked = intent.getStringExtra(MainActivity.Extra_STATUS).equals("1");

        // Capture the layout's TextView and set the string as its text
        TextView titleView = findViewById(R.id.title_view);
        TextView descView = findViewById(R.id.description_view);
        TextView dateView = findViewById(R.id.date_view);

        CheckBox checkedView = findViewById(R.id.task_checkBox);

        titleView.setText(title);
        descView.setText(desc);
        dateView.setText(date);
        checkedView.setChecked(checked);

        CheckBox check =  (CheckBox) this.findViewById(R.id.task_checkBox);
        check.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            Log.d("Checkbox", "check test");
            if (isChecked) {
                databaseHandler.updateStatus(Integer.parseInt(id), 1);
            } else {
                databaseHandler.updateStatus(Integer.parseInt(id), 0);
            }
        });
    }
}