package com.example.numericalpass;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.media.MediaRecorder;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.EditTextPreference;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class UsernameActivity extends ActionBarActivity {

    private static final String TAG = UsernameActivity.class.getSimpleName();

    private static final int REQUEST_CODE = 1000;
    private static final int DISPLAY_WIDTH = 720;
    private static final int DISPLAY_HEIGHT = 1280;

    private static final SparseIntArray ORIENTATIONS = new SparseIntArray();

    private int screenDensity;
    MediaRecorder mediaRecorder;
    static MediaProjection mediaProjection;
    MediaProjectionManager mediaProjectionManager;
    static MediaProjectionCallback mediaProjectionCallback;
    static VirtualDisplay virtualDisplay;

    EditText username;
    Button bcontinue;
    User user = new User();
    private DatabaseHelper db;

    // for recording the time user takes to signup : start time
    public static long startTime;
    public static ContentProviderHelper contentProviderHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.username_layout);

        init();

        contentProviderHelper = new ContentProviderHelper();

        username = (EditText) findViewById(R.id.username);
        bcontinue = (Button) findViewById(R.id.bcontinue);

        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMyyyyhhmmss");

        db = new DatabaseHelper(this);

        bcontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String u = username.getText().toString();
                System.out.println("u: " + u);
                if (!(u.equals(""))){
                    user.setUsername(u);
                // db.addUsername(user);

                // DatabaseHelper db = new DatabaseHelper(Context);
                if (!(db.getUserByName(u))) {

                    Log.v(TAG,"New user");

                    String timeStamp = simpleDateFormat.format(new Date());

                    String location = Environment.getExternalStoragePublicDirectory(Environment
                            .DIRECTORY_DOWNLOADS)+"/"+u+"_"+timeStamp+"_sign_up.mp4";

                    initRecorder(location);

                    // insert username in column 1 of contentProvider database
                    contentProviderHelper.insertNewUser(getApplicationContext(), u, location);

                    Toast.makeText(UsernameActivity.this, "started", Toast.LENGTH_SHORT).show();
                    shareScreen();

                    Intent intent = new Intent();
                    intent.setClass(UsernameActivity.this, Activitytest.class);
                    intent.putExtra("usern", u);
                    startActivity(intent);
                }
                else {
                    Log.v(TAG,"Old user");

                    String timeStamp = simpleDateFormat.format(new Date());

                    String location = Environment.getExternalStoragePublicDirectory(Environment
                            .DIRECTORY_DOWNLOADS)+"/"+u+"_"+timeStamp+"_sign_in.mp4";
                    initRecorder(location);

                    shareScreen();

                    contentProviderHelper.insertSignInVideo(getApplicationContext(), u, location);

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
    protected void onResume() {
        super.onResume();
        startTime = Calendar.getInstance().getTimeInMillis();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void init() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screenDensity = metrics.densityDpi;

        mediaRecorder = new MediaRecorder();
        mediaProjectionManager = (MediaProjectionManager) getSystemService(Context.MEDIA_PROJECTION_SERVICE);

        if(mediaProjection == null) {
            startActivityForResult(mediaProjectionManager.createScreenCaptureIntent(), REQUEST_CODE);
            return;
        }

    }

    private class MediaProjectionCallback extends MediaProjection.Callback {
        @Override
        public void onStop() {
            if(false) {
                //toggleButton.setChecked(false);
                mediaRecorder.stop();
                mediaRecorder.reset();
                Log.v(TAG,"Recording Stopped");
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != REQUEST_CODE) {
            Log.e(TAG, "Unknown request code: " + requestCode);
            return;
        }
        if (resultCode != RESULT_OK) {
            Toast.makeText(this,
                    "Screen Cast Permission Denied", Toast.LENGTH_SHORT).show();
            return;
        }
        mediaProjectionCallback = new MediaProjectionCallback();
        mediaProjection = mediaProjectionManager.getMediaProjection(resultCode, data);
        mediaProjection.registerCallback(mediaProjectionCallback, null);
    }

    private void shareScreen() {
        virtualDisplay = createVirtualDisplay();
        mediaRecorder.start();
    }

    private VirtualDisplay createVirtualDisplay() {
        if(mediaProjection == null) {
            Log.v(TAG,"null");
        }
        else
            Log.v(TAG,"not null");

        return mediaProjection.createVirtualDisplay("UsernameActivity",
                DISPLAY_WIDTH, DISPLAY_HEIGHT, screenDensity,
                DisplayManager.VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR,
                mediaRecorder.getSurface(), null /*Callbacks*/, null
                /*Handler*/);
    }


    private void initRecorder(String location) {

        try {
            mediaRecorder.setVideoSource(MediaRecorder.VideoSource.SURFACE);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);

            mediaRecorder.setOutputFile(location);
            mediaRecorder.setVideoSize(DISPLAY_WIDTH, DISPLAY_HEIGHT);
            mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.DEFAULT);
            mediaRecorder.setVideoEncodingBitRate(512 * 1000);
            mediaRecorder.setVideoFrameRate(30);
            int rotation = getWindowManager().getDefaultDisplay().getRotation();
            int orientation = ORIENTATIONS.get(rotation + 90);
            mediaRecorder.setOrientationHint(orientation);
            mediaRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void stopScreenSharing() {

        Log.v(TAG,"Recording Stopped");

        if(virtualDisplay == null) {
            return;
        }
        virtualDisplay.release();
        destroyMediaProjection();
    }

    private static void destroyMediaProjection() {
        if(mediaProjection != null) {
            mediaProjection.unregisterCallback(mediaProjectionCallback);
            mediaProjection.stop();
            mediaProjection = null;
        }
        Log.v(TAG,"MediaProjection Stopped");
    }

}


