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
public class ContentProviderHelper implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String TAG = ContentProviderHelper.class.getSimpleName();

    private static String user;

    CursorLoader cursorLoader;

    public void insertUsername(ContentResolver contentResolver, String userName) {
        Log.v(TAG,"insert username");

        user = userName;

        ContentValues contentValues = new ContentValues();

        contentValues.put(CustomContentProvider.COL_2, userName);

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

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
