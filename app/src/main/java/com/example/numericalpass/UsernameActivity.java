package com.example.numericalpass;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UsernameActivity extends ActionBarActivity {

    private static final String TAG = UsernameActivity.class.getSimpleName();

    EditText username;
    Button bcontinue;
    User user = new User();
    private DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.username_layout);


        username = (EditText) findViewById(R.id.username);
        bcontinue = (Button) findViewById(R.id.bcontinue);


        db = new DatabaseHelper(this);

        bcontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String u = username.getText().toString();
                System.out.println("u: " + u);
                if (!(u.equals(""))){
                    user.setUsername(u);
                //TODO the function name should be addUser
                // db.addUsername(user);

                // DatabaseHelper db = new DatabaseHelper(Context);
                if (!(db.getUserByName(u))) {
                    Intent intent = new Intent();
                    intent.setClass(UsernameActivity.this, Activitytest.class);
                    intent.putExtra("usern", u);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Login!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.setClass(UsernameActivity.this, LoginActivity.class);
                    intent.putExtra("usern", u);
                    startActivity(intent);
                }
            }
                else{
                    Toast.makeText(getApplicationContext(), "Enter a username!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void insertOnClick(View view) {
        Log.v(TAG,"insertOnClick");
        Toast.makeText(this, "insert", Toast.LENGTH_SHORT).show();

        ContentValues contentValues = new ContentValues();

        contentValues.put(CustomContentProvider.COL_2,
                ((EditText)findViewById(R.id.username)).getText().toString());

        contentValues.put(CustomContentProvider.COL_3,
                ((EditText)findViewById(R.id.no_of_successful_login)).getText().toString());

        Uri uri = getContentResolver().insert(
                CustomContentProvider.CONTENT_URI, contentValues
        );

        Toast.makeText(this,
                uri.toString(), Toast.LENGTH_LONG).show();
    }

    public void retrieveOnClick(View view) {
        Log.v(TAG,"retrieveOnClick");
        Toast.makeText(this, "retrieve", Toast.LENGTH_SHORT).show();

        Uri uri = Uri.parse(CustomContentProvider.URL);

        Cursor cursor = null;
        try {
            // Cursor cursor = managedQuery(uri, null, null, null, null);
            // query(Uri,          The content uri
            // mProjection,        The columns to return for each row
            // mSelectionClause,   Selection Criteria
            // mSelectionArgs,     Selection Criteria
            // mSortOrder);        The sort order for the returned rows

            cursor = getContentResolver().query(uri, null, null, null, null);

            if(cursor.moveToFirst()) {
                do {
                    Toast.makeText(this, "" +
                                    cursor.getString(cursor.getColumnIndex(CustomContentProvider.COL_1)) +
                                    " , " + cursor.getString(cursor.getColumnIndex(CustomContentProvider.COL_2)) +
                                    " , " + cursor.getString(cursor.getColumnIndex(CustomContentProvider.COL_3)),
                            Toast.LENGTH_SHORT).show();
                } while (cursor.moveToNext());
            }
        } finally {
            if(cursor != null)
                cursor.close();
        }
    }
}


