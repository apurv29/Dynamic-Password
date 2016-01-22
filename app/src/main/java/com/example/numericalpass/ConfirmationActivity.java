package com.example.numericalpass;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ConfirmationActivity extends Activity {

	Intent i,iusern;
	String s,str,str_usern;
	TextView textv;
	//SQLiteDatabase db;
	User user = new User();

			public void onCreate(Bundle savedInstanceState) {
			    super.onCreate(savedInstanceState);
			    setContentView(R.layout.confirmationlayout);
			    
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
