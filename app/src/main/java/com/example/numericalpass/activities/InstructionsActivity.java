package com.example.numericalpass.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.example.numericalpass.R;
import com.example.numericalpass.helper.CSVeditor;

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

    public static long endTime;
    @Override
    protected void onPause() {
        super.onPause();
        endTime = Calendar.getInstance().getTimeInMillis() - startTime;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
