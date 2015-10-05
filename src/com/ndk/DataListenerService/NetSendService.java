package com.ndk.DataListenerService;

/****************************************
 * ֻ�з��Ͷ˵�IP��ַ�ͽ��ܵ������ݰ��ķ��͵�ַ��ͬ�������������ݰ��Ų��ᱻ����
 ***************************************/
/**
 * Linux����ƽ̨�ϵ����ݷ���Ӧ�÷����߳����棬Ҫÿ��һ��ʱ�����ⷢ��һ������
 */
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import android.util.Log;

import com.ndk.Constant.InfoConstant;
import com.ndk.utils.DataProvide;
import com.ndk.utils.DataQueue;

public class NetSendService implements Runnable {
	private Thread netSendThread = null;
	private DatagramSocket udpSendSocket;// ��������Socket
	private DatagramPacket sendPacket = null;
	private String netSendIP = DataProvide.NET_SEND_IP;
	private int netSendPort = DataProvide.NET_SEND_PORT;
	private byte[] sBuffer = null;
	private DataQueue dataQueue = DataQueue.getInstance();

	public NetSendService(DatagramSocket netUdpSocket) {
		udpSendSocket = netUdpSocket;
	}

	/**
	 * ��һЩ���ݽ��г�ʼ�� ��ʱ���ᱻ���ã���Ϊ��������ʱ������Setting�����޸�IP�˿ں���θ��������⡣
	 * 
	 * @throws SocketException
	 */
	public void init() throws SocketException {

		if (!(netSendIP.equals(DataProvide.NET_SEND_IP))
				|| (netSendPort != DataProvide.NET_SEND_PORT)) {
			netSendIP = DataProvide.NET_SEND_IP;
			netSendPort = DataProvide.NET_SEND_PORT;
		}
	}

	/**
	 * ������ͨ�ı���ʽ������
	 * 
	 * @param SData
	 */
	public void SendData(String SData) {
		try {
			init();
			sBuffer = SData.getBytes(InfoConstant.ENDODED);

			sendPacket = new DatagramPacket(sBuffer, sBuffer.length,
					InetAddress.getByName(netSendIP), netSendPort);

			udpSendSocket.send(sendPacket);
			Log.d("Farm", "�ɹ���������Ϊ��" + SData);
			sendPacket = null;
			sBuffer = null;
		} catch (IOException ie) {
			udpSendSocket.close();
			udpSendSocket = null;
			sendPacket = null;
			Log.d("Farm", "���ݷ����쳣");
		} catch (Exception e) {
			// stopService();
			Log.e("Farm", "���ݷ��ʹ��ڴ���");
		}
	}

	/**
	 * �߳�run()����
	 */
	@Override
	public void run() {

		while (Thread.currentThread() == netSendThread) {
			try {
				DataPacketUtils.DataPacket();
				if (DataQueue.getInstance().getSize() > 1) {
					SendData(dataQueue.getValue());
					DataProvide.NET_SEND_MSG = dataQueue.getValue();
					dataQueue.deleteElement();
				}
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				DataProvide.NET_STATE = false;
			}
		}
	}

	/**
	 * �Զ������������
	 */
	public void startSend() {
		if (udpSendSocket == null)
			try {
				udpSendSocket = new DatagramSocket(55555);
			} catch (SocketException e) {
				Log.e("Farm", "DatagramSocket����ʧ��");
			}
		if (netSendThread == null) {
			netSendThread = new Thread(this);
			netSendThread.start();
		}
	}

	/**
	 * �Զ������ֹͣ����
	 */
	public void stopService() {
		DataProvide.NET_STATE = false;
		if (netSendThread != null) {
			netSendThread.stop();
			netSendThread = null;
		}
	}

}
