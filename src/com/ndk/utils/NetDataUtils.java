package com.ndk.utils;

import android.util.Log;

import com.ndk.Constant.NetCommand;
import com.ndk.Constant.SerialCommand;
import com.ndk.DataListenerService.SerialSendDataService;

public class NetDataUtils {
	/**
	 * �����ֻ����͵�����
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
		 * �򿪷���
		 */
		if (netRecvData.equals(NetCommand.OPEN_FAN)) {
			SerialSendDataService.getInstance()
					.SendData(SerialCommand.OPEN_FAN);
			DataProvide.FAN_STATE = "O";
			Log.i("Farm", "�򿪷���");
		}
		/**
		 * �رշ���
		 */
		else if (netRecvData.equals(NetCommand.CLOSE_FAN)) {
			SerialSendDataService.getInstance().SendData(
					SerialCommand.CLOSE_FAN);
			DataProvide.FAN_STATE = "C";
			Log.i("Farm", "�رշ���");
		}
		/**
		 * �򿪼�����
		 */
		else if (netRecvData.equals(NetCommand.OPEN_HEAT)) {
			SerialSendDataService.getInstance().SendData(
					SerialCommand.OPEN_HEATER);
			DataProvide.HEATER_STATE = "O";
			Log.i("Farm", "�򿪼�����");
		}
		/**
		 * �رռ�����
		 */
		else if (netRecvData.equals(NetCommand.CLOSE_HEAT)) {
			SerialSendDataService.getInstance().SendData(
					SerialCommand.CLOSE_HEATER);
			DataProvide.HEATER_STATE = "C";
			Log.i("Farm", "�رռ�����");
		}
		/**
		 * �򿪹��յ�
		 */
		else if (netRecvData.equals(NetCommand.OPEN_LIGHT)) {
			SerialSendDataService.getInstance().SendData(
					SerialCommand.OPEN_LIGHT);
			DataProvide.LIGHT_STATE = "O";
			Log.i("Farm", "�򿪹��յ�");
		}
		/**
		 * �رչ��յ�
		 */
		else if (netRecvData.equals(NetCommand.CLOSE_LIGHT)) {
			SerialSendDataService.getInstance().SendData(
					SerialCommand.CLOSE_LIGHT);
			DataProvide.LIGHT_STATE = "C";
			Log.i("Farm", "�رչ��յ�");
		}
		/**
		 * �򿪴���
		 */
		else if (netRecvData.equals(NetCommand.OPEN_WINDOWS)) {
			SerialSendDataService.getInstance().SendData(
					SerialCommand.OPEN_WINDOWS);
			DataProvide.WINDOWS_STATE = "O";
			Log.i("Farm", "�򿪴���");
		}
		/**
		 * �رմ���
		 */
		else if (netRecvData.equals(NetCommand.CLOSE_WINDOWS)) {
			SerialSendDataService.getInstance().SendData(
					SerialCommand.CLOSE_WINDOWS);
			DataProvide.WINDOWS_STATE = "C";
			Log.i("Farm", "�رմ���");
		}
		/**
		 * ��ˮ��
		 */
		else if (netRecvData.equals(NetCommand.OPEN_WATE)) {
			SerialSendDataService.getInstance().SendData(
					SerialCommand.OPEN_WATE);
			DataProvide.WATER_STATE = "O";
			Log.i("Farm", "��ˮ��");
		}
		/**
		 * �ر�ˮ��
		 */
		else if (netRecvData.equals(NetCommand.CLOSE_WATE)) {
			SerialSendDataService.getInstance().SendData(
					SerialCommand.CLOSE_WATE);
			DataProvide.WATER_STATE = "C";
			Log.i("Farm", "�ر�ˮ��");
		}
		/**
		 * ��������
		 */
		else if (netRecvData.equals(NetCommand.PRO_TEST)) {
			Log.i("Farm", "���Գɹ����յ�����");
		}
		/**
		 * �ֻ����͵����ݳ��ִ��󣬿����������ڴ��������
		 */
		else {
			Log.e("Farm", "��������");
		}
	}
}
