package com.ndk.FragmentActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ndk.Constant.SerialCommand;
import com.ndk.DataListenerService.SerialSendDataService;
import com.ndk.serialport.R;
import com.ndk.utils.DataProvide;

public class MainTab02 extends Fragment {
	private TextView ring, windSpeed;
	private Button windows_Open, windows_Close;
	private ImageView img_Windows;
	/**
	 * Handle更新线程
	 */
	public static Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			Log.d("Farm", "Hanlder" + msg.what);
			switch (msg.what) {
			case 0:
				break;

			default:
				break;
			}
			super.handleMessage(msg);
		}
	};
	/**
	 * 更新UI的子线程
	 */
	Runnable runnable = new Runnable() {
		public void run() {
			/**
			 * 控件更新逻辑
			 */

			if (DataProvide.RING == 0) {
				ring.setText("晴朗");
			} else {
				ring.setText("有雨");
			}

			windSpeed.setText(DataProvide.WINDSPEED + "");

			handler.postDelayed(runnable, 1000); // 每个1秒更新一次界面

		}
	};

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.main_tab_02, container, false);

		initWidget(view);
		handler.postDelayed(runnable, 1000);
		return view;
	}

	/**
	 * 监听按钮点击事件
	 * 
	 * @author QQ764073542 向串口发送特定命令
	 */
	class MyClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_windows_open:
				SerialSendDataService.getInstance().SendData(
						SerialCommand.OPEN_WINDOWS);
				img_Windows.setImageResource(R.drawable.btn_window_open);
				DataProvide.WINDOWS_STATE = "O";
				break;
			case R.id.btn_windows_close:
				SerialSendDataService.getInstance().SendData(
						SerialCommand.CLOSE_WINDOWS);
				img_Windows.setImageResource(R.drawable.btn_window_close);
				DataProvide.WINDOWS_STATE = "C";
				break;
			default:
				break;
			}
		}
	}

	/**
	 * 初始化界面
	 */
	private void initWidget(View view) {
		ring = (TextView) view.findViewById(R.id.tv_ring_state);
		windSpeed = (TextView) view.findViewById(R.id.tv_windspeed);
		img_Windows = (ImageView) view.findViewById(R.id.img_windows);
		windows_Open = (Button) view.findViewById(R.id.btn_windows_open);
		windows_Close = (Button) view.findViewById(R.id.btn_windows_close);
		windows_Open.setOnClickListener(new MyClickListener());
		windows_Close.setOnClickListener(new MyClickListener());
	}
}
