package com.example.numericalpass;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

public class InstructionsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
