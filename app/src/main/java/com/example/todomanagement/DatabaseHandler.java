
/*
This Class is responsible for the connection between the database and the application. It can add or delete tasks
to or from the database.
 */
package com.example.todomanagement;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String NAME = "toDoListDatabase";
    private static final String TODO_TABLE = "todo";
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String DUEDATE = "duedate";
    private static final String STATUS = "status";
    private static final String CREATE_TODO_TABLE = "CREATE TABLE " + TODO_TABLE + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TITLE + " TEXT, "
            + DESCRIPTION + " TEXT, " + DUEDATE + " TEXT, " + STATUS + " INTEGER)";

    private SQLiteDatabase db;

    public DatabaseHandler(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TODO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TODO_TABLE);
        // Create tables again
        onCreate(db);
    }

    public void openDatabase() {
        db = this.getWritableDatabase();
    }

    //Inserts a task into the database table.
    public void insertTask(Task task){
        ContentValues contentValues = new ContentValues();
        contentValues.put(TITLE, task.getTitle());
        contentValues.put(DESCRIPTION, task.getDescription());
        contentValues.put(DUEDATE, task.getDueDate());
        contentValues.put(STATUS, false);

        //nullColumnHack because we want to insert into the whole row
        db.insert(TODO_TABLE, null, contentValues);
    }

    //fetches all tasks from the database
    public ArrayList<Task> getAllTasks(){
        ArrayList<Task> taskList = new ArrayList<>();
        db.beginTransaction();
        try (Cursor cursor = db.query(TODO_TABLE, null, null, null, null, null, null, null)) {
            // to get all values from the table we pass null everywhere
            if (cursor != null) { // if we got values / rows continue
                if (cursor.moveToFirst()) {
                    do {
                        Task task = getTask(cursor);
                        taskList.add(task);
                    }
                    while (cursor.moveToNext());
                }
            }
        } finally {
            db.endTransaction();
        }
        return taskList;
    }

    //fetches one specific task
    private Task getTask(Cursor cursor){
        Task task = null;
        if(cursor != null){
            @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex(ID));
            @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex(TITLE));
            @SuppressLint("Range") String dueDate = cursor.getString(cursor.getColumnIndex(DUEDATE));
            @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex(DESCRIPTION));
            @SuppressLint("Range") String status = cursor.getString(cursor.getColumnIndex(STATUS));
            task = new Task(title, dueDate);
            task.setDescription(description);
            task.setId(Integer.parseInt(id));
            task.setStatus(Integer.parseInt(status));
        }
        return task;
    }

    //checkes if the task has already been checked or not
    public void updateStatus(int id, int status){
        ContentValues cv = new ContentValues();
        cv.put(STATUS, status);
        String idS = String.valueOf(id);
        int cur = db.update(TODO_TABLE, cv, ID + "= ?", new String[] {idS});
        Log.d("Update", String.valueOf(cur));
    }

    public void deleteTask(int id){
        int cur = db.delete(TODO_TABLE, ID + "= ?", new String[] {String.valueOf(id)});
    }

    //returns the size of all tasks
    public int getSize(){
        return (int) DatabaseUtils.queryNumEntries(db, TODO_TABLE);
    }
}