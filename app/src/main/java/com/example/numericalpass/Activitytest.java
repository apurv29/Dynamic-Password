package com.example.numericalpass;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.operator.Operator;

import java.util.Calendar;
import java.util.Random;

public class Activitytest extends Activity{

	EditText edittext;
	Button bdone;
	String str,strfirst,evaluate;
	String h= "";
	String u = "";
	boolean checkisvalid = false;
	boolean gone = true;
	Intent iusern;
	String str_usern;
	int num, lenother, result2;
	TextView form1,form2, form3;
	int count = 0;
	OpExtend mydiv, mymul, mysqroot, mypow, mymod;

	double result1,res;


	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.first_layout);

		bdone=(Button) findViewById(R.id.bdone);
		form1 = (TextView) findViewById(R.id.form1);
		form2 = (TextView) findViewById(R.id.form2);
		form3 = (TextView) findViewById(R.id.form3);
		edittext = (EditText) findViewById(R.id.edittext);

		iusern=getIntent();
		str_usern = iusern.getStringExtra("usern");

		form1.setCompoundDrawablesWithIntrinsicBounds(
				R.drawable.form1, 0, 0, 0);

		bdone.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				strfirst = edittext.getText().toString();

				boolean check = false;
				for (int d = 0; d < strfirst.length() - 1; d++) {
					if (strfirst.charAt(d) == '+' || strfirst.charAt(d) == '-' || strfirst.charAt(d) == '*' || strfirst.charAt(d) == '/' || strfirst.charAt(d) == '\u00F7' || strfirst.charAt(d) == '\u00D7' || strfirst.charAt(d) == '^' || strfirst.charAt(d) == '%') {
						if (((((int) strfirst.charAt(d + 1)) >= 48) && (((int) strfirst.charAt(d + 1)) <= 57)) || ((((int) strfirst.charAt(d + 1)) >= 97) && (((int) strfirst.charAt(d + 1)) <= 122)) || strfirst.charAt(d + 1) == '(' || strfirst.charAt(d + 1) == '[' || strfirst.charAt(d + 1) == '{' || strfirst.charAt(d + 1) == ' ' || strfirst.charAt(d + 1) == '\u221A' || strfirst.charAt(d + 1) == '\u03C0') {
							check = true;
							continue;
						} else {
							check = false;
							break;
						}
					} else if (((((int) strfirst.charAt(d)) >= 48) && (((int) strfirst.charAt(d)) <= 57)) || ((((int) strfirst.charAt(d)) >= 97) && (((int) strfirst.charAt(d)) <= 122)) || strfirst.charAt(d) == '\u03C0') {
						if (strfirst.charAt(d + 1) == '+' || strfirst.charAt(d + 1) == '-' || strfirst.charAt(d + 1) == '*' || strfirst.charAt(d + 1) == '\u00F7' || strfirst.charAt(d + 1) == '\u00D7' || strfirst.charAt(d + 1) == '/' || strfirst.charAt(d + 1) == ')' || strfirst.charAt(d + 1) == ']' || strfirst.charAt(d + 1) == '}' || strfirst.charAt(d + 1) == ' ' || strfirst.charAt(d + 1) == '\u221A' || strfirst.charAt(d + 1) == '^' || strfirst.charAt(d + 1) == '%') {
							check = true;
							continue;
						} else {
							check = false;
							break;
						}
					} else if (strfirst.charAt(d) == '(' || strfirst.charAt(d) == '[' || strfirst.charAt(d) == '{' || strfirst.charAt(d + 1) == ' ' || strfirst.charAt(d) == '\u221A') {
						if (((((int) strfirst.charAt(d + 1)) >= 48) && (((int) strfirst.charAt(d + 1)) <= 57)) || ((((int) strfirst.charAt(d + 1)) >= 97) && (((int) strfirst.charAt(d + 1)) <= 122)) || strfirst.charAt(d + 1) == '(' || strfirst.charAt(d + 1) == '[' || strfirst.charAt(d + 1) == '{' || strfirst.charAt(d + 1) == ' ' || strfirst.charAt(d + 1) == '\u221A' || strfirst.charAt(d + 1) == '\u03C0') {
							check = true;
							continue;
						} else {
							check = false;
							break;
						}
					} else if (strfirst.charAt(d) == ')' || strfirst.charAt(d) == ']' || strfirst.charAt(d) == '}' || strfirst.charAt(d + 1) == ' ' || strfirst.charAt(d + 1) == '\u221A') {
						if (strfirst.charAt(d + 1) == '+' || strfirst.charAt(d + 1) == '-' || strfirst.charAt(d + 1) == '*' || strfirst.charAt(d + 1) == '/' || strfirst.charAt(d + 1) == '\u00F7' || strfirst.charAt(d + 1) == '^' || strfirst.charAt(d + 1) == '%' || strfirst.charAt(d + 1) == '\u00D7' || strfirst.charAt(d + 1) == ')' || strfirst.charAt(d + 1) == ']' || strfirst.charAt(d + 1) == '}' || strfirst.charAt(d + 1) == ' ' || strfirst.charAt(d + 1) == '\u221A') {
							check = true;
							continue;
						} else {
							check = false;
							break;
						}
					}
				}
				if (check == false) {
					Toast.makeText(getApplicationContext(), "invalid string !!", Toast.LENGTH_SHORT).show();
				} else {
					str = strfirst.replaceAll("\\s+", "");
					int len = str.length();
					int g = 0;

					while (g < len) {
						if (str.charAt(g) >= 97 && str.charAt(g) <= 122) {
							count++;
						}
						g++;
					}

					lenother = count;
					Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
					int i = 0;
					int j = 0;
					char[] arraystring = new char[len];
					char[] arrayvariables = new char[lenother];
					char[] temp = new char[lenother];
					try {
						for (i = 0; i <= len - 1; i++) {

							arraystring[i] = str.charAt(i);
							Random r = new Random();
							int maximum = 57;
							int minimum = 49;
							int range = maximum - minimum + 1;
							num = r.nextInt(range) + minimum;

							if (arraystring[i] >= 97 && arraystring[i] <= 122)

							{

								if (j >= 0) {

									arrayvariables[j] = arraystring[i];
									temp[j] = arrayvariables[j];
									temp[j] = (char) (num);
									j++;
								}


							}

							if (arraystring[i] >= 97 && arraystring[i] <= 122) {

								arraystring[i] = (char) (num);

							} else {
								switch (arraystring[i]) {
									case 1:
										arraystring[i] = '1';
										break;
									case 2:
										arraystring[i] = '2';
										break;
									case 3:
										arraystring[i] = '3';
										break;
									case 4:
										arraystring[i] = '4';
										break;
									case 5:
										arraystring[i] = '5';
										break;
									case 6:
										arraystring[i] = '6';
										break;
									case 7:
										arraystring[i] = '7';
										break;
									case 8:
										arraystring[i] = '8';
										break;
									case 9:
										arraystring[i] = '9';
										break;
									case 0:
										arraystring[i] = '0';
										break;
									default:

										continue;
								}
							}

						}
					} catch (Exception e) {
						Toast.makeText(getApplicationContext(), "not allowed", Toast.LENGTH_SHORT).show();
					}

					for (int l = 0; l < str.length(); l++) {
						if (str.charAt(l) == '\u221A' || str.charAt(l) == '\u00D7' || str.charAt(l) == '\u00F7' || str.charAt(l) == '\u005E' || str.charAt(l) == '%') {
							gone = false;
						}
					}
					if (i == str.length()) {
						evaluate = new String(arraystring);
						for (int w = 0; w < str.length(); w++) {
							if (str.charAt(w) == '\u03C0') {
								String pi = "3.14";
								evaluate = evaluate.replace("\u03C0", pi);
							}
						}

						if (gone == true) {
							try {


								Expression calc = new ExpressionBuilder(evaluate).build();

								result1 = calc.evaluate();
								result1 = (double) Math.round(result1 * 100) / 100;
								checkisvalid = true;

							} catch (Exception e) {
								Toast.makeText(getApplicationContext(), "not allowed", Toast.LENGTH_SHORT).show();
								checkisvalid = false;
							}
						} else {

							for (int z = 0; z <= str.length() - 1; z++) {


								if (str.charAt(z) == '\u00F7' || str.charAt(z) == '\u00D7' || str.charAt(z) == '\u221A' || str.charAt(z) == '\u005E' || str.charAt(z) == '%') {
									try {
										mydiv = new OpExtend("\u00F7", 2, true, Operator.PRECEDENCE_DIVISION);
										mymul = new OpExtend("\u00D7", 2, true, Operator.PRECEDENCE_MULTIPLICATION);
										mysqroot = new OpExtend("\u221A", 1, true, Operator.PRECEDENCE_POWER + 1);
										mypow = new OpExtend("^", 2, true, Operator.PRECEDENCE_POWER);
										mymod = new OpExtend("%", 2, true, Operator.PRECEDENCE_MODULO);
									} catch (Exception e) {
										Toast.makeText(getApplicationContext(), "not allowed", Toast.LENGTH_SHORT).show();
									}


									try {
										res = new ExpressionBuilder(evaluate)
												.operator(mydiv.div).operator(mymul.mul).operator(mysqroot.sqroot).operator(mypow.powsymbol).operator(mymod.modulo)
												.build()

												.evaluate();
										checkisvalid = true;
									} catch (Exception e) {
										Toast.makeText(getApplicationContext(), "invalid string !!", Toast.LENGTH_SHORT).show();
									}

									result1 = (double) Math.round(res * 100) / 100;
								}


							}

						}


						if (checkisvalid == true) {
							result2 = (int) result1;
							Intent setintent = new Intent();
							setintent.setClass(Activitytest.this, RegistrationActivity.class);
							setintent.putExtra("c", str);
							setintent.putExtra("something", arrayvariables);
							setintent.putExtra("values", temp);
							setintent.putExtra("result", result1);

							setintent.putExtra("result2", result2);
							setintent.putExtra("usern", str_usern);


							int templen = temp.length;
							for (int p = 0; p <= templen - 1; p++) {
								u = arrayvariables[p] + "=" + temp[p];
								h = h.concat("\n" + u);

							}
							setintent.putExtra("getstring", h);

							long timeSpent = Calendar.getInstance().getTimeInMillis() - startTime;
							CSVeditor.shared().recordTimeStamp(timeSpent, 6);

							startActivity(setintent);
						}


					}


				}
			}
		});
		form1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				edittext.setText("(a+b)^2", TextView.BufferType.EDITABLE);
			}
		});

		form2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				edittext.setText("(a*b-c)/d", TextView.BufferType.EDITABLE);
			}
		});
		form3.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				edittext.setText("2*(a-b)", TextView.BufferType.EDITABLE);
			}
		});

	}

	long startTime;
	@Override
	protected void onResume() {
		super.onResume();
		startTime = Calendar.getInstance().getTimeInMillis();
	}

	@Override
	public void onBackPressed() {
		Toast.makeText(Activitytest.this, "Please complete the signUp process", Toast.LENGTH_SHORT).show();
	}
}
	
