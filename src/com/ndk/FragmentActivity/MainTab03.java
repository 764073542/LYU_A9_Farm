package com.ndk.FragmentActivity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.ndk.Constant.SerialCommand;
import com.ndk.DataListenerService.SerialSendDataService;
import com.ndk.serialport.R;
import com.ndk.utils.DataProvide;

public class MainTab03 extends Fragment {
	private EditText Lux, CO2;
	private Button light_Open, light_Close, fan_Open, fan_Close;
	private ImageButton img_Light, img_Fan_Close;
	private ProgressBar pro_Fan_Open;
	public static Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				break;
			default:
				break;
			}
			super.handleMessage(msg);
		}
	};
	Runnable runnable = new Runnable() {

		@Override
		public void run() {
			Lux.setText(DataProvide.LUX + "");
			CO2.setText(DataProvide.CO2 + "");
			handler.postDelayed(runnable, 1000);
		}
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.main_tab_03, container, false);
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
			case R.id.btn_light_open:
				SerialSendDataService.getInstance().SendData(
						SerialCommand.OPEN_LIGHT);
				img_Light.setBackgroundResource(R.drawable.btn_light_open);
				DataProvide.LIGHT_STATE = "O";
				break;
			case R.id.btn_light_close:
				SerialSendDataService.getInstance().SendData(
						SerialCommand.CLOSE_LIGHT);
				img_Light.setBackgroundResource(R.drawable.btn_light_close);
				DataProvide.LIGHT_STATE = "C";
				break;
			case R.id.btn_fan_open:
				SerialSendDataService.getInstance().SendData(
						SerialCommand.OPEN_FAN);
				pro_Fan_Open.setVisibility(ProgressBar.VISIBLE);
				img_Fan_Close.setVisibility(Button.GONE);
				DataProvide.FAN_STATE = "O";
				break;
			case R.id.btn_fan_close:
				SerialSendDataService.getInstance().SendData(
						SerialCommand.CLOSE_FAN);
				pro_Fan_Open.setVisibility(ProgressBar.GONE);
				img_Fan_Close.setVisibility(Button.VISIBLE);
				DataProvide.FAN_STATE = "C";
				break;
			default:
				break;
			}
		}
	}

	/**
	 * 初始化界面
	 * 
	 * @param view
	 */
	private void initWidget(View view) {
		Lux = (EditText) view.findViewById(R.id.edt_lux);
		CO2 = (EditText) view.findViewById(R.id.edt_co2);
		img_Light = (ImageButton) view.findViewById(R.id.img_light);
		img_Fan_Close = (ImageButton) view.findViewById(R.id.img_fan_close);
		pro_Fan_Open = (ProgressBar) view.findViewById(R.id.Progress_fan_open);
		light_Open = (Button) view.findViewById(R.id.btn_light_open);
		light_Close = (Button) view.findViewById(R.id.btn_light_close);
		fan_Open = (Button) view.findViewById(R.id.btn_fan_open);
		fan_Close = (Button) view.findViewById(R.id.btn_fan_close);
		light_Open.setOnClickListener(new MyClickListener());
		light_Close.setOnClickListener(new MyClickListener());
		fan_Open.setOnClickListener(new MyClickListener());
		fan_Close.setOnClickListener(new MyClickListener());
	}

}
