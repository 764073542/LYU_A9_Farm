package com.ndk.utils;

import java.util.ArrayList;

import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.ndk.Constant.SMSCommand;
import com.ndk.Constant.SerialCommand;
import com.ndk.DataListenerService.SerialSendDataService;

public class SMSUtils {
	public static void CheckSMS(String SMS) {
		/**
		 * 查看数据 以短信的形式发送过去
		 */
		if (SMS.contains(SMSCommand.DATAVIEW)) {
			SendSMS();
		}
		/**
		 * 打开 关闭风扇
		 */
		if (SMS.contains(SMSCommand.OPEN_FAN)) {
			SerialSendDataService.getInstance()
					.SendData(SerialCommand.OPEN_FAN);
			DataProvide.FAN_STATE = "O";
			Log.i("Farm", "打开风扇");
		}
		if (SMS.contains(SMSCommand.CLOSE_FAN)) {
			SerialSendDataService.getInstance().SendData(
					SerialCommand.CLOSE_FAN);
			DataProvide.FAN_STATE = "C";
			Log.i("Farm", "关闭风扇");
		}
		/**
		 * 打开关闭光照灯
		 */
		if (SMS.contains(SMSCommand.OPEN_LIGHT)) {
			SerialSendDataService.getInstance().SendData(
					SerialCommand.OPEN_LIGHT);
			DataProvide.LIGHT_STATE = "O";
			Log.i("Farm", "打开光照灯");
		}
		if (SMS.contains(SMSCommand.CLOSE_LIGHT)) {
			SerialSendDataService.getInstance().SendData(
					SerialCommand.CLOSE_LIGHT);
			DataProvide.LIGHT_STATE = "C";
			Log.i("Farm", "关闭光照灯");
		}
		/**
		 * 打开关闭水泵
		 */
		if (SMS.contains(SMSCommand.OPEN_WATE)) {
			SerialSendDataService.getInstance().SendData(
					SerialCommand.OPEN_WATE);
			DataProvide.WATER_STATE = "O";
			Log.i("Farm", "打开水泵");
		}
		if (SMS.contains(SMSCommand.CLOSE_WATE)) {
			SerialSendDataService.getInstance().SendData(
					SerialCommand.CLOSE_WATE);
			DataProvide.WATER_STATE = "C";
			Log.i("Farm", "关闭水泵");
		}
		/**
		 * 打开 关闭窗户
		 */
		if (SMS.contains(SMSCommand.OPEN_WINDOWS)) {
			SerialSendDataService.getInstance().SendData(
					SerialCommand.OPEN_WINDOWS);
			DataProvide.WINDOWS_STATE = "O";
			Log.i("Farm", "打开窗户");
		}
		if (SMS.contains(SMSCommand.CLOSE_WINDOWS)) {
			SerialSendDataService.getInstance().SendData(
					SerialCommand.CLOSE_WINDOWS);
			DataProvide.WINDOWS_STATE = "C";
			Log.i("Farm", "关闭窗户");
		}
		/**
		 * 打开 关闭加热器
		 */
		if (SMS.contains(SMSCommand.OPEN_HEATER)) {
			SerialSendDataService.getInstance().SendData(
					SerialCommand.OPEN_HEATER);
			DataProvide.HEATER_STATE = "O";
			Log.i("Farm", "打开加热器");
		}
		if (SMS.contains(SMSCommand.CLOSE_HEATER)) {
			SerialSendDataService.getInstance().SendData(
					SerialCommand.CLOSE_HEATER);
			DataProvide.HEATER_STATE = "C";
			Log.i("Farm", "关闭加热器");
		}

	}

	private static void SendSMS() {
		String number = DataProvide.PHONE_NUMBER;
		String content = DataProvide.PUBLIC_DATA_INFO;
		if (TextUtils.isEmpty(number) || TextUtils.isEmpty(content)) {
			Log.e("Farm", "信息不可为空");
		} else {
			SmsManager smsManager = SmsManager.getDefault();
			ArrayList<String> contents = smsManager.divideMessage(content);
			for (String str : contents) {
				smsManager.sendTextMessage(number, null, str, null, null);
			}

		}

	}
}
