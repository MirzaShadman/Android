package com.example.startserviceex;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Intent intent = null;
	private Messenger msngr = new Messenger(new MyHandler());

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button button = (Button) findViewById(R.id.button1);
		intent = new Intent(this, MyService.class);
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				intent.putExtra("messenger", msngr);
				startService(intent);
			}
		});
	}

	private class MyHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			Toast.makeText(MainActivity.this, ""+msg.what, Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		stopService(intent);
	}

}