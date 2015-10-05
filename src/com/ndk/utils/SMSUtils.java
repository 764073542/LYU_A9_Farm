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
		 * �鿴���� �Զ��ŵ���ʽ���͹�ȥ
		 */
		if (SMS.contains(SMSCommand.DATAVIEW)) {
			SendSMS();
		}
		/**
		 * �� �رշ���
		 */
		if (SMS.contains(SMSCommand.OPEN_FAN)) {
			SerialSendDataService.getInstance()
					.SendData(SerialCommand.OPEN_FAN);
			DataProvide.FAN_STATE = "O";
			Log.i("Farm", "�򿪷���");
		}
		if (SMS.contains(SMSCommand.CLOSE_FAN)) {
			SerialSendDataService.getInstance().SendData(
					SerialCommand.CLOSE_FAN);
			DataProvide.FAN_STATE = "C";
			Log.i("Farm", "�رշ���");
		}
		/**
		 * �򿪹رչ��յ�
		 */
		if (SMS.contains(SMSCommand.OPEN_LIGHT)) {
			SerialSendDataService.getInstance().SendData(
					SerialCommand.OPEN_LIGHT);
			DataProvide.LIGHT_STATE = "O";
			Log.i("Farm", "�򿪹��յ�");
		}
		if (SMS.contains(SMSCommand.CLOSE_LIGHT)) {
			SerialSendDataService.getInstance().SendData(
					SerialCommand.CLOSE_LIGHT);
			DataProvide.LIGHT_STATE = "C";
			Log.i("Farm", "�رչ��յ�");
		}
		/**
		 * �򿪹ر�ˮ��
		 */
		if (SMS.contains(SMSCommand.OPEN_WATE)) {
			SerialSendDataService.getInstance().SendData(
					SerialCommand.OPEN_WATE);
			DataProvide.WATER_STATE = "O";
			Log.i("Farm", "��ˮ��");
		}
		if (SMS.contains(SMSCommand.CLOSE_WATE)) {
			SerialSendDataService.getInstance().SendData(
					SerialCommand.CLOSE_WATE);
			DataProvide.WATER_STATE = "C";
			Log.i("Farm", "�ر�ˮ��");
		}
		/**
		 * �� �رմ���
		 */
		if (SMS.contains(SMSCommand.OPEN_WINDOWS)) {
			SerialSendDataService.getInstance().SendData(
					SerialCommand.OPEN_WINDOWS);
			DataProvide.WINDOWS_STATE = "O";
			Log.i("Farm", "�򿪴���");
		}
		if (SMS.contains(SMSCommand.CLOSE_WINDOWS)) {
			SerialSendDataService.getInstance().SendData(
					SerialCommand.CLOSE_WINDOWS);
			DataProvide.WINDOWS_STATE = "C";
			Log.i("Farm", "�رմ���");
		}
		/**
		 * �� �رռ�����
		 */
		if (SMS.contains(SMSCommand.OPEN_HEATER)) {
			SerialSendDataService.getInstance().SendData(
					SerialCommand.OPEN_HEATER);
			DataProvide.HEATER_STATE = "O";
			Log.i("Farm", "�򿪼�����");
		}
		if (SMS.contains(SMSCommand.CLOSE_HEATER)) {
			SerialSendDataService.getInstance().SendData(
					SerialCommand.CLOSE_HEATER);
			DataProvide.HEATER_STATE = "C";
			Log.i("Farm", "�رռ�����");
		}

	}

	private static void SendSMS() {
		String number = DataProvide.PHONE_NUMBER;
		String content = DataProvide.PUBLIC_DATA_INFO;
		if (TextUtils.isEmpty(number) || TextUtils.isEmpty(content)) {
			Log.e("Farm", "��Ϣ����Ϊ��");
		} else {
			SmsManager smsManager = SmsManager.getDefault();
			ArrayList<String> contents = smsManager.divideMessage(content);
			for (String str : contents) {
				smsManager.sendTextMessage(number, null, str, null, null);
			}

		}

	}
}
