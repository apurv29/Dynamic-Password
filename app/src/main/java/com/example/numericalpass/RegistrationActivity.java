package com.example.numericalpass;
import java.lang.reflect.Array;
import java.util.Arrays;








import android.R.array;
import android.app.Activity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class RegistrationActivity extends Activity implements OnClickListener{

	String s,s1,str,sfinalvar;
	Double ans;
	Integer ans1;
	String temp ="";
	Intent i,ianswer,ianswer1,ip;
	
	TextView tv,tv1;
	EditText et1;
	Button button1,button2;
	boolean flag = false;
	
	int j=0;
	int k=0;
	
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testform);
        
        i=getIntent();
        
   	 s = i.getStringExtra("c");
   	 ip=getIntent();
    
	 sfinalvar = ip.getStringExtra("yo");
   	 ianswer=getIntent();
	 ans= ianswer.getDoubleExtra("result", 0.0);
	 
   	 ianswer1=getIntent();
	 ans1= ianswer1.getIntExtra("result2", 0);
	System.out.println(ans1);
	et1=(EditText)findViewById(R.id.et1);
	button1=(Button)findViewById(R.id.button1);
   	

	
   	Bundle extras = getIntent().getExtras();
    char[] myArr= extras.getCharArray("something");
    char[] myArr1= extras.getCharArray("values");
   

    Log.d("RegistrationActivity", "Received Array from intent"+ Arrays.toString(myArr));
   	tv=(TextView)findViewById(R.id.tv);
   	tv.setText("Test your Formula:  " + s);
   
   
   	tv1=(TextView)findViewById(R.id.tv1);
   	tv1.setText("Values for variables  " + sfinalvar);
   
    button1=(Button)findViewById(R.id.button1);
    button1.setOnClickListener(this);
    button2=(Button)findViewById(R.id.button2);
    button2.setOnClickListener(this);

   	
   
	
/*	button1.setOnClickListener(new View.OnClickListener() {
		@ Override
        public void onClick(View v) {
			
			
			if(et1.getText().toString().equals(ans.toString()) || et1.getText().toString().equals(ans1.toString())) {
				
				Toast.makeText(getApplicationContext(),"You are right", Toast.LENGTH_SHORT).show();
				
			}
			else
				Toast.makeText(getApplicationContext(),"Let's try again!!", Toast.LENGTH_SHORT).show();
		

        }
		
    });
	
	
	/*button2.setOnClickListener(new View.OnClickListener() {
		@ Override
        public void onClick(View v) {
			
			Toast.makeText(getApplicationContext(),"confirm", Toast.LENGTH_SHORT).show();
			
		

        }
		
    });
	*/
    

	}	
	public void onClick(View v) {
		
		
		if(v == button1){
			if(et1.getText().toString().equals(ans.toString()) || et1.getText().toString().equals(ans1.toString())){
				Toast.makeText(getApplicationContext(),"You are right", Toast.LENGTH_SHORT).show();
				flag = true;
				System.out.println("flag from b1" + flag);
			}
			else
			{
				Toast.makeText(getApplicationContext(),"Let's try again!!", Toast.LENGTH_SHORT).show();
			}
		}
	if(v == button2){
		if(flag == true){
			System.out.println("flag from b2"+flag);
			Toast.makeText(getApplicationContext(),"confirm", Toast.LENGTH_SHORT).show();
			Intent setintent = new Intent();
	      	  setintent.setClass(RegistrationActivity.this, ConfirmationActivity.class);
	      	 setintent.putExtra("confirmstring",s);
	      	 startActivity(setintent);
		}
		if(flag == false){
			System.out.println("flag from b2"+flag);
			Toast.makeText(getApplicationContext(),"Get your formula right", Toast.LENGTH_SHORT).show();
		}
		}
	}
		/*(et1.getText().toString().equals(ans.toString()) || et1.getText().toString().equals(ans1.toString())) {
			
			

			
		}
		else
			Toast.makeText(getApplicationContext(),"Let's try again!!", Toast.LENGTH_SHORT).show();*/
	

  //  }*/
	@Override
	public void onBackPressed() {
	   Log.d("CDA", "onBackPressed Called");
	  Intent setIntent = new Intent(RegistrationActivity.this, Activitytest.class);
	  setIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	   
	   startActivity(setIntent);
	  
	   
	}


}
	
