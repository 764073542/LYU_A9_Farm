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
	/********************************* 数据接收变量 ***************************************/
	private String netRecvData;
	private Thread netRecvThread = null;// 接收数据线程
	private InetAddress address = null;
	private DatagramSocket netRecvSocket = null;// 接收数据Socket
	private DatagramPacket rPacket = null;
	private int netRecvPort = DataProvide.NET_RECV_PORT;
	private byte[] rBuffer = new byte[1024];// 接收数据缓存1024字节

	public NetReceiveService(DatagramSocket netUdpSocket2) {
		netRecvSocket = netUdpSocket2;
	}

	/**
	 * 自定义开始服务函数调用
	 */
	public void startReceive() {

		if (UdpInit()) {
			Log.d("Farm","接收线程已经启动");

		} else {
			Log.d("Farm","接收线程已经在运行不能启动");
		}
	}

	/**
	 * udp初始化函数，包括创建udp数据包，打开端口
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
			Log.d("Farm", "线程已启动");
		} catch (SocketException se) {
			DisConnectSocket();
			Log.d("Farm","open udp port error:" + se.getMessage());
		}
		return result;
	}

	/**
	 * 断开连接
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
	 * 自定义方法开启线程
	 */
	private void startThread() {
		if (netRecvThread == null) {
			netRecvThread = new Thread(this);
			netRecvThread.start();
		}
	}

	/**
	 * 自定义停止服务函数调用
	 */
	public void stopService() {
		if (netRecvThread != null) {
			netRecvThread.stop();
			netRecvThread = null;
		}
	}

	/**
	 * 线程run()方法
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
	 * 接收的数据以文本格式来显示
	 */
	private void recvData() {
		try {
			netRecvSocket.receive(rPacket);
			netRecvData = new String(rPacket.getData(), InfoConstant.ENDODED)
					.trim();
			/**
			 * 将接收到的数据进行解析。
			 */
			NetDataUtils.AnalyzeData(netRecvData);
			DataProvide.NET_RECV_MSG = netRecvData;
//			Log.d("Farm", "接收到数据：" + netRecvData);
			rBuffer = null;
			rBuffer = new byte[16];
			rPacket = new DatagramPacket(rBuffer, rBuffer.length); // 此处清空缓冲区的方式存在问题

		} catch (IOException ie) {
			Log.d("Farm", "数据错误");
		}catch (Exception e) {
//			stopService();
			Log.e("Farm", "数据接收存在错误！");
		}
	}
}
