package com.ndk.DataListenerService;

import java.io.FileOutputStream;
import java.io.IOException;

public class SerialSendDataService {
	private static FileOutputStream mFileOutputStream;
	private final static SerialSendDataService SerialSend = new SerialSendDataService();

	/**
	 * �����췽��˽�л�
	 */
	private SerialSendDataService() {
	}

	/**
	 * �����ṩ�����ȡ����getInstance()
	 * 
	 * @return
	 */
	public static SerialSendDataService getInstance() {
		return SerialSend;
	}

	/**
	 * FileOutputStream���󴫲η���
	 * 
	 * @param mFileOutputStream
	 * @return
	 */
	public static void init(FileOutputStream mFileOutputStream2) {
		mFileOutputStream = mFileOutputStream2;
	}

	/**
	 * ���ݷ��ͷ���
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
