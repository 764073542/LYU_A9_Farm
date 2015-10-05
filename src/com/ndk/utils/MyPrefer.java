package com.ndk.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.ndk.Constant.InfoConstant;

/*
 * 通过此类来存储和访问用户保存的配置文件，所以在程序启动的时候就要加载这个类
 */
public class MyPrefer {
	private Context context;

	public MyPrefer(Context context) {
		this.context = context;
	}

	/**
	 * 获取发送的IP地址
	 * 
	 * @return
	 */
	public String getIp() {
		SharedPreferences sPref = context.getSharedPreferences(
				InfoConstant.SOFTWARE_NAME, Context.MODE_PRIVATE);// MODE_PRIVATE表示存储的sharedpreference文件只能被本应用程序访问
		return sPref.getString(InfoConstant.IP_SEND, InfoConstant.DEFAULT_IP);
	}

	/**
	 * 设置发送的IP地址
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
	 * 获取发送端口号
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
	 * 设置发送端口号
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
	 * 获取本机用于接收的端口号
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
	 * 设置本机用于接收的端口号
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
	 * 获取串口信息
	 */
	public String getSerial() {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				InfoConstant.SOFTWARE_NAME, context.MODE_PRIVATE);
		String Serial = sharedPreferences.getString(InfoConstant.SERIAL_PORT,
				InfoConstant.DEFAULT_SERIAL_PORT);
		return Serial;
	}

	/**
	 * 设置串口信息
	 */
	public void setSerial(String serial) {
		SharedPreferences mySharedPreferences = context.getSharedPreferences(
				InfoConstant.SOFTWARE_NAME, context.MODE_PRIVATE);

		Editor ed = mySharedPreferences.edit();
		ed.putString(InfoConstant.SERIAL_PORT, serial);
		ed.commit();
	}

	/**
	 * 获取波特率信息
	 */
	public int getBaudRate() {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				InfoConstant.SOFTWARE_NAME, context.MODE_PRIVATE);
		String rate = sharedPreferences.getString(InfoConstant.BAUD_RATE,
				InfoConstant.DEFAULT_BAUD_RATE + "");

		return Integer.parseInt(rate);
	}

	/**
	 * 设置波特率信息
	 */
	public void setBaudRate(String baudRate) {
		SharedPreferences mySharedPreferences = context.getSharedPreferences(
				InfoConstant.SOFTWARE_NAME, context.MODE_PRIVATE);

		Editor ed = mySharedPreferences.edit();
		ed.putString(InfoConstant.BAUD_RATE, baudRate);
		ed.commit();
	}

	/**
	 * 获取手机号
	 */
	public String getPhoneNumber() {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				InfoConstant.SOFTWARE_NAME, context.MODE_PRIVATE);
		String Phone = sharedPreferences.getString(InfoConstant.PHONE_NUMBER,
				InfoConstant.DEFAULT_PHONE_NUMBER);

		return Phone;
	}

	/**
	 * 设置手机号
	 */
	public void setPhoneNumber(String Phone) {
		SharedPreferences mySharedPreferences = context.getSharedPreferences(
				InfoConstant.SOFTWARE_NAME, context.MODE_PRIVATE);
		Editor ed = mySharedPreferences.edit();
		ed.putString(InfoConstant.PHONE_NUMBER, Phone);
		ed.commit();
	}

	/**
	 * 设置URL
	 */
	public void setUrl(String url) {
		SharedPreferences mySharedPreferences = context.getSharedPreferences(
				InfoConstant.SOFTWARE_NAME, context.MODE_PRIVATE);
		Editor ed = mySharedPreferences.edit();
		ed.putString(InfoConstant.URL, url);
		ed.commit();
	}

	/**
	 * 获取url
	 */
	public String getUrl() {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				InfoConstant.SOFTWARE_NAME, context.MODE_PRIVATE);
		String Phone = sharedPreferences.getString(InfoConstant.URL,
				InfoConstant.DEFAULT_URL);

		return Phone;
	}
}
