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
	/* 添加函数声明,告诉编译和链接器该方法在本地代码中实现 */
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
		 * 初始化打开串口资源
		 */
		mFd = open(new String(DataProvide.SERIAL_PORT), DataProvide.BAUD_RATE); // 设备路径和波特率
		if (mFd == null) {
			Log.e("Farm", "串口打开失败");
			try {
				throw new IOException();
			} catch (IOException e) {
				Log.e("Farm", "串口打开失败");
			} catch (Exception ex) {
				Log.e("Farm", "存在其他错误");

			}
		}
		mFileInputStream = new FileInputStream(mFd);
		mFileOutputStream = new FileOutputStream(mFd);

		/* 初始化创建接收发送线程对象，并启动线程 */
		serialReceive = new SerialReceiveService(mFileInputStream);
		SerialSendDataService.init(mFileOutputStream);
		serialReceive.startReceive();
	}

	/**
	 * 服务被创建，调用init()完成初始化
	 */
	@Override
	public void onCreate() {
		super.onCreate();
		init();// 初始化
		Log.d("Farm", "服务已经被创建");
	}

	/**
	 * 服务被销毁之后线程退出
	 */
	@Override
	public void onDestroy() {
		super.onDestroy();
		serialReceive.stopReceive();
		Log.d("Farm", "服务已经被销毁");
		System.exit(0);
	};

}
