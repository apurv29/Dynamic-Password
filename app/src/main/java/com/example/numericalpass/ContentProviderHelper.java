package com.example.numericalpass;

import android.app.LoaderManager;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by deepaksood619 on 8/6/16.
 */
public class ContentProviderHelper {

    private static final String TAG = ContentProviderHelper.class.getSimpleName();

    private static String user;

    private static int numOfSuccessLogin = 0;

    public void insertNewUser(ContentResolver contentResolver, String userName, String location) {
        Log.v(TAG,"insert username & location");

        user = userName;

        ContentValues contentValues = new ContentValues();

        contentValues.put(CustomContentProvider.COL_2, userName);
        contentValues.put(CustomContentProvider.COL_4, location);
        contentValues.put(CustomContentProvider.COL_5, 0);
        contentValues.put(CustomContentProvider.COL_6, 0);

        contentResolver.insert(CustomContentProvider.CONTENT_URI, contentValues);
    }

    public void insertSignUpTime(Context context, String signUpTime) {
        Log.v(TAG,"insert signUpTime");

        ContentValues mUpdateValues = new ContentValues();
        String mSelectionClause = CustomContentProvider.COL_2 + " = ?";
        String[] mSelectionArgs = {""};
        mSelectionArgs[0] = user;

        int mUpdatedRows = 0;

        mUpdateValues.put(CustomContentProvider.COL_3, signUpTime);
        mUpdatedRows = context.getContentResolver().update(CustomContentProvider.CONTENT_URI,
                mUpdateValues,
                mSelectionClause,
                mSelectionArgs);

        Log.v(TAG,"Rows updated: "+mUpdatedRows);

        /*String[] projection = {CustomContentProvider.COL_1};
        String selectionClause = CustomContentProvider.COL_2 + " = ?";
        String[] selectionArgs = {""};
        selectionArgs[0] = user;

        Cursor cursor = context.getContentResolver().query(CustomContentProvider.CONTENT_URI, null, selectionClause, selectionArgs, null);
        if(cursor != null) {
            if(cursor.moveToFirst()) {
                do {

                    Log.v(TAG,"result: "+cursor.getString(cursor.getColumnIndex(CustomContentProvider.COL_1)) +
                            ", " +  cursor.getString(cursor.getColumnIndex(CustomContentProvider.COL_2)) +
                            ", " + cursor.getString(cursor.getColumnIndex(CustomContentProvider.COL_3)));

                } while (cursor.moveToNext());
            }
        }*/
    }

    public void updateLoginSuccessCounter(Context context, String userName) {

        int oldLoginCounter = 0;

        Log.v(TAG,"updateLoginSuccessCounter");
        ContentValues mUpdateValues = new ContentValues();

        //String[] projection = {CustomContentProvider.COL_5};
        String mSelectionClause = CustomContentProvider.COL_2 + " = ?";
        String[] mSelectionArgs = {""};
        mSelectionArgs[0] = userName;

        Cursor cursor = context.getContentResolver().query(CustomContentProvider.CONTENT_URI, null, mSelectionClause, mSelectionArgs, null);
        if(cursor != null) {
            if(cursor.moveToFirst()) {
                do {

                    oldLoginCounter = cursor.getInt(cursor.getColumnIndex(CustomContentProvider.COL_5));

                    Log.v(TAG,"result: "+cursor.getString(cursor.getColumnIndex(CustomContentProvider.COL_2)) +
                    ", "+ oldLoginCounter);

                } while (cursor.moveToNext());
            }
        }

        int newLoginCounter = ++oldLoginCounter;
        numOfSuccessLogin = newLoginCounter;

        mUpdateValues.put(CustomContentProvider.COL_5, newLoginCounter);

        int mUpdatedRows = 0;

        mUpdatedRows = context.getContentResolver().update(CustomContentProvider.CONTENT_URI,
                mUpdateValues,
                mSelectionClause,
                mSelectionArgs);

        Log.v(TAG,"no of rows updated: "+mUpdatedRows);
    }

    public void updateMeanLoginTime(Context context, String userName, long loginTime) {
        Log.v(TAG,"updateMeanLoginTime");

        long oldMeanLoginTime = 0;

        ContentValues mUpdateValues = new ContentValues();

        //String[] projection = {CustomContentProvider.COL_6};
        String mSelectionClause = CustomContentProvider.COL_2 + " = ?";
        String[] mSelectionArgs = {""};
        mSelectionArgs[0] = userName;

        Cursor cursor = context.getContentResolver().query(CustomContentProvider.CONTENT_URI, null, mSelectionClause, mSelectionArgs, null);
        if(cursor != null) {
            if(cursor.moveToFirst()) {
                do {

                    oldMeanLoginTime = cursor.getLong(cursor.getColumnIndex(CustomContentProvider.COL_6));

                    Log.v(TAG,"result: "+cursor.getString(cursor.getColumnIndex(CustomContentProvider.COL_2)) +
                            ", "+ oldMeanLoginTime);

                } while (cursor.moveToNext());
            }
        }

        long newMeanLoginTime = 0;
        if(numOfSuccessLogin == 1) {
            newMeanLoginTime = loginTime;
        }
        else {
            newMeanLoginTime = (oldMeanLoginTime+loginTime)/2;
        }

        mUpdateValues.put(CustomContentProvider.COL_6, newMeanLoginTime);

        int mUpdatedRows = 0;

        mUpdatedRows = context.getContentResolver().update(CustomContentProvider.CONTENT_URI,
                mUpdateValues,
                mSelectionClause,
                mSelectionArgs);

        Log.v(TAG,"no of rows updated: "+mUpdatedRows);

        Log.v(TAG,"oldMeanLoginTime: "+oldMeanLoginTime);
        Log.v(TAG,"loginTime: "+loginTime);
        Log.v(TAG,"newMeanLoginTime: "+newMeanLoginTime);
        Log.v(TAG,"numOfSuccessLogin: "+numOfSuccessLogin);

    }

}
