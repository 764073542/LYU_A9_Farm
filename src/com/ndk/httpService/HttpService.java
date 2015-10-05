package com.ndk.httpService;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class HttpService extends Service {
	private HttpSendJson sendJson;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		sendJson = new HttpSendJson();
		sendJson.startSendJson();
		super.onCreate();
		Log.i("Farm", "开启json发送线程");
	}

	@Override
	public boolean stopService(Intent name) {
		sendJson.stopThread();
		return super.stopService(name);
	}

}
