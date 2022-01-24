package com.example.todomanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class AddTaskActivity extends AppCompatActivity{

    private MainActivity mainActivity = new MainActivity();
    private EditText editText;
    private EditText editDescription;
    private EditText editDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //TODO inject Taskmanager (Dagger)

        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_task_activity);

        Button newTaskButton = findViewById(R.id.button_add_new_task);

        editText = findViewById(R.id.task_title);
        editDescription = findViewById(R.id.task_description);
        editDate = findViewById(R.id.task_due_date);

        editDate.addTextChangedListener(new TextWatcher() {
            private String current = "";
            private String ddmmyyyy = "DDMMYYYY";
            private Calendar cal = Calendar.getInstance();


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
                        clean = clean + ddmmyyyy.substring(clean.length());
                    }else{
                        //This part makes sure that when we finish entering numbers
                        //the date is correct, fixing it otherwise
                        int day  = Integer.parseInt(clean.substring(0,2));
                        int mon  = Integer.parseInt(clean.substring(2,4));
                        int year = Integer.parseInt(clean.substring(4,8));

                        if(mon > 12) mon = 12;
                        cal.set(Calendar.MONTH, mon-1);

                        year = (year<1900)?1900:(year>2100)?2100:year;
                        cal.set(Calendar.YEAR, year);
                        // ^ first set year for the line below to work correctly
                        //with leap years - otherwise, date e.g. 29/02/2012
                        //would be automatically corrected to 28/02/2012

                        day = (day > cal.getActualMaximum(Calendar.DATE))? cal.getActualMaximum(Calendar.DATE):day;
                        clean = String.format("%02d%02d%02d",day, mon, year);
                    }

                    clean = String.format("%s/%s/%s", clean.substring(0, 2),
                            clean.substring(2, 4),
                            clean.substring(4, 8));

                    sel = sel < 0 ? 0 : sel;
                    current = clean;
                    editDate.setText(current);
                    editDate.setSelection(sel < current.length() ? sel : current.length());



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
            String duedate = editDate.getText().toString();

            if (!title.equals("")){
                Task task = new Task(title, duedate);
                if(description != null){
                    task.setDescription(description);
                }
                Intent intent = new Intent(this.getApplicationContext(), MainActivity.class);
                this.startActivity(intent);
                DatabaseHandler db = new DatabaseHandler(this);

            }else {
                Toast.makeText(this.getApplicationContext(),"Please enter a Title",Toast.LENGTH_LONG).show();
            }
        });
    }
}