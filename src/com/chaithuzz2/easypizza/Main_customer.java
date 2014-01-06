package com.chaithuzz2.easypizza;

import java.math.BigDecimal;

import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PaymentActivity;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

import org.json.JSONException;

import android.telephony.SmsManager;
import android.provider.Telephony.Sms;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony.Sms;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;

import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

public class Main_customer extends Activity {
	
    // set to PaymentActivity.ENVIRONMENT_PRODUCTION to move real money.
    // set to PaymentActivity.ENVIRONMENT_SANDBOX to use your test credentials from https://developer.paypal.com
    // set to PaymentActivity.ENVIRONMENT_NO_NETWORK to kick the tires without communicating to PayPal's servers.
    private static final String CONFIG_ENVIRONMENT = PaymentActivity.ENVIRONMENT_SANDBOX;
    
    // note that these credentials will differ between live & sandbox environments.
    private static final String CONFIG_CLIENT_ID = "AZN8ahDXE0e57GOl9hIbPCIsk0YrkLH4x1tuGCcz3fxBeAmf0gZkYA9irhgC";
    // when testing in sandbox, this is likely the -facilitator email address. 
    private static final String CONFIG_RECEIVER_EMAIL = "chaithuzz2@merchant.com"; 
	
	public EditText order_code;
	public EditText order_time;
	public EditText order_tip;
	
	public TextView order_summary;
	public TextView order_payable;
	
	public LinearLayout second;
	public LinearLayout third;
	
	public Button check;
	public Button total;
	public Button payment; 
	
	public String finalamount;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_customer);
		
		Intent intent = new Intent(this, PayPalService.class);
        
        intent.putExtra(PaymentActivity.EXTRA_PAYPAL_ENVIRONMENT, CONFIG_ENVIRONMENT);
        intent.putExtra(PaymentActivity.EXTRA_CLIENT_ID, CONFIG_CLIENT_ID);
        intent.putExtra(PaymentActivity.EXTRA_RECEIVER_EMAIL, CONFIG_RECEIVER_EMAIL);
        
        startService(intent);
		
        order_code = (EditText)findViewById(R.id.code);
        order_time = (EditText)findViewById(R.id.penalty);
		order_tip = (EditText)findViewById(R.id.tip);
		
		order_summary = (TextView)findViewById(R.id.summ);
		order_payable = (TextView)findViewById(R.id.totbill);
		
		check = (Button)findViewById(R.id.check);
		total = (Button)findViewById(R.id.get_total);
		payment= (Button)findViewById(R.id.payment);
		
		second = (LinearLayout) findViewById(R.id.second);
		third = (LinearLayout) findViewById(R.id.third);
		
		second.setVisibility(second.INVISIBLE);
		third.setVisibility(third.INVISIBLE);
		
		check.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(order_code.getText().toString().matches(MyApp.ordercode)){
					order_summary.setText("Order Summary: "+MyApp.summary);
					second.setVisibility(second.VISIBLE);
				}
				else{
					Toast.makeText(getBaseContext(), 
	                        "Order not found", 
	                        Toast.LENGTH_SHORT).show();
					
				}
			}
		});
		total.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Double tpen =0.0;
				Double tip =0.0;
				Double Bill_amount= Double.parseDouble(MyApp.amount);
				Double amount_to_pay=0.0;
				if(order_time.getText().toString().length()!=0)tpen = Double.parseDouble(order_time.getText().toString());
				if(order_tip.getText().toString().length()!=0)tip = Double.parseDouble(order_tip.getText().toString());
				amount_to_pay = Bill_amount + tip - (tpen*0.1);
				finalamount = amount_to_pay.toString();
				order_payable.setText("Bill Amount : $ "+finalamount);
				third.setVisibility(third.VISIBLE);
			}
		});
		payment.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				String paid_amount = order_payable.getText().toString();
				if(paid_amount.matches(""))paid_amount="0.00";
		        PayPalPayment thingToBuy = new PayPalPayment(new BigDecimal(finalamount), "USD", MyApp.summary);
		        
		        Intent intent = new Intent(Main_customer.this, PaymentActivity.class);
		        
		        intent.putExtra(PaymentActivity.EXTRA_PAYPAL_ENVIRONMENT, CONFIG_ENVIRONMENT);
		        intent.putExtra(PaymentActivity.EXTRA_CLIENT_ID, CONFIG_CLIENT_ID);
		        intent.putExtra(PaymentActivity.EXTRA_RECEIVER_EMAIL, CONFIG_RECEIVER_EMAIL);
		        
		        // It's important to repeat the clientId here so that the SDK has it if Android restarts your 
		        // app midway through the payment UI flow.
		        intent.putExtra(PaymentActivity.EXTRA_CLIENT_ID, "AZN8ahDXE0e57GOl9hIbPCIsk0YrkLH4x1tuGCcz3fxBeAmf0gZkYA9irhgC");
		        intent.putExtra(PaymentActivity.EXTRA_PAYER_ID, "chaithuzz2@buyer.com");
		        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, thingToBuy);
		        
		        startActivityForResult(intent, 0);				
			}
		});
		
	}

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            PaymentConfirmation confirm = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
            if (confirm != null) {
                try {
                    Log.i("paymentExample", confirm.toJSONObject().toString(4));
                    sendSms(MyApp.mobile,"Dear Delivery, The amount of "+finalamount+" was recieved successfully from Mr."+MyApp.customername);
					Toast.makeText(getBaseContext(), 
	                        "Payment Done", 
	                        Toast.LENGTH_SHORT).show();                    

                    // TODO: send 'confirm' to your server for verification.
                    // see https://developer.paypal.com/webapps/developer/docs/integration/mobile/verify-mobile-payment/
                    // for more details.

                } catch (JSONException e) {
                    Log.e("paymentExample", "an extremely unlikely failure occurred: ", e);
                }
            }
        }
        else if (resultCode == Activity.RESULT_CANCELED) {
            Log.i("paymentExample", "The user canceled.");
        }
        else if (resultCode == PaymentActivity.RESULT_PAYMENT_INVALID) {
            Log.i("paymentExample", "An invalid payment was submitted. Please see the docs.");
        }
    }
    
    private void sendSms(String s, String g){
    	
        PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, Sms.class), 0);                
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(s, null, g, pi, null);
    }
    
    @Override
    public void onDestroy() {
        stopService(new Intent(this, MainActivity.class));
        super.onDestroy();
    }
}