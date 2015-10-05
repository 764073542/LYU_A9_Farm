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
	private DatagramSocket netUdpSocket = null;// ��������Socket

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	private void init() {
		/* ��ʼ���������շ����̶߳��󣬲������߳� */
		/**
		 * �����������ݷ��ͽ����߳�
		 */
		try {
			netUdpSocket = new DatagramSocket(DataProvide.NET_RECV_PORT);
		} catch (SocketException e) {
			Log.e("Farm", "DatagramSocket����ʧ��");
			DataProvide.NET_STATE = false;
		}
		netSendService = new NetSendService(netUdpSocket);
		netReceiveService = new NetReceiveService(netUdpSocket);
		netReceiveService.startReceive();
		netSendService.startSend();
	}

	@Override
	public void onCreate() {
		Log.d("Farm", "�����Ѿ�������");
		super.onCreate();
		init();// ��ʼ��
	}

	@Override
	public void onStart(Intent intent, int startId) {
		Log.d("Farm", "NetService�����Ѿ�������");
		super.onStart(intent, startId);
	}

//	private void stop() {
//		Log.i("Farm", "ִ��stop");
//		netSendService.stopService();
//	}
	public void onDestroy() {
		super.onDestroy();
		DataProvide.NET_STATE = false;
		netSendService.stopService();
		netReceiveService.stopService();
		Log.d("Farm", "�����Ѿ�������");
		System.exit(0);
	};
}
