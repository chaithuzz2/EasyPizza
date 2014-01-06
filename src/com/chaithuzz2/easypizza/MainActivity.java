package com.chaithuzz2.easypizza;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	public Button customer;
	public Button merchant;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		customer = (Button) findViewById(R.id.cust);
		merchant = (Button) findViewById(R.id.merc);
		customer.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				Intent intent = new Intent(MainActivity.this,Main_customer.class);
				startActivity(intent);
				
			}
		});
		merchant.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				Intent intent = new Intent(MainActivity.this,Main_merchant.class);
				startActivity(intent);
				
			}
		});
		
	}


}
