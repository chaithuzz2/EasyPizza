package com.chaithuzz2.easypizza;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Main_merchant extends Activity {
	
	public EditText edit_ordercode;
	public EditText edit_customername;
	public EditText edit_ordersummary;
	public EditText edit_time;
	public EditText edit_amount;
	public EditText edit_deliverermobile;
	public Button create_order;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_merchant);
		
		 
		edit_ordercode = (EditText) findViewById(R.id.OrderCode);
		edit_customername =  (EditText) findViewById(R.id.CustomerName);
		edit_ordersummary = (EditText) findViewById(R.id.orderSummary);
		edit_time = (EditText) findViewById(R.id.merchantTime);
		edit_amount = (EditText) findViewById(R.id.BillAmount);
		edit_deliverermobile = (EditText) findViewById(R.id.DelivMobile);
		create_order = (Button) findViewById(R.id.cust);
		create_order.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				String ordercode = edit_ordercode.getText().toString();
				String customername = edit_customername.getText().toString();
				String ordersummary = edit_ordersummary.getText().toString();
				String amount = edit_amount.getText().toString();
				String time = edit_time.getText().toString();
				String deliverermobile = edit_deliverermobile.getText().toString();
				if(ordersummary.matches("") || amount.matches("") || time.matches("") || deliverermobile.matches("") || ordercode.matches("") || customername.matches("")){
					
					Toast.makeText(getBaseContext(), 
	                        "Please enter all fields", 
	                        Toast.LENGTH_SHORT).show();
				}
				else{
					
					MyApp.ordercode=ordercode;
					MyApp.customername = customername;
					MyApp.summary = ordersummary;
					MyApp.amount = amount;
					MyApp.time = time;
					MyApp.mobile=deliverermobile;
					Intent i = new Intent(Main_merchant.this,MainActivity.class);
					Toast.makeText(getBaseContext(), 
	                        "order created", 
	                        Toast.LENGTH_SHORT).show();
					startActivity(i);
				}
			}
		});
	}
	

}

class MyApp extends Application {
	
	public static String ordercode;
	public static String customername;
	public static String summary;
	public static String mobile;
	public static String time;
	public static String amount;
	
	
}
