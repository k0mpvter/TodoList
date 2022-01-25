/*
This Activity represents the Screen where you can add a new Task to your Board. You can add a title, a description and a
due date. The date automatically changes itself when entering a wrong year or a year that is in the past.
 */
package com.example.todomanagement;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class AddTaskActivity extends AppCompatActivity{
    private DatabaseHandler db;
    private EditText editText;
    private EditText editDescription;
    private EditText editDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_task_activity);

        db = new DatabaseHandler(this);

        Button newTaskButton = findViewById(R.id.button_add_new_task);

        editText = findViewById(R.id.task_title);
        editDescription = findViewById(R.id.task_description);
        editDate = findViewById(R.id.task_due_date);

        editDate.addTextChangedListener(new TextWatcher() {
            private String current = "";
            private final Calendar cal = Calendar.getInstance();


            @SuppressLint("DefaultLocale")
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(current)) {
                    String clean = s.toString().replaceAll("[^\\d.]", "");
                    String cleanC = current.replaceAll("[^\\d.]", "");

                    int cl = clean.length();
                    int sel = cl;
                    for (int i = 2; i <= cl && i < 6; i += 2) {
                        sel++;
                    }
                    //Fix for pressing delete next to a forward slash
                    if (clean.equals(cleanC)) sel--;

                    if (clean.length() < 8){
                        String ddmmyyyy = "DDMMYYYY";
                        clean = clean + ddmmyyyy.substring(clean.length());
                    }else{
                        //This part makes sure that when we finish entering numbers
                        //the date is correct, fixing it otherwise
                        int day  = Integer.parseInt(clean.substring(0,2));
                        int mon  = Integer.parseInt(clean.substring(2,4));
                        int year = Integer.parseInt(clean.substring(4,8));

                        if(mon > 12) mon = 12;
                        cal.set(Calendar.MONTH, mon-1);

                        year = (year<1900)?1900: Math.min(year, 2100);
                        cal.set(Calendar.YEAR, year);
                        //first set year for the line below to work correctly
                        //with leap years - otherwise, date e.g. 29/02/2012
                        //would be automatically corrected to 28/02/2012

                        day = Math.min(day, cal.getActualMaximum(Calendar.DATE));
                        clean = String.format("%02d%02d%02d",day, mon, year);
                    }

                    clean = String.format("%s/%s/%s", clean.substring(0, 2),
                            clean.substring(2, 4),
                            clean.substring(4, 8));

                    sel = Math.max(sel, 0);
                    current = clean;
                    editDate.setText(current);
                    editDate.setSelection(Math.min(sel, current.length()));



                    editDate.setSelection(Math.min(sel, current.length()));
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void afterTextChanged(Editable s) {}
        });

        newTaskButton.setOnClickListener(view -> {
            //db = new ContactsContract.Data()

            String title = editText.getText().toString();
            String description = editDescription.getText().toString();
            String dueDate = editDate.getText().toString();

            if (!title.equals("")){
                Task task = new Task(title, dueDate);

                if(!description.equals("")){
                    task.setDescription(description);
                }
                db.openDatabase();
                db.insertTask(task);

                Intent intent = new Intent(this.getApplicationContext(), MainActivity.class);
                this.startActivity(intent);

            }else {
                Toast.makeText(this.getApplicationContext(),"Please enter a Title",Toast.LENGTH_LONG).show();
            }
        });
    }
}