package com.ndk.Constant;

/**
 * 该类中的数据大部分用于SharedPreference中保存数据的名称
 * 
 * @author 傲娇小笼包
 * 
 */
public class InfoConstant {
	/**
	 * IP地址指定名称
	 */
	public final static String IP_SEND = "IP_SEND";
	/**
	 * 发送端口号指定名称
	 */
	public final static String PORT_SEND = "PORT_SEND";
	/**
	 * 接收端口号指定名称
	 */
	public final static String PORT_RECEIVE = "PORT_RECEIVE";
	/**
	 * 串口号指定名称
	 */
	public final static String SERIAL_PORT = "SERIAL_PORT";
	/**
	 * 手机号指定名称
	 */
	public final static String PHONE_NUMBER = "PHONE_NUMBER";
	/**
	 * url指定名称
	 */
	public final static String URL = "URL";
	/**
	 * 波特率号指定名称
	 */
	public final static String BAUD_RATE = "BAUD_RATE";
	/**
	 * 默认IP地址
	 */
	public final static String DEFAULT_IP = "192.168.1.100";
	/**
	 * 默认发送方的（远程）端口号
	 */
	public final static int DEFAULT_SEND_PORT = 60000;
	/**
	 * 默认接收方（本机）端口号
	 */
	public final static int DEFAULT_RECV_PORT = 60000;
	/**
	 * 默认串口号
	 */
	public final static String DEFAULT_SERIAL_PORT = "/dev/ttySAC0";
	/**
	 * 默认波特率
	 */
	public final static int DEFAULT_BAUD_RATE = 9600;
	/**
	 * 默认手机号
	 */
	public final static String DEFAULT_PHONE_NUMBER = "15263906209";
	/**
	 * 默认url
	 */
	public final static String DEFAULT_URL = "http://192.168.253.19:8080/LYU/MainServet";
	/**
	 * 唯一的字符串标志着，软件的名称，此命名用于保存用户配置文件的文件名
	 */
	public final static String SOFTWARE_NAME = "LYU_SOFTSTAR";
	/**
	 * 定义错误数据，或异常等发送指定命令
	 */
	public final static String SEND_ERROR = "ERROR";
	/**
	 * 定义数据接收发送默认的编码方式
	 */
	public final static String ENDODED = "UTF-8";
	/**
	 * 数据库名称
	 */
	public static final String DATABASE_NAME = "LYU_FARM";
	/**
	 * 数据库版本
	 */
	public static final int DATABASE_VERSION = 1;
}
