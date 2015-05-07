package com.example.servicecommunication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

public class MyService extends Service {

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Intent broadcastIntent = new Intent();
		broadcastIntent.putExtra("extra", "message from service");
		broadcastIntent.setAction("COM.EX.RECEIVER");
		sendBroadcast(broadcastIntent);
		return START_STICKY;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
//		Toast.makeText(getApplicationContext(), "destroy", Toast.LENGTH_LONG).show();
		Log.e("destroy", "Destroy");
	}
}
