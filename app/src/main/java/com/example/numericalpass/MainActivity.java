package com.example.numericalpass;


import java.util.Arrays;





import java.util.Random;
import java.util.concurrent.ExecutorService;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;




public class MainActivity extends Activity implements OnClickListener{

	private static final Object[] String = null;
	Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b0;
	Button bclr,bdiv,bmul,bmin,bplu,bopenb,bcloseb,bdot,bmod,bequal;
	TextView ans;
	int num;
	
	int n=10;
	public String str ="";
	double result1;
	int k;
	 boolean checkisvalid = false;
	 String evaluate;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       
        b1=(Button) findViewById(R.id.b1);
        b2=(Button) findViewById(R.id.b2);
        b3=(Button) findViewById(R.id.b3);
        b4=(Button) findViewById(R.id.b4);
        b5=(Button) findViewById(R.id.b5);
        b6=(Button) findViewById(R.id.b6);
        b7=(Button) findViewById(R.id.b7);
        b8=(Button) findViewById(R.id.b8);
        b9=(Button) findViewById(R.id.b9);
        b0=(Button) findViewById(R.id.b0);
        
        
        bclr=(Button) findViewById(R.id.bclr);
        bdiv=(Button) findViewById(R.id.bdiv);
        bmul=(Button) findViewById(R.id.bmul);
        bmin=(Button) findViewById(R.id.bmin);
        bplu=(Button) findViewById(R.id.bplu);
        bopenb=(Button) findViewById(R.id.bopenb);
        bcloseb=(Button) findViewById(R.id.bcloseb);
        bdot=(Button) findViewById(R.id.bdot);
        bmod=(Button) findViewById(R.id.bmod);
        bequal=(Button) findViewById(R.id.bequal);
        
        ans = (TextView) findViewById(R.id.textView1);
        
      
        
        
        	
        	
        	
        }
        

	


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    
    //private OnClickListener myButtonListener = new OnClickListener() {
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		//System.out.println("hi")
		
		if(view == b1){
			 ans.setText(ans.getText()+"a");
		}
		if(view == b2){
			 ans.setText(ans.getText()+"b");
		}
		if(view == b3){
			 ans.setText(ans.getText()+"c");
		}
		if(view == b4){
			 ans.setText(ans.getText()+"d");
		}
		if(view == b5){
			 ans.setText(ans.getText()+"e");
		}
		if(view == b6){
			 ans.setText(ans.getText()+"f");
		}
		if(view == b7){
			 ans.setText(ans.getText()+"g");
		}
		if(view == b8){
			 ans.setText(ans.getText()+"h");
		}
		if(view == b9){
			 ans.setText(ans.getText()+"i");
		}
		if(view == b0){
			 ans.setText(ans.getText()+"j");
		}
		if(view == bmul){
			 ans.setText(ans.getText()+"*");
		}
		if(view == bdiv){
			 ans.setText(ans.getText()+"/");
		}
		if(view == bmin){
			 ans.setText(ans.getText()+"-");
		}
		if(view == bplu){
			 ans.setText(ans.getText()+"+");
		}
		if(view == bmod){
			 ans.setText(ans.getText()+"%");
		}
		if(view == bdot){
			 ans.setText(ans.getText()+".");
		}
		if(view == bopenb){
			 ans.setText(ans.getText()+"(");
		}
		if(view == bcloseb){
			 ans.setText(ans.getText()+")");
		}
		if(view == bclr){
			 ans.setText("");
		}
		
		if(view == bequal){
			
			str = ans.getText().toString();
     	    
    		
    		
    		int len = str.length();            //length of string (formulae)
     		    	    
     	   Toast.makeText(getApplicationContext(),str, Toast.LENGTH_SHORT).show();
     	  int i = 0;
     	  int j = 0;
     	  char[] arraystring= new char[len];   //array for the complete string
     	  char[] arrayvariables= new char[len/2+1];
     	 char[] temp= new char[len/2+1];
     	  
     	 
     	  for(i=0;i<=len-1;i++){
     		 
     		 arraystring[i] = str.charAt(i);
     		 
     		 System.out.println("hi from i");
     		 System.out.println(i);
     		 System.out.println(arraystring[i]);
     		
     		
     		Random r = new Random();
    		 int maximum = 57;
    		 int minimum = 49;
    		int range = maximum - minimum + 1;
          	num = r.nextInt(range)+minimum;

     			 System.out.println("inside loop");
     			 
     			if(arraystring[i]=='a' || arraystring[i]=='b' || arraystring[i]=='c' ||arraystring[i]=='d'||arraystring[i]=='e'||arraystring[i]=='f'||arraystring[i]=='g'||arraystring[i]=='h'||arraystring[i]=='i'||arraystring[i]=='j')
     		 {	 
     				
     				  if(j>=0){
     			
     					 arrayvariables[j]=arraystring[i];
     					temp[j]=arrayvariables[j];
     					temp[j]=(char)(num);
          		 System.out.println("after array");
          		System.out.println(arrayvariables[j]);
          		System.out.println(temp[j]);
          		j++;
     				  }
          		
          	}
     			 	

     		 if(arraystring[i]=='a' || arraystring[i]=='b' || arraystring[i]=='c' ||arraystring[i]=='d'||arraystring[i]=='e'||arraystring[i]=='f'||arraystring[i]=='g'||arraystring[i]=='h'||arraystring[i]=='i'||arraystring[i]=='j'){
     		            //	arrayvariables[i]=(char)(num);
           	arraystring[i]=(char)(num);
           	
           	System.out.println(num);
           	System.out.println(arraystring[i]);
     		 }
     		 else
     			continue;
     		 
     
          
          }
     	 
     	
     	  try{
     	  evaluate = new String(arraystring); 
     	 Expression calc = new ExpressionBuilder(evaluate).build();
     	Log.d("MainActivity", Arrays.toString(arraystring));
        Log.d("MainActivity", evaluate);
        Log.d("Mainhere", Arrays.toString(arrayvariables));
     	 result1=calc.evaluate();//string to evaluate
     	 System.out.println(result1);
     	// String strans = result1.toString();
     	 checkisvalid = true;
     	  }
     	  
     	  catch(Exception e){
     		  
     		 Toast.makeText(getApplicationContext(),"invalid string", Toast.LENGTH_SHORT).show(); 
     		 checkisvalid = false;
     	  } 
     	 if(checkisvalid == true){
	          Intent setintent = new Intent();
	      	  setintent.setClass(MainActivity.this, RegistrationActivity.class);
	      	 setintent.putExtra("c",str);
	      	 setintent.putExtra("something", arrayvariables);
	      	setintent.putExtra("values", temp);
	      	setintent.putExtra("result", result1);
	      	// Toast.makeText(getApplicationContext(),result1, Toast.LENGTH_SHORT).show();
	                       startActivity(setintent);
	     		 }
     	/* else{
     		 Toast.makeText(getApplicationContext(),"hello from invalid string", Toast.LENGTH_SHORT).show(); 
     	 }*/
     		     	  
          
          
      
		}
		
		
		
	}

   

	
	
	}
