package com.example.demodatabasecrud;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "simplenotes.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NOTE = "note";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NOTE_CONTENT = "note_content";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createNoteTableSql = "CREATE TABLE " + TABLE_NOTE + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NOTE_CONTENT + " TEXT ) ";
        db.execSQL(createNoteTableSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTE);
        onCreate(db);
    }

    public long insertNote(String noteContent) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOTE_CONTENT, noteContent);
        long result = db.insert(TABLE_NOTE, null, values);
        db.close();
        if (result == -1) {
            Log.d("DBHelper", "Insert Failed");
        } else {
            Log.d("SQL Insert", "ID:" + result);
        } //id returned, shouldn’t be -1
        return result;
    }

}

