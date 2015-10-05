package com.ndk.DataListenerService;

/****************************************
 * 只有发送端的IP地址和接受到的数据包的发送地址相同，在网络上数据包才不会被丢弃
 ***************************************/
/**
 * Linux控制平台上的数据发送应该放在线程里面，要每隔一段时间向外发送一次数据
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
	private DatagramSocket udpSendSocket;// 发送数据Socket
	private DatagramPacket sendPacket = null;
	private String netSendIP = DataProvide.NET_SEND_IP;
	private int netSendPort = DataProvide.NET_SEND_PORT;
	private byte[] sBuffer = null;
	private DataQueue dataQueue = DataQueue.getInstance();

	public NetSendService(DatagramSocket netUdpSocket) {
		udpSendSocket = netUdpSocket;
	}

	/**
	 * 将一些数据进行初始化 暂时不会被调用，因为程序运行时，再在Setting界面修改IP端口后，如何更新有问题。
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
	 * 发送普通文本格式的数据
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
			Log.d("Farm", "成功发送数据为：" + SData);
			sendPacket = null;
			sBuffer = null;
		} catch (IOException ie) {
			udpSendSocket.close();
			udpSendSocket = null;
			sendPacket = null;
			Log.d("Farm", "数据发送异常");
		} catch (Exception e) {
			// stopService();
			Log.e("Farm", "数据发送存在错误！");
		}
	}

	/**
	 * 线程run()方法
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
	 * 自定义服务开启方法
	 */
	public void startSend() {
		if (udpSendSocket == null)
			try {
				udpSendSocket = new DatagramSocket(55555);
			} catch (SocketException e) {
				Log.e("Farm", "DatagramSocket创建失败");
			}
		if (netSendThread == null) {
			netSendThread = new Thread(this);
			netSendThread.start();
		}
	}

	/**
	 * 自定义服务停止方法
	 */
	public void stopService() {
		DataProvide.NET_STATE = false;
		if (netSendThread != null) {
			netSendThread.stop();
			netSendThread = null;
		}
	}

}
