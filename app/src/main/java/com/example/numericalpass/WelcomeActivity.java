package com.example.numericalpass;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class WelcomeActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }
    @Override
    public void onBackPressed() {

        finish();
        Intent intent = new Intent(WelcomeActivity.this, UsernameActivity.class);
        startActivity(intent);
    }

}
