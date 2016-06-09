package com.example.numericalpass;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.operator.Operator;

import java.util.Arrays;
import java.util.Random;

public class LoginActivity extends Activity implements View.OnClickListener {
    Intent iusern;
    String str_usern,str,evaluate;
    DatabaseHelper db;
    int count=0;
    int lenother,num,result2;
    double result1,res;
    boolean gone=true;
    boolean checkisvalid = false;
    String u="";
    String h="";
    TextView tvlogin,tv1login;
    Button blogin;
    boolean flag = false;
    EditText etlogin;
    int wrongtry = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new DatabaseHelper(this);
        iusern=getIntent();
        str_usern = iusern.getStringExtra("usern");

       String spass =  db.getPassByName(str_usern);
        System.out.println("pass: "+spass);
       str = spass.replaceAll("\\s+","");
        //str="a+b";
        System.out.println(str);
        int len = str.length();
        int g =0;
        blogin=(Button)findViewById(R.id.blogin);
        blogin.setOnClickListener(this);
        etlogin=(EditText)findViewById(R.id.etlogin);
        while(g < len)	{

            if(str.charAt(g) >= 97 && str.charAt(g)<=122)
            {
                count++;

            }

            g++;

        }
        lenother = count;
      //  Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
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

                    result1=calc.evaluate();
                    result1 = (double) Math.round(result1 * 100) / 100;
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
                       // result1=res;
                        result1 = (double) Math.round(result1 * 100) / 100;
                    }


                }

            }


            if(checkisvalid == true){
                result2 = (int)result1;
                /*Intent setintent = new Intent();
                setintent.setClass(Activitytest.this, RegistrationActivity.class);
                setintent.putExtra("c",str);
                setintent.putExtra("something", arrayvariables);
                setintent.putExtra("values", temp);
                setintent.putExtra("result", result1);
                setintent.putExtra("result2", result2);
                setintent.putExtra("usern", str_usern);*/


                int templen = temp.length;
                for(int p=0; p<= templen-1; p++){
                    u = arrayvariables[p] + "=" + temp[p];



                    System.out.println(u);
                    h = h.concat("\n" + u);

                }
                tv1login=(TextView)findViewById(R.id.tv1login);


                tv1login.setText("Substitute these variables in your formula: \n  " + h);
                tv1login.setMovementMethod(new ScrollingMovementMethod());
                //setintent.putExtra("getstring", h);



                //startActivity(setintent);
            }


        }


    }
    public void onClick(View v) {


        if (v == blogin) {

            if (etlogin.getText().toString().equals(String.valueOf(result1)) || etlogin.getText().toString().equals(String.valueOf(result2))) {

                db.updatewrongtry(str_usern,wrongtry);
                Toast.makeText(getApplicationContext(), "You are right", Toast.LENGTH_SHORT).show();
                flag = true;
                Intent intent = new Intent();
                intent.putExtra("USERNAME",str_usern);
                intent.setClass(LoginActivity.this, WelcomeActivity.class);
                //intent.putExtra("usern", u);
                startActivity(intent);


            } else {
                wrongtry++;
                if (wrongtry <= 3) {
                    Toast.makeText(getApplicationContext(), "Let's try again", Toast.LENGTH_SHORT).show();
                    flag = true;
                    System.out.println("wrongtry: " + wrongtry);
                    db.updatewrongtry(str_usern, wrongtry);
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong !!", Toast.LENGTH_SHORT).show();
                    db.updatewrongtry(str_usern,wrongtry);
                    Intent intent = new Intent();
                    intent.setClass(LoginActivity.this, UsernameActivity.class);
                    //intent.putExtra("usern", u);
                    startActivity(intent);
                    //  Toast.makeText(getApplicationContext(), "Let's try again!!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onBackPressed() {

        finish();
        Intent intent = new Intent(LoginActivity.this, UsernameActivity.class);
        startActivity(intent);
    }
}
