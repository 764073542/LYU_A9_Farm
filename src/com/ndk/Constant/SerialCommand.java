package com.ndk.Constant;

/**
 * 节点控制命令
 * 
 * @author 傲娇小笼包
 * 
 */
public class SerialCommand {
	/**
	 * 风扇控制
	 */
	public final static char[] OPEN_FAN = { 'I', 'A', 'S', 0x01, 0x01 };
	public final static char[] CLOSE_FAN = { 'I', 'A', 'S', 0x01, 0x02 };
	/**
	 * 窗户控制
	 */
	public final static char[] OPEN_WINDOWS = { 'I', 'A', 'S', 0x02, 0x01 };
	public final static char[] CLOSE_WINDOWS = { 'I', 'A', 'S', 0x02, 0x02 };
	/**
	 * 光照灯控制
	 */
	public final static char[] OPEN_LIGHT = { 'I', 'A', 'S', 0x03, 0x01 };
	public final static char[] CLOSE_LIGHT = { 'I', 'A', 'S', 0x03, 0x02 };
	/**
	 * 水泵控制
	 */
	public final static char[] OPEN_WATE = { 'I', 'A', 'S', 0x04, 0x01 };
	public final static char[] CLOSE_WATE = { 'I', 'A', 'S', 0x04, 0x02 };
	/**
	 * 加热器控制
	 */
	public final static char[] OPEN_HEATER = { 'I', 'A', 'S', 0x05, 0x01 };
	public final static char[] CLOSE_HEATER = { 'I', 'A', 'S', 0x05, 0x02 };
	
}
