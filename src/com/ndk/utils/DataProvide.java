package com.ndk.utils;

public class DataProvide {
	public static String URL = "http://192.168.253.19:8080/LYU/MainServet";
	/************************************ �ڵ�������Ϣ *****************************************/
	/**
	 * CO2Ũ��
	 */
	public static int CO2 = 400;
	/**
	 * �����¶�
	 */
	public static float AIR_TEMP = 27.5f;
	/**
	 * ����ʪ��
	 */
	public static float AIR_HUMIDITY = 18;
	/**
	 * �����¶�
	 */
	public static float SOIL_TEMP = 23;
	/**
	 * ����ʪ��
	 */
	public static float SOIL_HUMIDITY = 25;
	/**
	 * ����ǿ��
	 */
	public static int LUX = 6000;
	/**
	 * ����
	 */
	public static int WINDSPEED = 0;
	/**
	 * ��ѩ״̬
	 */
	public static int RING = 0;

	/*****************************
	 * ��Ӳ��֣� ���棬���������������߿�ȼ���崫���� ��⣬ʵ�ֱ����������Ͷ���
	 ****************************/
	public static int FLAME = 0;
	/*********************************** �ڵ�״̬��Ϣ ***************************************/
	/**
	 * Э�����ڵ�����״̬
	 */
	public static boolean COORDINATOR;
	/**
	 * CO2�ڵ㵱ǰ����״̬
	 */
	public static boolean CO2_STATE = false;
	/**
	 * ������ʪ�Ƚڵ�����״̬
	 */
	public static boolean AIR__STATE = false;
	/**
	 * ������ʪ�Ƚڵ�����״̬
	 */
	public static boolean SOIL_STATE = false;
	/**
	 * ��ѩ�ڵ�����״̬
	 */
	public static boolean RING_STATE = false;
	/**
	 * ����ǿ�Ƚڵ�����״̬
	 */
	public static boolean LUX_STATE = false;
	/**
	 * ���Ƚڵ�����״̬
	 */
	public static String FAN_STATE = "C";
	/**
	 * �����ڵ�����״̬
	 */
	public static String WINDOWS_STATE = "C";
	/**
	 * ���ٽڵ�����״̬
	 */
	public static boolean WINDSPEED_STATE = false;
	/**
	 * ˮ�ýڵ�����״̬
	 */
	public static String WATER_STATE = "C";
	/**
	 * �������ڵ�����״̬
	 */
	public static String HEATER_STATE = "C";
	/**
	 * ���յƽڵ�����״̬
	 */
	public static String LIGHT_STATE = "C";
	/**
	 * ��ǰ����״̬
	 */
	public static boolean NET_STATE = false;
	/**
	 * ��ǰ����״̬
	 */
	public static boolean SERIAL_STATE = false;
	/**
	 * ���ں�
	 */
	public static String SERIAL_PORT = "/dev/ttySAC0";
	/**
	 * ������
	 */
	public static int BAUD_RATE = 9600;

	/**
	 * ���ŷ��ͺ���
	 */
	public static String PHONE_NUMBER = "15263906209";

	/**
	 * �������ݷ���IP��ַ
	 */
	public static String NET_SEND_IP = "121.250.126.59";
	/**
	 * �������ݻ�ȡ��������̫��IP��ַ
	 */
	public static String NET_NATIVE_IP = "";
	/**
	 * �������ݻ�ȡ������WiFiIP��ַ
	 */
	public static String NET_WIFI_IP = "";
	/**
	 * �������ݷ��Ͷ˿ں�
	 */
	public static int NET_SEND_PORT = 60000;
	/**
	 * �������ݱ��������˿ں�
	 */
	public static int NET_RECV_PORT = 60000;
	/**
	 * �洢���ڴ��͵�����
	 */
	public static String SERIAL_RECV_MSG = "";
	/**
	 * �洢��Ҫ���͸����ڵ�����
	 */
	public static String SERIAL_SEND_MSG = "";
	/**
	 * �洢�ֻ����͵�����
	 */
	public static String NET_RECV_MSG = "";
	/**
	 * �洢���͸��ֻ�������
	 */
	public static String NET_SEND_MSG = "";
	/**
	 * ���ں����繲������
	 */
	public static String PUBLIC_DATA_INFO = "SMSTestData";
	/**
	 * Spinner Baud Rate��ѡ����
	 */
	public static int SPINNER_BAUDRATE_POSITION = 2;
	/**
	 * Spinner Serial Port ��ѡ����
	 */
	public static int SPINNER_SERIAL_POSITION = 0;

	/**
	 * ������ʾ����
	 */
	public static String ShowData() {
		return "����ǿ�ȣ�" + LUX + " CO2Ũ��" + CO2 + " ������ʪ�ȣ�" + AIR_TEMP + "/"
				+ AIR_HUMIDITY + " ������ʪ��" + SOIL_TEMP + "/" + SOIL_HUMIDITY
				+ " ���٣�" + WINDSPEED + " ��ѩ״̬" + RING;
	}

	/**
	 * ��ʾ�����ڵ��״̬����
	 */
	public static String ShowState() {
		return "����ǿ��" + LUX_STATE + " CO2Ũ��" + CO2_STATE + " ������ʪ��"
				+ AIR__STATE + " ������ʪ��" + SOIL_STATE + " ���ٽڵ�"
				+ WINDSPEED_STATE + " ��ѩ�ڵ�" + RING_STATE + " ���յ�" + LIGHT_STATE
				+ " ˮ��" + WATER_STATE + " ����" + WINDOWS_STATE + " ������"
				+ HEATER_STATE + " ����" + FAN_STATE;
	}
}
