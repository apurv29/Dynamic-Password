package com.example.numericalpass;

import java.util.Arrays;
import java.util.Random;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.operator.Operator;
import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Activitytest extends Activity{

	
	EditText edittext;
	Button bdone;
	String str,strfirst,evaluate;
	String h= "";
	String u = "";
	boolean checkisvalid = false;
	boolean gone = true;
	
	int num, lenother, result2;;
	int count = 0;


double resultdiv,resultmul,resultsqroot,result1,res;

	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        bdone=(Button) findViewById(R.id.bdone);
        edittext = (EditText) findViewById(R.id.edittext);
      
       
	   
	
	bdone.setOnClickListener(new View.OnClickListener() {
					@ Override
			        public void onClick(View v) {
						
						
							strfirst = edittext.getText().toString();
				
			boolean check= false;
			for(int d = 0; d< strfirst.length()-1; d++)
			{
						if(strfirst.charAt(d) == '+' || strfirst.charAt(d) == '-' || strfirst.charAt(d) == '*' || strfirst.charAt(d) == '/' || strfirst.charAt(d) == '\u00F7' || strfirst.charAt(d) == '\u00D7')
						{
							if(((((int)strfirst.charAt(d+1))>= 48) && (((int)strfirst.charAt(d+1))<=57)) || ((((int)strfirst.charAt(d+1))>= 97) && (((int)strfirst.charAt(d+1))<=122)) || strfirst.charAt(d+1) == '(' || strfirst.charAt(d+1)=='[' || strfirst.charAt(d+1)=='{' || strfirst.charAt(d+1)==' ' || strfirst.charAt(d+1)=='\u221A' || strfirst.charAt(d+1) == '\u03C0')
							{
								check =true;
								continue;
							}
							else
							{
								check=false;
								break;
							}
						}
						else if(((((int)strfirst.charAt(d))>= 48) && (((int)strfirst.charAt(d))<=57)) || ((((int)strfirst.charAt(d))>= 97) && (((int)strfirst.charAt(d))<=122)) || strfirst.charAt(d) == '\u03C0')
						{
							if(strfirst.charAt(d+1)=='+' || strfirst.charAt(d+1)=='-' || strfirst.charAt(d+1)=='*' || strfirst.charAt(d+1) == '\u00F7' || strfirst.charAt(d+1) == '\u00D7' || strfirst.charAt(d+1)=='/'  || strfirst.charAt(d+1) == ')' || strfirst.charAt(d+1)==']' || strfirst.charAt(d+1) == '}' || strfirst.charAt(d+1)==' ' || strfirst.charAt(d+1)=='\u221A')
							{
								check = true;
								continue;
							}
							else
							{
								check=false;
								break;
							}
						}
						else if(strfirst.charAt(d) == '(' || strfirst.charAt(d) == '[' || strfirst.charAt(d) == '{' || strfirst.charAt(d+1)==' ' || strfirst.charAt(d)=='\u221A')
						{
							if(((((int)strfirst.charAt(d+1))>= 48) && (((int)strfirst.charAt(d+1))<=57)) || ((((int)strfirst.charAt(d+1))>= 97) && (((int)strfirst.charAt(d+1))<=122)) || strfirst.charAt(d+1) == '(' || strfirst.charAt(d+1) == '[' || strfirst.charAt(d+1) == '{' || strfirst.charAt(d+1)==' ' || strfirst.charAt(d+1)=='\u221A' || strfirst.charAt(d+1) == '\u03C0')
							{
								check=true;
								continue;
							}
							else
							{ 
								check = false;
								break;
							}
						}
							else if(strfirst.charAt(d)==')' || strfirst.charAt(d)==']' || strfirst.charAt(d)=='}' || strfirst.charAt(d+1)==' ' || strfirst.charAt(d+1)=='\u221A')
						{
							if(strfirst.charAt(d+1)=='+' || strfirst.charAt(d+1)=='-' || strfirst.charAt(d+1)=='*' || strfirst.charAt(d+1)=='/' || strfirst.charAt(d+1) == '\u00F7' || strfirst.charAt(d+1) == '\u00D7' || strfirst.charAt(d+1) == ')' || strfirst.charAt(d+1) == ']' || strfirst.charAt(d+1) == '}' || strfirst.charAt(d+1)==' ' || strfirst.charAt(d+1)=='\u221A')
							{
								check=true;
								continue;
							}
							else
							{
								check=false;
								break;
							}
						}	
			}
			if(check == false)
			{
				System.out.println("invalid");
				 Toast.makeText(getApplicationContext(),"invalid string !!", Toast.LENGTH_SHORT).show();
			}
			else
			{
				 str = strfirst.replaceAll("\\s+","");
				System.out.println(str);
						int len = str.length(); 
						int g =0;
						
						while(g < len)	{
							
							if(str.charAt(g) >= 97 && str.charAt(g)<=122)
							{
								count++;
								
							}
							
							g++;
			
						}
						lenother = count;
				     	   Toast.makeText(getApplicationContext(),str, Toast.LENGTH_SHORT).show();
				     	  int i = 0;
				     	  int j = 0;
				     	  char[] arraystring= new char[len];   
				     	  char[] arrayvariables= new char[lenother];
				     	 char[] temp= new char[lenother];
				     try{
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
				      			 if(arraystring[i]>=97 && arraystring[i] <=122)
				      		
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
				      			 	
				      		 if(arraystring[i]>=97 && arraystring[i] <=122){
				      			
				            	arraystring[i]=(char)(num);
				            	
				            	System.out.println(num);
				            	System.out.println(arraystring[i]);
				      		 }
				      			
				      			else{
				      				switch(arraystring[i]){
				      							case 1:
				      								arraystring[i] = '1';
				      								break;
				      							case 2:
				      								arraystring[i]='2';
				      								break;
				      							case 3:
				      								arraystring[i]='3';
				      								break;
				      							case 4:
				      								arraystring[i]='4';
				      								break;
				      							case 5:
				      								arraystring[i]='5';
				      								break;
				      							case 6:
				      								arraystring[i]='6';
				      								break;
				      							case 7:
				      								arraystring[i]='7';
				      								break;
				      							case 8:
				      								arraystring[i]='8';
				      								break;
				      							case 9:
				      								arraystring[i]='9';
				      								break;
				      							case 0:
				      								arraystring[i]='0';
				      								break;
				      							default:
				      								
				      							continue;
				      				}
				      			}
				      		
				     	  }
					}
					catch(Exception e){
						Toast.makeText(getApplicationContext(),"not allowed", Toast.LENGTH_SHORT).show();
					}
				     	
				     for(int l = 0; l< str.length(); l++){
				    	 if(str.charAt(l)== '\u221A' || str.charAt(l)== '\u00D7' || str.charAt(l)== '\u00F7'){
				    		 gone = false;
				    		 System.out.println("hello from extra");
				    	 }
				     }
				     	  if(i == str.length()){
				     		 evaluate = new String(arraystring);
				     		 for(int w = 0; w< str.length(); w++){
				    	    	 if(str.charAt(w)== '\u03C0'){
				    	    		 System.out.println("hello from pi");
				    	    	
				    	    		String pi = "3.14";
				    	    		System.out.println("value of char pi"+ pi);
				    	    		evaluate= evaluate.replace("\u03C0", pi);
				    	    		 System.out.println("value of evaluate after pi"+evaluate);
				    	    	 }
				    	     }
				     		
				     		if(gone == true){
				      		try{
				      			
				           	   
				           	 Expression calc = new ExpressionBuilder(evaluate).build();
				           	Log.d("MainActivity", Arrays.toString(arraystring));
				              Log.d("MainActivity", evaluate);
				              Log.d("Mainhere", Arrays.toString(arrayvariables));
				           	 result1=calc.evaluate();
				           	 System.out.println(result1);
				           	 System.out.println(result2);
				           	
				           	 checkisvalid = true;
				           	  }
				           	  
				           	  catch(Exception e){
				           		; 
				           		 
				           		 checkisvalid = false;
				           	  } 
				     		  }
				     		 else{
				     			
				     			 for(int z=0;z<=str.length()-1;z++){
				     				 
				     				 if(str.charAt(z)=='\u00F7' || str.charAt(z)=='\u00D7' || str.charAt(z)=='\u221A'){
				     					OpExtend mydiv = new OpExtend("\u00F7", 2, true, Operator.PRECEDENCE_DIVISION); 
				     					OpExtend mymul = new OpExtend("\u00D7", 2, true, Operator.PRECEDENCE_MULTIPLICATION);
				     					OpExtend mysqroot = new OpExtend("\u221A", 1, true, Operator.PRECEDENCE_POWER+1);
				     					res=new ExpressionBuilder(evaluate)
				     					.operator(mydiv.div).operator(mymul.mul).operator(mysqroot.sqroot)
				     			        .build()
				     			        .evaluate();
				     					checkisvalid = true;
				     					result1=res;
				     					
				     				 }
				     		
				     				 
				     			 }
				     			 
				     		 }
				     		
							 
				           	 if(checkisvalid == true){
				           		result2 = (int)result1;
				      	          Intent setintent = new Intent();
				      	      	  setintent.setClass(Activitytest.this, RegistrationActivity.class);
				      	      	 setintent.putExtra("c",str);
				      	      	 setintent.putExtra("something", arrayvariables);
				      	      	setintent.putExtra("values", temp);
				      	      	setintent.putExtra("result", result1);
				      	      setintent.putExtra("result2", result2);
				      	  
				      	      int templen = temp.length;
				      	      for(int p=0; p<= templen-1; p++){
				      	    	  u = arrayvariables[p] + "=" + temp[p];
				      	    	  
				      	    	
				      	    	  
				      	    	System.out.println(u); 
				      	    	 h = h.concat("\n" + u);
				      	    	
				      	      }
				      	    setintent.putExtra("getstring", h);
				      	    
				      	      
				      	     
				      	                       startActivity(setintent);
				      	     		 }
				      		 
				      
				     	  }
				           
					
			
			        }
				}
    });
	
	
		
	

	}
	


}
	
