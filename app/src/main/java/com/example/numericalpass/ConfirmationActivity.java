package com.example.numericalpass;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Calendar;

public class ConfirmationActivity extends Activity {

    private static final String TAG = ConfirmationActivity.class.getSimpleName();

	Intent i,iusern;
	String s,str,str_usern;
	TextView textv;
	//SQLiteDatabase db;
	User user = new User();

    // for recording the time user takes to signup : end time
    public long endSignUpTime;

			public void onCreate(Bundle savedInstanceState) {
			    super.onCreate(savedInstanceState);
			    setContentView(R.layout.confirmationlayout);

				endSignUpTime = Calendar.getInstance().getTimeInMillis();

                // insert time taken in signup process by user
                long totalSignUpTime = endSignUpTime - UsernameActivity.startSignUpTime;
                Log.v(TAG,"totalSignUpTime: "+totalSignUpTime);

                UsernameActivity.contentProviderHelper.insertSignUpTime(getApplicationContext(), String.valueOf(totalSignUpTime));
			    
			    i=getIntent();
				iusern = getIntent();
			    
				 s = i.getStringExtra("confirmstring");
				 str_usern = iusern.getStringExtra("usern");
				System.out.println(s);
				 textv = (TextView)findViewById(R.id.textv);

				DatabaseHelper db = new DatabaseHelper(this);
				user.setUsername(str_usern);
				user.setPassword(s);
				textv.setText("Your formula: "+ s);
				db.addUser(user);

			}
	@Override
	public void onBackPressed() {

		finish();
		Intent intent = new Intent(ConfirmationActivity.this, UsernameActivity.class);
		startActivity(intent);
	}
			

}
