package com.ndk.utils;

public class DataProvide {
	public static String URL = "http://192.168.253.19:8080/LYU/MainServet";
	/************************************ 节点数据信息 *****************************************/
	/**
	 * CO2浓度
	 */
	public static int CO2 = 400;
	/**
	 * 空气温度
	 */
	public static float AIR_TEMP = 27.5f;
	/**
	 * 空气湿度
	 */
	public static float AIR_HUMIDITY = 18;
	/**
	 * 土壤温度
	 */
	public static float SOIL_TEMP = 23;
	/**
	 * 土壤湿度
	 */
	public static float SOIL_HUMIDITY = 25;
	/**
	 * 光照强度
	 */
	public static int LUX = 6000;
	/**
	 * 风速
	 */
	public static int WINDSPEED = 0;
	/**
	 * 雨雪状态
	 */
	public static int RING = 0;

	/*****************************
	 * 添加部分： 火焰，或者烟雾传感器或者可燃气体传感器 检测，实现报警，并发送短信
	 ****************************/
	public static int FLAME = 0;
	/*********************************** 节点状态信息 ***************************************/
	/**
	 * 协调器节点上线状态
	 */
	public static boolean COORDINATOR;
	/**
	 * CO2节点当前上线状态
	 */
	public static boolean CO2_STATE = false;
	/**
	 * 空气温湿度节点上线状态
	 */
	public static boolean AIR__STATE = false;
	/**
	 * 土壤温湿度节点上线状态
	 */
	public static boolean SOIL_STATE = false;
	/**
	 * 雨雪节点上线状态
	 */
	public static boolean RING_STATE = false;
	/**
	 * 光照强度节点上线状态
	 */
	public static boolean LUX_STATE = false;
	/**
	 * 风扇节点上线状态
	 */
	public static String FAN_STATE = "C";
	/**
	 * 窗户节点上线状态
	 */
	public static String WINDOWS_STATE = "C";
	/**
	 * 风速节点上线状态
	 */
	public static boolean WINDSPEED_STATE = false;
	/**
	 * 水泵节点上线状态
	 */
	public static String WATER_STATE = "C";
	/**
	 * 加热器节点上线状态
	 */
	public static String HEATER_STATE = "C";
	/**
	 * 光照灯节点上线状态
	 */
	public static String LIGHT_STATE = "C";
	/**
	 * 当前网络状态
	 */
	public static boolean NET_STATE = false;
	/**
	 * 当前串口状态
	 */
	public static boolean SERIAL_STATE = false;
	/**
	 * 串口号
	 */
	public static String SERIAL_PORT = "/dev/ttySAC0";
	/**
	 * 波特率
	 */
	public static int BAUD_RATE = 9600;

	/**
	 * 短信发送号码
	 */
	public static String PHONE_NUMBER = "15263906209";

	/**
	 * 网络数据发送IP地址
	 */
	public static String NET_SEND_IP = "121.250.126.59";
	/**
	 * 网络数据获取本机的以太网IP地址
	 */
	public static String NET_NATIVE_IP = "";
	/**
	 * 网络数据获取本机的WiFiIP地址
	 */
	public static String NET_WIFI_IP = "";
	/**
	 * 网络数据发送端口号
	 */
	public static int NET_SEND_PORT = 60000;
	/**
	 * 网络数据本机监听端口号
	 */
	public static int NET_RECV_PORT = 60000;
	/**
	 * 存储串口传送的数据
	 */
	public static String SERIAL_RECV_MSG = "";
	/**
	 * 存储需要发送给串口的数据
	 */
	public static String SERIAL_SEND_MSG = "";
	/**
	 * 存储手机传送的数据
	 */
	public static String NET_RECV_MSG = "";
	/**
	 * 存储传送给手机的数据
	 */
	public static String NET_SEND_MSG = "";
	/**
	 * 串口和网络共享数据
	 */
	public static String PUBLIC_DATA_INFO = "SMSTestData";
	/**
	 * Spinner Baud Rate被选择项
	 */
	public static int SPINNER_BAUDRATE_POSITION = 2;
	/**
	 * Spinner Serial Port 被选择项
	 */
	public static int SPINNER_SERIAL_POSITION = 0;

	/**
	 * 数据显示函数
	 */
	public static String ShowData() {
		return "光照强度：" + LUX + " CO2浓度" + CO2 + " 空气温湿度：" + AIR_TEMP + "/"
				+ AIR_HUMIDITY + " 土壤温湿度" + SOIL_TEMP + "/" + SOIL_HUMIDITY
				+ " 风速：" + WINDSPEED + " 雨雪状态" + RING;
	}

	/**
	 * 显示各个节点的状态数据
	 */
	public static String ShowState() {
		return "光照强度" + LUX_STATE + " CO2浓度" + CO2_STATE + " 空气温湿度"
				+ AIR__STATE + " 土壤温湿度" + SOIL_STATE + " 风速节点"
				+ WINDSPEED_STATE + " 雨雪节点" + RING_STATE + " 光照灯" + LIGHT_STATE
				+ " 水泵" + WATER_STATE + " 窗户" + WINDOWS_STATE + " 加热器"
				+ HEATER_STATE + " 风扇" + FAN_STATE;
	}
}
