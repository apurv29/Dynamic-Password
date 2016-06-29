package com.example.numericalpass;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import java.util.Calendar;

public class InstructionsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);
    }

    long startTime;
    @Override
    protected void onResume() {
        super.onResume();
        startTime = Calendar.getInstance().getTimeInMillis();
    }

    @Override
    protected void onPause() {
        super.onPause();
        long endTime = Calendar.getInstance().getTimeInMillis() - startTime;
        CSVeditor.shared().recordTimeStamp(endTime, 14);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
