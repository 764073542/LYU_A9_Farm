package com.ndk.Constant;

/**
 * �ڵ��������
 * 
 * @author ����С����
 * 
 */
public class SerialCommand {
	/**
	 * ���ȿ���
	 */
	public final static char[] OPEN_FAN = { 'I', 'A', 'S', 0x01, 0x01 };
	public final static char[] CLOSE_FAN = { 'I', 'A', 'S', 0x01, 0x02 };
	/**
	 * ��������
	 */
	public final static char[] OPEN_WINDOWS = { 'I', 'A', 'S', 0x02, 0x01 };
	public final static char[] CLOSE_WINDOWS = { 'I', 'A', 'S', 0x02, 0x02 };
	/**
	 * ���յƿ���
	 */
	public final static char[] OPEN_LIGHT = { 'I', 'A', 'S', 0x03, 0x01 };
	public final static char[] CLOSE_LIGHT = { 'I', 'A', 'S', 0x03, 0x02 };
	/**
	 * ˮ�ÿ���
	 */
	public final static char[] OPEN_WATE = { 'I', 'A', 'S', 0x04, 0x01 };
	public final static char[] CLOSE_WATE = { 'I', 'A', 'S', 0x04, 0x02 };
	/**
	 * ����������
	 */
	public final static char[] OPEN_HEATER = { 'I', 'A', 'S', 0x05, 0x01 };
	public final static char[] CLOSE_HEATER = { 'I', 'A', 'S', 0x05, 0x02 };
	
}
