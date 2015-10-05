package com.ndk.DataListenerService;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.ndk.utils.DataProvide;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class SerialService extends Service {
	/* ��Ӻ�������,���߱�����������÷����ڱ��ش�����ʵ�� */
	private native static FileDescriptor open(String path, int baudrate);

	public native void close();

	private FileDescriptor mFd;
	private FileInputStream mFileInputStream;
	private FileOutputStream mFileOutputStream;

	private SerialReceiveService serialReceive;

	@Override
	public IBinder onBind(Intent intent) {

		return null;
	}

	public void init() {
		/**
		 * ��ʼ���򿪴�����Դ
		 */
		mFd = open(new String(DataProvide.SERIAL_PORT), DataProvide.BAUD_RATE); // �豸·���Ͳ�����
		if (mFd == null) {
			Log.e("Farm", "���ڴ�ʧ��");
			try {
				throw new IOException();
			} catch (IOException e) {
				Log.e("Farm", "���ڴ�ʧ��");
			} catch (Exception ex) {
				Log.e("Farm", "������������");

			}
		}
		mFileInputStream = new FileInputStream(mFd);
		mFileOutputStream = new FileOutputStream(mFd);

		/* ��ʼ���������շ����̶߳��󣬲������߳� */
		serialReceive = new SerialReceiveService(mFileInputStream);
		SerialSendDataService.init(mFileOutputStream);
		serialReceive.startReceive();
	}

	/**
	 * ���񱻴���������init()��ɳ�ʼ��
	 */
	@Override
	public void onCreate() {
		super.onCreate();
		init();// ��ʼ��
		Log.d("Farm", "�����Ѿ�������");
	}

	/**
	 * ��������֮���߳��˳�
	 */
	@Override
	public void onDestroy() {
		super.onDestroy();
		serialReceive.stopReceive();
		Log.d("Farm", "�����Ѿ�������");
		System.exit(0);
	};

}
