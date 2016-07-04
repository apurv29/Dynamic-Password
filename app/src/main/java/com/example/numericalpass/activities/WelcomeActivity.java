package com.example.numericalpass.activities;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.numericalpass.R;
import com.example.numericalpass.helper.CSVeditor;
import com.example.numericalpass.helper.NotificationPublisher;

import java.util.Calendar;

public class WelcomeActivity extends ActionBarActivity {

    private static final String TAG = WelcomeActivity.class.getSimpleName();

    public long endLoginTime;

    private Button btnSubmit;
    private RatingBar ratingBar;
    private Spinner spnMemoryBurden;
    private Spinner spnUnderstand;
    private Spinner spnRemember;

    boolean submitPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        btnSubmit = (Button) findViewById(R.id.btn_submit_feedback);
        ratingBar = (RatingBar) findViewById(R.id.rating_bar);
        spnMemoryBurden = (Spinner) findViewById(R.id.spn_memory_burden);
        spnUnderstand = (Spinner) findViewById(R.id.spn_understand);
        spnRemember = (Spinner) findViewById(R.id.spn_remember);

        final String userName = getIntent().getStringExtra("USERNAME");

        endLoginTime = Calendar.getInstance().getTimeInMillis();

        long totalLoginTime = endLoginTime - UsernameActivity.startTime;
        Log.v(TAG,"Total login time: "+totalLoginTime);

        CSVeditor.shared().recordTimeStamp(totalLoginTime, 9);

        UsernameActivity.stopScreenSharing();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int rating = (int) ratingBar.getRating();
                String memoryBurden = spnMemoryBurden.getSelectedItem().toString();
                String understand = spnUnderstand.getSelectedItem().toString();
                String remember = spnRemember.getSelectedItem().toString();

                CSVeditor.shared().insertFeedback(rating, memoryBurden, understand, remember);
                CSVeditor.shared().recordTimeStamp(InstructionsActivity.endTime, 14);

                scheduleNotification(getNotification("Its time to login using "+userName), AlarmManager.INTERVAL_DAY);
                submitPressed = true;
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(submitPressed) {
            finish();
            Intent intent = new Intent(WelcomeActivity.this, UsernameActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(WelcomeActivity.this, "Please press submit", Toast.LENGTH_SHORT).show();
        }
    }

    private void scheduleNotification(Notification notification, long delay) {

        Intent notificationIntent = new Intent(this, NotificationPublisher.class);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
    }

    private Notification getNotification(String content) {

        Intent myIntent = new Intent(getApplicationContext(), UsernameActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                getApplicationContext(),
                0,
                myIntent,
                PendingIntent.FLAG_ONE_SHOT);

        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("Numerical password login");
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        builder.setDefaults(Notification.DEFAULT_ALL);
        builder.setContentText(content);
        builder.setSmallIcon(R.mipmap.ic_launcher);

        return builder.build();
    }
}