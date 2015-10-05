package com.ndk.FragmentActivity;

/***********************************************
 * 目前问题：
 * 1.Fragment布局
 * 2.jni编程，需要与类名相同
 * 3.短信验证
 * 4.添加语音识别功能
 * 5.实现定时任务功能
 * 6.设置界面  串口、网络、短信、其它
 * 7.调试界面 连接反馈 网络调试、短信调试、其它
 * 8.Service DatagramSocket 向上层抽取
 * 9.为了减少网路资源消耗，尽量采用TCP协议来完成短连接服务
 **********************************************/

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ndk.DataListenerService.NetSendService;
import com.ndk.DataListenerService.NetService;
import com.ndk.DataListenerService.SerialService;
import com.ndk.camera.CameraActivity;
import com.ndk.httpService.HttpService;
import com.ndk.serialport.R;
import com.ndk.utils.DataProvide;
import com.ndk.utils.InterAddressUtil;
import com.ndk.utils.MyPrefer;

public class MainActivity extends FragmentActivity {
	private static MainActivity mainActivity;
	private long exitTime = 0;
	private ViewPager mViewPager;
	private FragmentPagerAdapter mAdapter;
	private WifiManager wifiManager = null;
	private List<Fragment> mFragments = new ArrayList<Fragment>();

	/**
	 * 底部四个按钮
	 */
	private LinearLayout mTabBtnWeixin;
	private LinearLayout mTabBtnFrd;
	private LinearLayout mTabBtnAddress;
	private LinearLayout mTabBtnSettings;
	private LinearLayout mTabBtnCamera;

	/**
	 * onCreate程序开始函数
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* 设置全屏 */
		// final Window win = this.getWindow();
		// win.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		// WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		mViewPager = (ViewPager) findViewById(R.id.id_viewpager);

		initView();
		initParameters();
		CheckNetState();
		/**
		 * 通过 Intent 来开启服务 串口监听服务 网络数据发送服务
		 */
//		Intent serialIntent = new Intent(this, SerialService.class);
//		startService(serialIntent);
//
//		Intent netIntent = new Intent(this, NetService.class);
//		startService(netIntent);

//		Intent httpIntent = new Intent(this, HttpService.class);
//		startService(httpIntent);

		mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

			@Override
			public int getCount() {
				return mFragments.size();
			}

