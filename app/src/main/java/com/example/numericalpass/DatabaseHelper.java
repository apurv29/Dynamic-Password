package com.example.numericalpass;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;


import java.util.ArrayList;
import java.util.List;



import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.net.nsd.NsdManager.RegistrationListener;
import android.provider.ContactsContract.CommonDataKinds.Im;
import android.util.Log;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "numpin";

    // Table Names
    private static final String TABLE_NUMPIN = "numpin_table";


    private static final String KEY_ID = "_id"; //primary key
    private static final String KEY_CREATED_AT = "created_at";

    // t_login Table - column nmaes

    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_WRONGTRY = "wrongtry";







    // Table Create Statements
    // t_login table create statement
    private static final String CREATE_TABLE_t_login = "CREATE TABLE "
            + TABLE_NUMPIN + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_USERNAME + " VARCHAR," + KEY_PASSWORD + " VARCHAR," + KEY_WRONGTRY + " INTEGER " + " )";




    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_t_login);


        Log.d(TAG, "creating tables");

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NUMPIN);
        // create new tables
        onCreate(db);
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        boolean exists = getUserByName(user.getUsername());
        if(exists == false) {
            values.put(KEY_USERNAME, user.getUsername()); // Contact Name
            values.put(KEY_PASSWORD, user.getPassword()); // Contact Phone Number
            // Inserting Row
            db.insert(TABLE_NUMPIN, null, values);
        System.out.println("inserted successfully");
       }
        db.close(); // Closing database connection
    }

    public boolean getUserByName(String username) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NUMPIN,
                new String[]{KEY_USERNAME},
                KEY_USERNAME + " = ? ",
                new String[]{username},
                null, null, null, null);

        if(cursor.moveToFirst()) {
            System.out.println("cursor: "+ cursor);
            return true; //row exists
        }
        else
            return false;


    }


    public String getPassByName(String username){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NUMPIN, new String[] { KEY_ID,
                        KEY_USERNAME, KEY_PASSWORD }, KEY_USERNAME + "=?",
                new String[] { username }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        String pass = new String((cursor.getString(2)));
        //System.out.println("usern db: "+ usern);
        // return contact
        return pass;

    }
}
