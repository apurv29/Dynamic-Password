package com.example.numericalpass;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import java.util.Calendar;

public class WelcomeActivity extends ActionBarActivity {

    private static final String TAG = WelcomeActivity.class.getSimpleName();

    public long endLoginTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        String userName = getIntent().getStringExtra("USERNAME");

        endLoginTime = Calendar.getInstance().getTimeInMillis();

        long totalLoginTime = endLoginTime - UsernameActivity.startTime;
        Log.v(TAG,"Total login time: "+totalLoginTime);

        UsernameActivity.contentProviderHelper.updateLoginSuccessCounter(getApplicationContext(), userName);
        UsernameActivity.contentProviderHelper.updateMeanLoginTime(getApplicationContext(), userName, totalLoginTime);

        UsernameActivity.stopScreenSharing();

    }
    @Override
    public void onBackPressed() {

        finish();
        Intent intent = new Intent(WelcomeActivity.this, UsernameActivity.class);
        startActivity(intent);
    }

}
