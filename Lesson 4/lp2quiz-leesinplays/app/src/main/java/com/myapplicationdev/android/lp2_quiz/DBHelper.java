package com.myapplicationdev.android.lp2_quiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VER = 1;
    private static final String DATABASE_NAME = "mydb.db";

    private static final String TABLE_TODO = "ToDo";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_REMINDER = "reminder";
    private static final String COLUMN_DATE = "date";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE " + TABLE_TODO +  "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_DATE + " TEXT,"
                + COLUMN_REMINDER + " TEXT )";
        db.execSQL(createTableSql);
        Log.i("info" ,"created tables");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO);
        // Create table(s) again
        onCreate(db);
    }

    public void insertToDo(String reminder, String date){

        // Get an instance of the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        // We use ContentValues object to store the values for
        //  the db operation
        ContentValues values = new ContentValues();
        // Store the column name as key and the description as value
        values.put(COLUMN_REMINDER, reminder);
        // Store the column name as key and the date as value
        values.put(COLUMN_DATE, date);
        // Insert the row into the TABLE_TASK
        db.insert(TABLE_TODO, null, values);
        // Close the database connection
        db.close();
    }

    public ArrayList<ToDo> getToDo() {
        //TODO return records in Java objects

        ArrayList<ToDo> toDos = new ArrayList<ToDo>();
        String selectQuery = "SELECT " + COLUMN_ID + ", "
                + COLUMN_DATE + ", "
                + COLUMN_REMINDER
                + " FROM " + TABLE_TODO;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String date = cursor.getString(1);
                String data = cursor.getString(2);
                ToDo obj = new ToDo(id, date, data);
                toDos.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return toDos;
    }

    public ArrayList<ToDo> getToDoRecents() {
        //TODO return records in Java objects

        ArrayList<ToDo> toDos = new ArrayList<ToDo>();
        String selectQuery = "SELECT " + COLUMN_ID + ", "
                + COLUMN_DATE + ", "
                + COLUMN_REMINDER
                + " FROM " + TABLE_TODO + " ORDER BY " + COLUMN_ID +" DESC limit 10";
        // googled

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String date = cursor.getString(1);
                String data = cursor.getString(2);
                ToDo obj = new ToDo(id, date, data);
                toDos.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return toDos;
    }

    public int updateToDo(ToDo data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_REMINDER, data.getData());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};
        int result = db.update(TABLE_TODO, values, condition, args);
        db.close();
        if (result < 1){
            Log.d("DBHelper", "Update failed");
        }
        return result;
    }

    public int deleteToDo(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_TODO, condition, args);
        db.close();
        if (result < 1){
            Log.d("DBHelper", "Update failed");
        }
        return result;
    }
}

