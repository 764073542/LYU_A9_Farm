package com.ndk.utils;

import android.util.Log;

import com.ndk.Constant.NetCommand;
import com.ndk.Constant.SerialCommand;
import com.ndk.DataListenerService.SerialSendDataService;

public class NetDataUtils {
	/**
	 * 解析手机发送的数据
	 * 
	 * @param netRecvData2
	 */
	public static void AnalyzeData(String netRecvData) {
		netRecvData = netRecvData.toUpperCase();
		if (netRecvData.equals("")) {
			return;
		}
		if (netRecvData.equals("HELLO")) {
			if(DataProvide.NET_STATE != true){				
				DataQueue.getInstance().addElement(NetCommand.NET_STATE_TEST);
			}
			DataProvide.NET_STATE = true;
			return;
		}
		/**
		 * 打开风扇
		 */
		if (netRecvData.equals(NetCommand.OPEN_FAN)) {
			SerialSendDataService.getInstance()
					.SendData(SerialCommand.OPEN_FAN);
			DataProvide.FAN_STATE = "O";
			Log.i("Farm", "打开风扇");
		}
		/**
		 * 关闭风扇
		 */
		else if (netRecvData.equals(NetCommand.CLOSE_FAN)) {
			SerialSendDataService.getInstance().SendData(
					SerialCommand.CLOSE_FAN);
			DataProvide.FAN_STATE = "C";
			Log.i("Farm", "关闭风扇");
		}
		/**
		 * 打开加热器
		 */
		else if (netRecvData.equals(NetCommand.OPEN_HEAT)) {
			SerialSendDataService.getInstance().SendData(
					SerialCommand.OPEN_HEATER);
			DataProvide.HEATER_STATE = "O";
			Log.i("Farm", "打开加热器");
		}
		/**
		 * 关闭加热器
		 */
		else if (netRecvData.equals(NetCommand.CLOSE_HEAT)) {
			SerialSendDataService.getInstance().SendData(
					SerialCommand.CLOSE_HEATER);
			DataProvide.HEATER_STATE = "C";
			Log.i("Farm", "关闭加热器");
		}
		/**
		 * 打开光照灯
		 */
		else if (netRecvData.equals(NetCommand.OPEN_LIGHT)) {
			SerialSendDataService.getInstance().SendData(
					SerialCommand.OPEN_LIGHT);
			DataProvide.LIGHT_STATE = "O";
			Log.i("Farm", "打开光照灯");
		}
		/**
		 * 关闭光照灯
		 */
		else if (netRecvData.equals(NetCommand.CLOSE_LIGHT)) {
			SerialSendDataService.getInstance().SendData(
					SerialCommand.CLOSE_LIGHT);
			DataProvide.LIGHT_STATE = "C";
			Log.i("Farm", "关闭光照灯");
		}
		/**
		 * 打开窗户
		 */
		else if (netRecvData.equals(NetCommand.OPEN_WINDOWS)) {
			SerialSendDataService.getInstance().SendData(
					SerialCommand.OPEN_WINDOWS);
			DataProvide.WINDOWS_STATE = "O";
			Log.i("Farm", "打开窗户");
		}
		/**
		 * 关闭窗户
		 */
		else if (netRecvData.equals(NetCommand.CLOSE_WINDOWS)) {
			SerialSendDataService.getInstance().SendData(
					SerialCommand.CLOSE_WINDOWS);
			DataProvide.WINDOWS_STATE = "C";
			Log.i("Farm", "关闭窗户");
		}
		/**
		 * 打开水泵
		 */
		else if (netRecvData.equals(NetCommand.OPEN_WATE)) {
			SerialSendDataService.getInstance().SendData(
					SerialCommand.OPEN_WATE);
			DataProvide.WATER_STATE = "O";
			Log.i("Farm", "打开水泵");
		}
		/**
		 * 关闭水泵
		 */
		else if (netRecvData.equals(NetCommand.CLOSE_WATE)) {
			SerialSendDataService.getInstance().SendData(
					SerialCommand.CLOSE_WATE);
			DataProvide.WATER_STATE = "C";
			Log.i("Farm", "关闭水泵");
		}
		/**
		 * 测试用例
		 */
		else if (netRecvData.equals(NetCommand.PRO_TEST)) {
			Log.i("Farm", "测试成功，收到数据");
		}
		/**
		 * 手机发送的数据出现错误，可能是数据在传输中损毁
		 */
		else {
			Log.e("Farm", "数据已损坏");
		}
	}
}
