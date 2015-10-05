package com.ndk.DataListenerService;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import com.ndk.Constant.InfoConstant;
import com.ndk.utils.DataProvide;
import com.ndk.utils.NetDataUtils;

import android.util.Log;

public class NetReceiveService implements Runnable {
	/********************************* ���ݽ��ձ��� ***************************************/
	private String netRecvData;
	private Thread netRecvThread = null;// ���������߳�
	private InetAddress address = null;
	private DatagramSocket netRecvSocket = null;// ��������Socket
	private DatagramPacket rPacket = null;
	private int netRecvPort = DataProvide.NET_RECV_PORT;
	private byte[] rBuffer = new byte[1024];// �������ݻ���1024�ֽ�

	public NetReceiveService(DatagramSocket netUdpSocket2) {
		netRecvSocket = netUdpSocket2;
	}

	/**
	 * �Զ��忪ʼ����������
	 */
	public void startReceive() {

		if (UdpInit()) {
			Log.d("Farm","�����߳��Ѿ�����");

		} else {
			Log.d("Farm","�����߳��Ѿ������в�������");
		}
	}

	/**
	 * udp��ʼ����������������udp���ݰ����򿪶˿�
	 * 
	 * @return
	 */
	private boolean UdpInit() {
		boolean result = false;
		try {
			if (netRecvSocket == null)
				netRecvSocket = new DatagramSocket(netRecvPort);
			if (rPacket == null)
				rPacket = new DatagramPacket(rBuffer, rBuffer.length);
			startThread();
			result = true;
			Log.d("Farm", "�߳�������");
		} catch (SocketException se) {
			DisConnectSocket();
			Log.d("Farm","open udp port error:" + se.getMessage());
		}
		return result;
	}

	/**
	 * �Ͽ�����
	 */
	public void DisConnectSocket() {
		if (netRecvSocket != null) { 
			netRecvSocket.close();
			netRecvSocket = null;
		}
		if (rPacket != null)
			rPacket = null;
		stopService();
	}

	/**
	 * �Զ��巽�������߳�
	 */
	private void startThread() {
		if (netRecvThread == null) {
			netRecvThread = new Thread(this);
			netRecvThread.start();
		}
	}

	/**
	 * �Զ���ֹͣ����������
	 */
	public void stopService() {
		if (netRecvThread != null) {
			netRecvThread.stop();
			netRecvThread = null;
		}
	}

	/**
	 * �߳�run()����
	 */
	@Override
	public void run() {
		while (Thread.currentThread() == netRecvThread) {
			try {

				recvData();
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * ���յ��������ı���ʽ����ʾ
	 */
	private void recvData() {
		try {
			netRecvSocket.receive(rPacket);
			netRecvData = new String(rPacket.getData(), InfoConstant.ENDODED)
					.trim();
			/**
			 * �����յ������ݽ��н�����
			 */
			NetDataUtils.AnalyzeData(netRecvData);
			DataProvide.NET_RECV_MSG = netRecvData;
//			Log.d("Farm", "���յ����ݣ�" + netRecvData);
			rBuffer = null;
			rBuffer = new byte[16];
			rPacket = new DatagramPacket(rBuffer, rBuffer.length); // �˴���ջ������ķ�ʽ��������

		} catch (IOException ie) {
			Log.d("Farm", "���ݴ���");
		}catch (Exception e) {
//			stopService();
			Log.e("Farm", "���ݽ��մ��ڴ���");
		}
	}
}
