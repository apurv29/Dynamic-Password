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

	Intent i;
	String s,str;
	TextView textv;
	SQLiteDatabase db;

			public void onCreate(Bundle savedInstanceState) {
			    super.onCreate(savedInstanceState);
			    setContentView(R.layout.confirmationlayout);
			    
			    i=getIntent();
			    
				 s = i.getStringExtra("confirmstring");
				 
				 textv = (TextView)findViewById(R.id.textv);
				 //textv.setText("Final formula: "+s);
				 
				 db = openOrCreateDatabase("NumericalPass", Context.MODE_PRIVATE, null);
				 db.execSQL("CREATE TABLE IF NOT EXISTS NumPass2(_id INTEGER PRIMARY KEY, password VARCHAR(25));");
				 //db.execSQL("CREATE TABLE IF NOT EXISTS NumPass1(password VARCHAR);");
				// db.execSQL("INSERT into NumPass1 values ('" + s + "')");
				 db.execSQL("INSERT into NumPass2(password) values ('" + s + "')");
				 
				 //db.execSQL("UPDATE NumPass SET password='"+s+"'");
				 //Cursor c=db.rawQuery("SELECT * FROM login2 WHERE username = '"+username.getText().toString()+"'", null);

			//	 Cursor c = db.rawQuery("SELECT * FROM NumPass1", null); 
				 Cursor c = db.rawQuery("SELECT * FROM NumPass2", null); //db.execSQL("SELECT * FROM NumPass");
				 System.out.println(c);
				
				 //str = c.getString( c.getColumnIndex("password") );
				
				// System.out.println(str);
				/* if(c != null){
					 if(c.moveToFirst()){
					 		System.out.println("hello from cursor");
					 Toast.makeText(getApplicationContext(),"Password stored for future use!!", Toast.LENGTH_SHORT).show();
					 str = c.getString( c.getColumnIndex("password") );
					 System.out.println(str);
					 textv.setText("Final formula: "+str);
					 	}
					 
				 }*/
				 
					//try{
						c.moveToFirst();
						//if(usr.equals(c.getString(2)) && pass.equals(c.getString(3)))
			            //{
						//Toast.makeText(getApplicationContext(),"hello from cursor", Toast.LENGTH_SHORT).show();
			              //if(c != null){
			                while (c.isAfterLast() == false) { // if Cursor is not empty
			                	// str = c.getString( c.getColumnIndex("password") );
			                	 Toast.makeText(getApplicationContext(),"hello from cursor", Toast.LENGTH_SHORT).show();
			                	// textv.append("Your formula: " + c.getString(0) + "\n");
			                	 textv.setText("Your formula: " + c.getString(1) + "\n");
			                	// Log.d("try", String.valueOf(c.getString(arg0)(c.getColumnIndex("ID"))));
			                	 c.moveToNext();
			                	// c.moveToNext();
			                	 //textv.setText("Final formula: "+s);
			                	 //showMessage("Error",id);
			                 }
			               
			                
			                /*while (cur.isAfterLast() == false) {
			                    view.append("n" + cur.getString(1));
			               	    cur.moveToNext();
			                }
			                cur.close();*/
			                
			            /*    Intent i =new Intent(this,Welcome.class);
			                i.putExtra("StringName",id);
			                startActivity(i);*/

			           // }
						
			          /*  else
			            {
			            	
			            	Toast.makeText(getApplicationContext(),"hello from else", Toast.LENGTH_SHORT).show();
			            }*/
			              
						//}
						/*catch(Exception e){
							Toast.makeText(getApplicationContext(),"hello from exception", Toast.LENGTH_SHORT).show();
						}*/
				c.close(); 
			}
			

}
