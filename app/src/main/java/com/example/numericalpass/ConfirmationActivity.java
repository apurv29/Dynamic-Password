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

				//user.getUsername(str_usern);
				// db = openOrCreateDatabase("NumericalPass", Context.MODE_PRIVATE, null);
				 //db.execSQL("CREATE TABLE IF NOT EXISTS NumPass2(_id INTEGER PRIMARY KEY, password VARCHAR(25));");
				
				// db.execSQL("INSERT into NumPass2(password) values ('" + s + "')");
				 
				
				// Cursor c = db.rawQuery("SELECT * FROM NumPass2", null); //db.execSQL("SELECT * FROM NumPass");
				 //System.out.println(c);


				
				 
					
						//c.moveToFirst();
						
			            //    while (c.isAfterLast() == false) { // if Cursor is not empty
			                	
			              //  	 Toast.makeText(getApplicationContext(),"hello from cursor", Toast.LENGTH_SHORT).show();
			                	
			                //	 textv.setText("Your formula: " + c.getString(1) + "\n");
			                	
			                //	 c.moveToNext();
			                	
			                // }
			               
			               
				//c.close();
			}
	@Override
	public void onBackPressed() {

		finish();
		Intent intent = new Intent(ConfirmationActivity.this, UsernameActivity.class);
		startActivity(intent);
	}
			

}
