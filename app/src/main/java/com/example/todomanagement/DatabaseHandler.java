package com.example.todomanagement;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Date;

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

    public void insertTask(Task task){
        ContentValues contentValues = new ContentValues();
        contentValues.put(TITLE, task.getTitle());
        contentValues.put(DESCRIPTION, task.getDescription());
        contentValues.put(DUEDATE, task.getDueDate());
        contentValues.put(STATUS, false);

        //nullColumnHack beacuse we want to insert into the whole row
        db.insert(TODO_TABLE, null, contentValues);
    }

    public ArrayList<Task> getAllTasks(){
        ArrayList<Task> taskList = new ArrayList<>();
        Cursor cursor = null;
        db.beginTransaction();
        try{
            // to get all values from the table we pass null everywhere
            cursor = db.query(TODO_TABLE, null, null, null, null, null, null, null);
            if(cursor != null){ // if we got values / rows continue
                if(cursor.moveToFirst()){
                    do{
                        /*
                        int id = cursor.getInt(cursor.getColumnIndex(ID));
                        String title = cursor.getString(cursor.getColumnIndex(TITLE));
                        String description = cursor.getString(cursor.getColumnIndex(DESCRIPTION));
                        String dueDate = cursor.getString(cursor.getColumnIndex(DUEDATE));
                        int status = cursor.getInt(cursor.getColumnIndex(STATUS));

                        Task task = new Task(title, dueDate);
                        task.setId(id);
                        task.setDescription(description);
                        task.setChecked(status);*/

                        Task task = getTask(cursor);
                        taskList.add(task);
                    }
                    while(cursor.moveToNext());
                }
            }
        }
        finally {
            db.endTransaction();
            //assert cursor != null;
            cursor.close();
        }
        return taskList;
    }


    private Task getTask(Cursor cursor){
        Task task = null;
        //Cursor cursor = db.rawQuery("select * from " + TODO_TABLE + " where " + ID+ "='" + idx + "'" , null);
        if(cursor != null){
            @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex(TITLE));
            @SuppressLint("Range") String dueDate = cursor.getString(cursor.getColumnIndex(DUEDATE));
            @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex(DESCRIPTION));
            @SuppressLint("Range") String status = cursor.getString(cursor.getColumnIndex(STATUS));
            task = new Task(title, dueDate);
            task.setDescription(description);
        }

        return task;
    }

    private Task getTask(int idx){
        Task task = null;
        Cursor cursor = db.rawQuery("select * from " + TODO_TABLE + " where " + ID+ "='" + idx + "'" , null);
        if(cursor != null){
            @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex(TITLE));
            @SuppressLint("Range") String dueDate = cursor.getString(cursor.getColumnIndex(DUEDATE));
            @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex(DESCRIPTION));
            @SuppressLint("Range") String status = cursor.getString(cursor.getColumnIndex(STATUS));
            task = new Task(title, dueDate);
            task.setDescription(description);
        }

        return task;
    }

    public void updateStatus(int id, int status){
        ContentValues cv = new ContentValues();
        cv.put(STATUS, status);
        db.update(TODO_TABLE, cv, ID + "= ?", new String[] {String.valueOf(id)});
    }

    public void updateTask(int id, String task) {
        ContentValues cv = new ContentValues();
        cv.put(TITLE, task);
        db.update(TODO_TABLE, cv, ID + "= ?", new String[] {String.valueOf(id)});
    }

    public void deleteTask(int id){
        db.delete(TODO_TABLE, ID + "= ?", new String[] {String.valueOf(id)});
    }
}