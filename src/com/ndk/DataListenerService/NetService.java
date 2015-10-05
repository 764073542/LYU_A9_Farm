package com.ndk.DataListenerService;

import java.net.DatagramSocket;
import java.net.SocketException;

import com.ndk.utils.DataProvide;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class NetService extends Service {
	private NetReceiveService netReceiveService;
	private NetSendService netSendService;
	private DatagramSocket netUdpSocket = null;// 接收数据Socket

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	private void init() {
		/* 初始化创建接收发送线程对象，并启动线程 */
		/**
		 * 创建网络数据发送接收线程
		 */
		try {
			netUdpSocket = new DatagramSocket(DataProvide.NET_RECV_PORT);
		} catch (SocketException e) {
			Log.e("Farm", "DatagramSocket创建失败");
			DataProvide.NET_STATE = false;
		}
		netSendService = new NetSendService(netUdpSocket);
		netReceiveService = new NetReceiveService(netUdpSocket);
		netReceiveService.startReceive();
		netSendService.startSend();
	}

	@Override
	public void onCreate() {
		Log.d("Farm", "服务已经被创建");
		super.onCreate();
		init();// 初始化
	}

	@Override
	public void onStart(Intent intent, int startId) {
		Log.d("Farm", "NetService服务已经被启动");
		super.onStart(intent, startId);
	}

//	private void stop() {
//		Log.i("Farm", "执行stop");
//		netSendService.stopService();
//	}
	public void onDestroy() {
		super.onDestroy();
		DataProvide.NET_STATE = false;
		netSendService.stopService();
		netReceiveService.stopService();
		Log.d("Farm", "服务已经被销毁");
		System.exit(0);
	};
}
