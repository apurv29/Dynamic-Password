package com.example.numericalpass.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.numericalpass.R;
import com.example.numericalpass.objects.User;
import com.example.numericalpass.helper.CSVeditor;
import com.example.numericalpass.helper.DatabaseHelper;

import java.util.Calendar;

public class ConfirmationActivity extends Activity {

    private static final String TAG = ConfirmationActivity.class.getSimpleName();

	Intent i,iusern;
	String s,str_usern;
	TextView textv;
	User user = new User();

    // for recording the time user takes to signup : end time
    public long endSignUpTime;

			public void onCreate(Bundle savedInstanceState) {
			    super.onCreate(savedInstanceState);
			    setContentView(R.layout.confirmationlayout);

				endSignUpTime = Calendar.getInstance().getTimeInMillis();

                // insert time taken in signup process by user
                long totalSignUpTime = endSignUpTime - UsernameActivity.startTime;

				CSVeditor.shared().recordTimeStamp(totalSignUpTime, 9);
			    
			    i=getIntent();
				iusern = getIntent();
			    
				 s = i.getStringExtra("confirmstring");
				 str_usern = iusern.getStringExtra("usern");
				 textv = (TextView)findViewById(R.id.textv);

				DatabaseHelper db = new DatabaseHelper(this);
				user.setUsername(str_usern);
				user.setPassword(s);
				textv.setText("Your formula: "+ s);
				db.addUser(user);

                UsernameActivity.stopScreenSharing();

			}
	@Override
	public void onBackPressed() {

		finish();
		Intent intent = new Intent(ConfirmationActivity.this, UsernameActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);

	}
			

}
