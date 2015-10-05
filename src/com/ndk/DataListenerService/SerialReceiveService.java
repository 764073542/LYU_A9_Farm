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
	 * �߳���������
	 */
	public void startReceive() {
		Log.e("Farm", "�������ڿ�ʼ����");
		if (RecvThread == null) {
			RecvThread = new Thread(this);
			RecvThread.start();
		}
	}

	/**
	 * �߳�ֹͣ����
	 */
	public void stopReceive() {
		Log.e("Farm", "�������ڿ�ʼ����");
		if (RecvThread != null) {
			RecvThread.stop();
			RecvThread = null;
		}
	}

	/**
	 * �߳�run()����
	 */
	@Override
	public void run() {
		while (Thread.currentThread() == RecvThread) { // trueѭ�����ܻ����һЩ����
			int size, i;
			try {
				byte[] buffer = new byte[128];
				if (mFileInputStream == null)
					return;
				size = mFileInputStream.read(buffer);
//				Log.i("Farm", "��ǰ���ݳ��ȣ�" + String.valueOf(size));
				if (size > 0) {
					Message msg = new Message();
					String status = new String(buffer, 0, size);
					/* ���߳��н����յ������ݴ��ݸ�CheckInfo�������д��� */
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
