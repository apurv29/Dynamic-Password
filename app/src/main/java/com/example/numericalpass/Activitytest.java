package com.example.numericalpass;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.operator.Operator;








import android.R.array;
import android.app.Activity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class Activitytest extends Activity{

	
	EditText edittext;
	Button bdone;
	String str,strfirst,evaluate;
	String h= "";
	String u = "";
	//String[] l = new String("");
	double result1;
	boolean checkisvalid = false;
	int result2;
	int num, lenother;
	int count = 0;
boolean gone = true;
Operator factorial;
	
	
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
			int g =0;//length of string (formulae)
			//for(int g=0; g<len;g+=2) {
			while(g < len)	{
				//if(str.charAt(g) == 'a' || str.charAt(g) == 'b' || str.charAt(g) == 'c' || str.charAt(g) == 'd' || str.charAt(g) == 'e' || str.charAt(g) == 'f' || str.charAt(g) == 'g' || str.charAt(g) == 'h' || str.charAt(g) == 'i' || str.charAt(g) == 'j' || str.charAt(g) == 'k' || str.charAt(g) == 'l' || str.charAt(g) == 'm' || str.charAt(g) == 'n' || str.charAt(g) == 'o' || str.charAt(g) == 'p' || str.charAt(g) == 'q' || str.charAt(g) == 'r' || str.charAt(g) == 's' || str.charAt(g) == 't' || str.charAt(g) == 'u' || str.charAt(g) == 'v' || str.charAt(g) == 'w' || str.charAt(g) == 'x' || str.charAt(g) == 'y' || str.charAt(g) == 'z')
				if(str.charAt(g) >= 97 && str.charAt(g)<=122)
				{
					count++;
					
				}
				//g+=2;
				g++;

			}
			lenother = count;
	     	   Toast.makeText(getApplicationContext(),str, Toast.LENGTH_SHORT).show();
	     	  int i = 0;
	     	  int j = 0;
	     	  char[] arraystring= new char[len];   //array for the complete string
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
	      		//	if(arraystring[i]=='a' || arraystring[i]=='b' || arraystring[i]=='c' ||arraystring[i]=='d'||arraystring[i]=='e'||arraystring[i]=='f'||arraystring[i]=='g'||arraystring[i]=='h'||arraystring[i]=='i'||arraystring[i]=='j' ||arraystring[i]=='k' ||arraystring[i]=='l' ||arraystring[i]=='m' ||arraystring[i]=='n' ||arraystring[i]=='o' ||arraystring[i]=='p' ||arraystring[i]=='q' ||arraystring[i]=='r' ||arraystring[i]=='s' ||arraystring[i]=='t' ||arraystring[i]=='u' ||arraystring[i]=='v' ||arraystring[i]=='w' ||arraystring[i]=='x' ||arraystring[i]=='y' ||arraystring[i]=='z')
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
	      			//if(arraystring[i]=='a' || arraystring[i]=='b' || arraystring[i]=='c' ||arraystring[i]=='d'||arraystring[i]=='e'||arraystring[i]=='f'||arraystring[i]=='g'||arraystring[i]=='h'||arraystring[i]=='i'||arraystring[i]=='j' ||arraystring[i]=='k' ||arraystring[i]=='l' ||arraystring[i]=='m' ||arraystring[i]=='n' ||arraystring[i]=='o' ||arraystring[i]=='p' ||arraystring[i]=='q' ||arraystring[i]=='r' ||arraystring[i]=='s' ||arraystring[i]=='t' ||arraystring[i]=='u' ||arraystring[i]=='v' ||arraystring[i]=='w' ||arraystring[i]=='x' ||arraystring[i]=='y' ||arraystring[i]=='z'){
	      		            //	arrayvariables[i]=(char)(num);
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
	      								//break;
	      							continue;
	      				}
	      			}
	      		
	     	  }
		}
		catch(Exception e){
			Toast.makeText(getApplicationContext(),"not allowed", Toast.LENGTH_SHORT).show();
		}
	     	
	     for(int l = 0; l< str.length(); l++){
	    	 if(str.charAt(l)== '\u03C0' || str.charAt(l)== '\u221A' || str.charAt(l)== '\u00D7' || str.charAt(l)== '\u00F7'){
	    		 gone = false;
	    		 System.out.println("hello from extra");
	    	 }
	     }
	     	  if(i == str.length()){
	     		 evaluate = new String(arraystring);
	     		if(gone == true){
	      		try{
	      			
	           	   
	           	 Expression calc = new ExpressionBuilder(evaluate).build();
	           	Log.d("MainActivity", Arrays.toString(arraystring));
	              Log.d("MainActivity", evaluate);
	              Log.d("Mainhere", Arrays.toString(arrayvariables));
	           	 result1=calc.evaluate();//string to evaluate
	           	result2 = (int)result1;
	           	 System.out.println(result1);
	           	 System.out.println(result2);
	           	// String strans = result1.toString();
	           	 checkisvalid = true;
	           	  }
	           	  
	           	  catch(Exception e){
	           		; 
	           		// Toast.makeText(getApplicationContext(),"invalid string", Toast.LENGTH_SHORT).show(); 
	           		 checkisvalid = false;
	           	  } 
	     		  }
	     		 else{
	     			/* ExOp(str);
	     			 double cr = new ExpressionBuilder(evaluate)
	 		        .operator(factorial)
	 		        .build()
	 		        .evaluate();
	     			 
	     			 System.out.println("value for a-"+evaluate+"factorial ans-"+cr);*/
	     			 
	     			 
	     			 //using instance of OpExtend
	     			 //OpExtend div = new OpExtend(evaluate, 2, true, j);
	     			 
	     			 //OpExtend div,mul;
	     			 for(int z=0;z<=str.length()-1;z++){
	     				 
	     				 switch(str.charAt(z)){
	     				 
	     				 case '\u00F7':{
	     					OpExtend myoperatordiv = new OpExtend("\u00F7", 2, true, Operator.PRECEDENCE_DIVISION);
	     					System.out.println("string here: "+ evaluate);
	     					double resultdiv = new ExpressionBuilder(evaluate)
	     			        .operator(myoperatordiv)
	     			        .build()
	     			        .evaluate();
	     					System.out.println("hello from divide"+ resultdiv);
	     					break;
	     					
	     				 }
	     				case '\u00D7':{
	     					OpExtend myoperatormul = new OpExtend("\u00D7", 2, true, Operator.PRECEDENCE_MULTIPLICATION);
	     					System.out.println("string here: "+ evaluate);
	     					double resultmul = new ExpressionBuilder(evaluate)
	     			        .operator(myoperatormul)
	     			        .build()
	     			        .evaluate();
	     					System.out.println("hello from multiply"+ resultmul);
	     					break;
	     					
	     				 }
	     				
	     				case '\u221A':{
	     					OpExtend myoperatorsqroot = new OpExtend("\u221A", 1, true, Operator.PRECEDENCE_POWER+1);
	     					System.out.println("string here: "+ evaluate);
	     					double resultsqroot = new ExpressionBuilder(evaluate)
	     			        .operator(myoperatorsqroot)
	     			        .build()
	     			        .evaluate();
	     					System.out.println("hello from sqroot"+ resultsqroot);
	     					break;
	     					
	     				 }
	     				case '\u03C0':{
	     					OpExtend myoperatorsqroot = new OpExtend("\u03C0", 1, true, Operator.PRECEDENCE_POWER-1);
	     					System.out.println("string here: "+ evaluate);
	     					double resultpi = new ExpressionBuilder(evaluate)
	     			        .operator(myoperatorsqroot)
	     			        .build()
	     			        .evaluate();
	     					System.out.println("hello from pi"+ resultpi);
	     					break;
	     					
	     				 }
	     				}
	     			 }
	     			 
	     		  }
	           	 if(checkisvalid == true){
	      	          Intent setintent = new Intent();
	      	      	  setintent.setClass(Activitytest.this, RegistrationActivity.class);
	      	      	 setintent.putExtra("c",str);
	      	      	 setintent.putExtra("something", arrayvariables);
	      	      	setintent.putExtra("values", temp);
	      	      	setintent.putExtra("result", result1);
	      	      setintent.putExtra("result2", result2);
	      	   // String[] a = new String[len/2+1];
	      	  //char[] l = new char[len/2+1];
	      	      int templen = temp.length;
	      	      for(int p=0; p<= templen-1; p++){
	      	    	  u = arrayvariables[p] + "=" + temp[p];
	      	    	  
	      	    	// a[p] =  u;
	      	    	  
	      	    	System.out.println(u); 
	      	    	 h = h.concat("\n" + u);
	      	    	// l = h.split("\n");
	      	    	 
	      	    	//System.out.println(a[p]);  
	      	      }
	      	    setintent.putExtra("yo", h);
	      	    
	      	      
	      	      	// Toast.makeText(getApplicationContext(),result1, Toast.LENGTH_SHORT).show();
	      	                       startActivity(setintent);
	      	     		 }
	      		 
	      
	     	  }
	           
		

        }
	}
    });
	
	
		
	

	}
	
	/*public double ExOp(String str){
		
		System.out.println("hello from ExOp");
		factorial = new Operator("!", 1, true, Operator.PRECEDENCE_POWER + 1) {

		    @Override
		    public double apply(double... args) {
		        final int arg = (int) args[0];
		        if ((double) arg != args[0]) {
		            throw new IllegalArgumentException("Operand for factorial has to be an integer");
		        }
		        if (arg < 0) {
		            throw new IllegalArgumentException("The operand of the factorial can not be less than zero");
		        }
		        double re = 1;
		        for (int i = 1; i <= arg; i++) {
		            re *= i;
		        }
		        return re;
		    }
		};

//		 double cr = new ExpressionBuilder(str)
//		        .operator(factorial)
//		        .build()
//		        .evaluate();
//		 System.out.println(cr);
		 checkisvalid = true;
		 return 0.0;
		 
		 
	}*/

}
	
