package com.chaithuzz2.easypizza;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class customer_login extends Activity {
	
	public int connection_success = 1;
	public Button login;
	public TextView email;
	public TextView password;
	public TextView signup;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.customer_login);
		login = (Button) findViewById(R.id.btnLogin);
		email = (TextView) findViewById(R.id.email);
		password = (TextView) findViewById(R.id.password);
		signup = (TextView) findViewById(R.id.link_to_register);
		login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				Intent intent = new Intent(customer_login.this,Main_customer.class);
				startActivity(intent);
				
			}
		});
		
	}

}
