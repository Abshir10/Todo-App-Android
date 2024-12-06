package com.example.todolistgu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseLogin extends SQLiteOpenHelper {

    static final String dbName = "databaseLogin";
    static final  String tbName = "users";
    public DatabaseLogin(@Nullable Context context) {
        super(context, dbName, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists users");
    }
    String createTable = "create table users (username TEXT PRIMARY KEY ," +
            "email TEXT UNIQUE , password TEXT)";

    public Boolean insertUser(String  username, String  email ,String password) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content =  new ContentValues();
        content.put("username", username);
        content.put("email", email);
        content.put("password", password);
        long result   = db.insert(tbName, null , content);
        if(result==-1) {
            return  false;
        }
        else return true ;

    }

    public Boolean userCheckDatabase(String username , String password) {

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from users where username =? and password =?", new String[] {username , password} );

        if(cursor.getCount()>0) {
            return true;

        }
        else return false ;
    }


}
