package com.ndk.DataListenerService;

import java.io.FileInputStream;
import java.io.IOException;

import android.os.Message;
import android.util.Log;

import com.ndk.utils.DataProvide;
import com.ndk.utils.SerialDataUtils;

public class SerialReceiveService implements Runnable {
	public FileInputStream mFileInputStream;
	private Thread RecvThread;

	public SerialReceiveService(FileInputStream mFileInputStream) {
		this.mFileInputStream = mFileInputStream;
	}

	/**
	 * 线程启动函数
	 */
	public void startReceive() {
		Log.e("Farm", "服务现在开始启动");
		if (RecvThread == null) {
			RecvThread = new Thread(this);
			RecvThread.start();
		}
	}

	/**
	 * 线程停止函数
	 */
	public void stopReceive() {
		Log.e("Farm", "服务现在开始启动");
		if (RecvThread != null) {
			RecvThread.stop();
			RecvThread = null;
		}
	}

	/**
	 * 线程run()方法
	 */
	@Override
	public void run() {
		while (Thread.currentThread() == RecvThread) { // true循环可能会造成一些问题
			int size, i;
			try {
				byte[] buffer = new byte[128];
				if (mFileInputStream == null)
					return;
				size = mFileInputStream.read(buffer);
//				Log.i("Farm", "当前数据长度：" + String.valueOf(size));
				if (size > 0) {
					Message msg = new Message();
					String status = new String(buffer, 0, size);
					/* 在线程中将接收到的数据传递给CheckInfo函数进行处理 */
					DataProvide.SERIAL_RECV_MSG = status;
					SerialDataUtils.CheckInfo(status);
				}
			} catch (IOException e) {
				e.printStackTrace();
				return;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

}
