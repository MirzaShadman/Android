package com.example.servicecommunication;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Intent intent = null;
	private BroadcastReceiver receiver = new Receiver();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button button = (Button) findViewById(R.id.button1);
		intent = new Intent(this, MyService.class);
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startService(intent);
			}
		});
	}

	@Override
	public void onStart() {
		super.onStart();
		IntentFilter filter = new IntentFilter("COM.EX.RECEIVER");
		registerReceiver(receiver, filter);
	}

	@Override
	public void onStop() {
		super.onStop();
		unregisterReceiver(receiver);
	}

	private class Receiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			String msg = intent.getExtras().getString("extra");
			Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
		}
		
	}
	@Override
	public void onDestroy() {
		super.onDestroy();
		stopService(intent);
	}

}