			@Override
			public Fragment getItem(int arg0) {
				return mFragments.get(arg0);
			}
		};

		mViewPager.setAdapter(mAdapter);

		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			private int currentIndex;

			public void onPageSelected(int position) {
				resetTabBtn();
				switch (position) {
				case 0:
					((ImageButton) mTabBtnWeixin
							.findViewById(R.id.btn_tab_bottom_weixin))
							.setImageResource(R.drawable.ico_main_passed);
					break;
				case 1:
					((ImageButton) mTabBtnFrd
							.findViewById(R.id.btn_tab_bottom_friend))
							.setImageResource(R.drawable.ico_left_passed);
					break;
				case 2:
					((ImageButton) mTabBtnAddress
							.findViewById(R.id.btn_tab_bottom_contact))
							.setImageResource(R.drawable.ico_middle_passed);
					break;
				case 3:
					((ImageButton) mTabBtnSettings
							.findViewById(R.id.btn_tab_bottom_setting))
							.setImageResource(R.drawable.ico_right_passed);
					break;
				case 4:
					((ImageButton) mTabBtnCamera
							.findViewById(R.id.btn_tab_bottom_camera))
							.setImageResource(R.drawable.ico_last_passed);

					break;
				}

				currentIndex = position;
			}

			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			public void onPageScrollStateChanged(int arg0) {
			}
		});
		mainActivity = this;

	}

	private void CheckNetState() {
		String IP = InterAddressUtil.getLocalIpAddress();
		DataProvide.NET_NATIVE_IP = IP;
		wifiManager = (WifiManager) this.getSystemService(this.WIFI_SERVICE);
		int stat = WifiManager.WIFI_STATE_DISABLED; // 将初始值设置为WiFi关闭状态码
		String Wifi_Ip = "";
		if (wifiManager != null) {
			stat = wifiManager.getWifiState();
			WifiInfo wifiInfo = wifiManager.getConnectionInfo();
			int ipAddress = wifiInfo.getIpAddress();
			Wifi_Ip = intToIp(ipAddress);
			Log.i("Farm", "ip地址为：" + Wifi_Ip);
			Log.d("Farm", "WIFI状态：" + wifiManager.getWifiState());
		}
		DataProvide.NET_WIFI_IP = Wifi_Ip;

		Log.i("Farm", "以太网IP地址为：" + IP + " - WIFI地址为：" + Wifi_Ip);
	}

	/**
	 * 将二进制的IP地址转化为10进制的IP地址，并返回第一组。
	 * 
	 * @param ipAddress
	 * @return
	 */
	private String intToIp(int ipAddress) {

		return (ipAddress & 0xFF) + "." + (0xFF & ipAddress >> 8) + "."
				+ (0xFF & ipAddress >> 16) + "." + (0xFF & ipAddress >> 24);
	}

	/**
	 * 将数据进行初始化
	 */
	private void initParameters() {
		MyPrefer prefer = new MyPrefer(this);
		DataProvide.NET_SEND_IP = prefer.getIp();
		DataProvide.NET_SEND_PORT = prefer.getSendPort();
		DataProvide.NET_RECV_PORT = prefer.getRecvPort();
		DataProvide.SERIAL_PORT = prefer.getSerial();
		DataProvide.BAUD_RATE = prefer.getBaudRate();
		DataProvide.PHONE_NUMBER = prefer.getPhoneNumber() + "";
		DataProvide.URL = prefer.getUrl();

	}

	protected void resetTabBtn() {
		((ImageButton) mTabBtnWeixin.findViewById(R.id.btn_tab_bottom_weixin))
				.setImageResource(R.drawable.ico_main_normal);
		((ImageButton) mTabBtnFrd.findViewById(R.id.btn_tab_bottom_friend))
				.setImageResource(R.drawable.ico_left_normal);
		((ImageButton) mTabBtnAddress.findViewById(R.id.btn_tab_bottom_contact))
				.setImageResource(R.drawable.ico_middle_normal);
		((ImageButton) mTabBtnSettings
				.findViewById(R.id.btn_tab_bottom_setting))
				.setImageResource(R.drawable.ico_right_normal);
		((ImageButton) mTabBtnCamera.findViewById(R.id.btn_tab_bottom_camera))
				.setImageResource(R.drawable.ico_last_normal);

	}

	private void initView() {

		mTabBtnWeixin = (LinearLayout) findViewById(R.id.id_tab_bottom_weixin);
		mTabBtnFrd = (LinearLayout) findViewById(R.id.id_tab_bottom_friend);
		mTabBtnAddress = (LinearLayout) findViewById(R.id.id_tab_bottom_contact);
		mTabBtnSettings = (LinearLayout) findViewById(R.id.id_tab_bottom_setting);
		mTabBtnCamera = (LinearLayout) findViewById(R.id.id_tab_bottom_camera);

		MainTab01 tab01 = new MainTab01();
		MainTab02 tab02 = new MainTab02();
		MainTab03 tab03 = new MainTab03();
		MainTab04 tab04 = new MainTab04();
		MainTab05 tab05 = new MainTab05();
		mFragments.add(tab01);
		mFragments.add(tab02);
		mFragments.add(tab03);
		mFragments.add(tab04);
		mFragments.add(tab05);
	}	

	/**
	 * 设置
	 * 
	 * @param view
	 */

	public void Setting(View view) {
		Intent intent = new Intent(MainActivity.this, SettingActivity.class);
		startActivity(intent);
	}

	/**
	 * 标题栏左侧按钮点击事件
	 * 
	 * @param view
	 */
	public void AboutUS(View view) {
		Intent intent = new Intent(MainActivity.this, DebugActivity.class);
		startActivity(intent);
	}

	public void StartCamera(View view) {
		Intent intent = new Intent(MainActivity.this, CameraActivity.class);
		startActivity(intent);
	}

	/* 加载JNI代码编译生成的共享库 */
	static {
		System.loadLibrary("serialport-jni");
	}

	/**
	 * 连续点击两次返回退出程序
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			if ((System.currentTimeMillis() - exitTime) > 2000) {
				Toast.makeText(getApplicationContext(), "再按一次退出程序",
						Toast.LENGTH_SHORT).show();
				exitTime = System.currentTimeMillis();
			} else {
				finish();
				exit();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	/**
	 * 退出结束服务
	 */
	public static void exit() {
		mainActivity.finish();
		mainActivity.stopService(new Intent(mainActivity, NetService.class));
		mainActivity.stopService(new Intent(mainActivity, SerialService.class));
		mainActivity.stopService(new Intent(mainActivity, HttpService.class));
		System.exit(0);
	}
/**
 * 一键离家
 * 关闭网络数据发送	
 */
//	public void LeaveHone(View view){
//		Intent netIntent = new Intent(this, NetService.class);
//		stopService(netIntent);
//	}
	
}
