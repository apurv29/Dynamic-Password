package com.example.numericalpass;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


public class RegistrationActivity extends Activity implements OnClickListener{

	String stringform,sfinalvar,str_usern;
	Double ans;
	Integer ans1;
	Intent i,ianswer,ianswer1,ip,iusern;
	TextView tv,tv1;
	EditText et1;
	Button button1,button2;
	boolean flag = false;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.testform);
		iusern=getIntent();
		str_usern = iusern.getStringExtra("usern");
		i=getIntent();

		stringform = i.getStringExtra("c");
		ip=getIntent();

		sfinalvar = ip.getStringExtra("getstring");
		ianswer=getIntent();
		ans= ianswer.getDoubleExtra("result", 0.00);
		ianswer1=getIntent();
		ans1= ianswer1.getIntExtra("result2", 0);
		et1=(EditText)findViewById(R.id.et1);
		button1=(Button)findViewById(R.id.button1);

		Bundle extras = getIntent().getExtras();

		tv=(TextView)findViewById(R.id.tv);
		tv.setText("Formula:  " + stringform);

		button1=(Button)findViewById(R.id.button1);
		button1.setOnClickListener(this);
		button2=(Button)findViewById(R.id.button2);
		button2.setOnClickListener(this);

		tv1=(TextView)findViewById(R.id.tv1);

		tv1.setText("Round off till 2 decimal digits or enter only the integer value \n Value of pi: 3.14 \n Substitute these values in the above formula: \n  " + sfinalvar);
		tv1.setMovementMethod(new ScrollingMovementMethod());
	}

	public void onClick(View v) {

		if(v == button1){
			if(et1.getText().toString().equals(ans.toString()) || et1.getText().toString().equals(ans1.toString())){
				Toast.makeText(getApplicationContext(),"You are right", Toast.LENGTH_SHORT).show();
				flag = true;
			}
			else
			{
				Toast.makeText(getApplicationContext(),"Let's try again!!", Toast.LENGTH_SHORT).show();
			}
		}
		if(v == button2){
			if(flag == true){
				Toast.makeText(getApplicationContext(),"confirm", Toast.LENGTH_SHORT).show();
				Intent setintent = new Intent();
				setintent.setClass(RegistrationActivity.this, ConfirmationActivity.class);
				setintent.putExtra("confirmstring",stringform);
				setintent.putExtra("usern", str_usern);

				long timeSpent = Calendar.getInstance().getTimeInMillis() - startTime;
				CSVeditor.shared().recordTimeStamp(timeSpent, 7);

				startActivity(setintent);
			}
			if(flag == false){
				Toast.makeText(getApplicationContext(),"Get your formula right", Toast.LENGTH_SHORT).show();
			}
		}
	}

	@Override
	public void onBackPressed() {
		Intent setIntent = new Intent(RegistrationActivity.this, Activitytest.class);
		setIntent.putExtra("usern",str_usern);
		setIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

		startActivity(setIntent);
	}

	long startTime;
	@Override
	protected void onResume() {
		super.onResume();
		startTime = Calendar.getInstance().getTimeInMillis();
	}
}
	
