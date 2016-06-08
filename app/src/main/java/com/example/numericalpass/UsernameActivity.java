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

import java.util.Calendar;

public class UsernameActivity extends ActionBarActivity {

    private static final String TAG = UsernameActivity.class.getSimpleName();

    EditText username;
    Button bcontinue;
    User user = new User();
    private DatabaseHelper db;

    // for recording the time user takes to signup : start time
    public static long startSignUpTime;
    public static ContentProviderHelper contentProviderHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.username_layout);

        startSignUpTime = Calendar.getInstance().getTimeInMillis();

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

                    // insert username in column 1 of contentProvider database
                    contentProviderHelper = new ContentProviderHelper();
                    contentProviderHelper.insertUsername(getContentResolver(), u);

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
}


