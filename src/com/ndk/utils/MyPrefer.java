package com.ndk.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.ndk.Constant.InfoConstant;

/*
 * ͨ���������洢�ͷ����û�����������ļ��������ڳ���������ʱ���Ҫ���������
 */
public class MyPrefer {
	private Context context;

	public MyPrefer(Context context) {
		this.context = context;
	}

	/**
	 * ��ȡ���͵�IP��ַ
	 * 
	 * @return
	 */
	public String getIp() {
		SharedPreferences sPref = context.getSharedPreferences(
				InfoConstant.SOFTWARE_NAME, Context.MODE_PRIVATE);// MODE_PRIVATE��ʾ�洢��sharedpreference�ļ�ֻ�ܱ���Ӧ�ó������
		return sPref.getString(InfoConstant.IP_SEND, InfoConstant.DEFAULT_IP);
	}

	/**
	 * ���÷��͵�IP��ַ
	 * 
	 * @param Proxy_IP
	 */
	public void setIP(String Proxy_IP) {

		SharedPreferences mySharedPreferences = context.getSharedPreferences(
				InfoConstant.SOFTWARE_NAME, context.MODE_PRIVATE);

		Editor ed = mySharedPreferences.edit();
		ed.putString(InfoConstant.IP_SEND, Proxy_IP);
		ed.commit();
	}

	/**
	 * ��ȡ���Ͷ˿ں�
	 * 
	 * @return
	 */

	public int getSendPort() {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				InfoConstant.SOFTWARE_NAME, context.MODE_PRIVATE);
		String Port = sharedPreferences.getString(InfoConstant.PORT_SEND,
				InfoConstant.DEFAULT_SEND_PORT + "");
		return Integer.parseInt(Port);

	}

	/**
	 * ���÷��Ͷ˿ں�
	 * 
	 * @param Proxy_Port
	 */
	public void setSendPort(String Proxy_Port) {

		SharedPreferences mySharedPreferences = context.getSharedPreferences(
				InfoConstant.SOFTWARE_NAME, context.MODE_PRIVATE);

		Editor ed = mySharedPreferences.edit();
		ed.putString(InfoConstant.PORT_SEND, Proxy_Port);
		ed.commit();
	}

	/**
	 * ��ȡ�������ڽ��յĶ˿ں�
	 * 
	 * @return
	 */
	public int getRecvPort() {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				InfoConstant.SOFTWARE_NAME, context.MODE_PRIVATE);
		String Port = sharedPreferences.getString(InfoConstant.PORT_RECEIVE,
				InfoConstant.DEFAULT_RECV_PORT + "");
		return Integer.parseInt(Port);

	}

	/**
	 * ���ñ������ڽ��յĶ˿ں�
	 * 
	 * @param Proxy_Port
	 */
	public void setRecvPort(String Proxy_Port) {

		SharedPreferences mySharedPreferences = context.getSharedPreferences(
				InfoConstant.SOFTWARE_NAME, context.MODE_PRIVATE);

		Editor ed = mySharedPreferences.edit();
		ed.putString(InfoConstant.PORT_RECEIVE, Proxy_Port);
		ed.commit();
	}

	/**
	 * ��ȡ������Ϣ
	 */
	public String getSerial() {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				InfoConstant.SOFTWARE_NAME, context.MODE_PRIVATE);
		String Serial = sharedPreferences.getString(InfoConstant.SERIAL_PORT,
				InfoConstant.DEFAULT_SERIAL_PORT);
		return Serial;
	}

	/**
	 * ���ô�����Ϣ
	 */
	public void setSerial(String serial) {
		SharedPreferences mySharedPreferences = context.getSharedPreferences(
				InfoConstant.SOFTWARE_NAME, context.MODE_PRIVATE);

		Editor ed = mySharedPreferences.edit();
		ed.putString(InfoConstant.SERIAL_PORT, serial);
		ed.commit();
	}

	/**
	 * ��ȡ��������Ϣ
	 */
	public int getBaudRate() {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				InfoConstant.SOFTWARE_NAME, context.MODE_PRIVATE);
		String rate = sharedPreferences.getString(InfoConstant.BAUD_RATE,
				InfoConstant.DEFAULT_BAUD_RATE + "");

		return Integer.parseInt(rate);
	}

	/**
	 * ���ò�������Ϣ
	 */
	public void setBaudRate(String baudRate) {
		SharedPreferences mySharedPreferences = context.getSharedPreferences(
				InfoConstant.SOFTWARE_NAME, context.MODE_PRIVATE);

		Editor ed = mySharedPreferences.edit();
		ed.putString(InfoConstant.BAUD_RATE, baudRate);
		ed.commit();
	}

	/**
	 * ��ȡ�ֻ���
	 */
	public String getPhoneNumber() {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				InfoConstant.SOFTWARE_NAME, context.MODE_PRIVATE);
		String Phone = sharedPreferences.getString(InfoConstant.PHONE_NUMBER,
				InfoConstant.DEFAULT_PHONE_NUMBER);

		return Phone;
	}

	/**
	 * �����ֻ���
	 */
	public void setPhoneNumber(String Phone) {
		SharedPreferences mySharedPreferences = context.getSharedPreferences(
				InfoConstant.SOFTWARE_NAME, context.MODE_PRIVATE);
		Editor ed = mySharedPreferences.edit();
		ed.putString(InfoConstant.PHONE_NUMBER, Phone);
		ed.commit();
	}

	/**
	 * ����URL
	 */
	public void setUrl(String url) {
		SharedPreferences mySharedPreferences = context.getSharedPreferences(
				InfoConstant.SOFTWARE_NAME, context.MODE_PRIVATE);
		Editor ed = mySharedPreferences.edit();
		ed.putString(InfoConstant.URL, url);
		ed.commit();
	}

	/**
	 * ��ȡurl
	 */
	public String getUrl() {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				InfoConstant.SOFTWARE_NAME, context.MODE_PRIVATE);
		String Phone = sharedPreferences.getString(InfoConstant.URL,
				InfoConstant.DEFAULT_URL);

		return Phone;
	}
}
