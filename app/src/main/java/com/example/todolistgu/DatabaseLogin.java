package com.example.todolistgu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseLogin extends SQLiteOpenHelper {

    static final String dbName = "databaseLogin";
    static final String tbName = "users";

    // Constants for column names
    static final String COLUMN_USERNAME = "username";
    static final String COLUMN_EMAIL = "email";
    static final String COLUMN_PASSWORD = "password";

    public DatabaseLogin(@Nullable Context context) {
        super(context, dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Table creation SQL
        String createTable = "CREATE TABLE " + tbName + " (" +
                COLUMN_USERNAME + " TEXT PRIMARY KEY, " +
                COLUMN_EMAIL + " TEXT UNIQUE, " +
                COLUMN_PASSWORD + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the table and recreate it if the schema is upgraded
        db.execSQL("DROP TABLE IF EXISTS " + tbName);
        onCreate(db);
    }

    // Method to insert a user into the database
    public Boolean insertUser(String username, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(COLUMN_USERNAME, username);
        content.put(COLUMN_EMAIL, email);
        content.put(COLUMN_PASSWORD, password);

        long result = db.insert(tbName, null, content);
        db.close(); // Always close the database after operations
        return result != -1;
    }

    // Method to check if a user exists with the given username and password
    public Boolean userCheckDatabase(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + tbName + " WHERE " + COLUMN_USERNAME + " =? AND " + COLUMN_PASSWORD + " =?",
                new String[]{username, password});

        boolean userExists = false;
        if (cursor != null) {
            // Move cursor to first row
            if (cursor.moveToFirst()) {
                userExists = true;
            }
            cursor.close(); // Close cursor after use
        }
        db.close(); // Close the database after use
        return userExists;
    }
}
