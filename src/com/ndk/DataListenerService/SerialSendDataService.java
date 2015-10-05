package com.ndk.DataListenerService;

import java.io.FileOutputStream;
import java.io.IOException;

public class SerialSendDataService {
	private static FileOutputStream mFileOutputStream;
	private final static SerialSendDataService SerialSend = new SerialSendDataService();

	/**
	 * 将构造方法私有化
	 */
	private SerialSendDataService() {
	}

	/**
	 * 对外提供对象获取方法getInstance()
	 * 
	 * @return
	 */
	public static SerialSendDataService getInstance() {
		return SerialSend;
	}

	/**
	 * FileOutputStream对象传参方法
	 * 
	 * @param mFileOutputStream
	 * @return
	 */
	public static void init(FileOutputStream mFileOutputStream2) {
		mFileOutputStream = mFileOutputStream2;
	}

	/**
	 * 数据发送方法
	 * 
	 * @param buf
	 */
	public void SendData(char[] buf) {

		try {
			mFileOutputStream.write(new String(buf).getBytes());
			mFileOutputStream.write('\n');
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
