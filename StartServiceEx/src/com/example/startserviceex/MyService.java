package com.example.startserviceex;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

public class MyService extends Service {

	private int selfId = 0;
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		selfId = startId;
		Messenger msngr = (Messenger) intent.getExtras().get("messenger");
		Message msg = Message.obtain();
		msg.what = 200;
		try {
			msngr.send(msg);
		} catch(RemoteException re) {
			re.printStackTrace();
		}
		return START_STICKY;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
//		Toast.makeText(getApplicationContext(), "destroy", Toast.LENGTH_LONG).show();
		Log.e("destroy", "Destroy");
	}
}
