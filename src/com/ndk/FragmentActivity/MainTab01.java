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
import android.widget.EditText;
import android.widget.ImageButton;

import com.ndk.Constant.SerialCommand;
import com.ndk.DataListenerService.SerialSendDataService;
import com.ndk.serialport.R;
import com.ndk.utils.DataProvide;

public class MainTab01 extends Fragment {

	/**
	 * 按钮控件、文本框等创建
	 */
	private EditText edt_Air_Temp, edt_Air_Humidity, edt_Soil_Temp,
			edt_Soil_Humidity;
	private Button heater_Open, heater_Close, water_Open, water_Close;
	private ImageButton img_Heater,img_Water;
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
			edt_Air_Temp.setText(DataProvide.AIR_TEMP + "");
			edt_Air_Humidity.setText(DataProvide.AIR_HUMIDITY + "");
			edt_Soil_Temp.setText(DataProvide.SOIL_TEMP + "");
			edt_Soil_Humidity.setText(DataProvide.SOIL_HUMIDITY + "");			
			handler.postDelayed(runnable, 1000);
		}
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.main_tab_01, container, false);

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
			case R.id.btn_heater_open:
				SerialSendDataService.getInstance().SendData(
						SerialCommand.OPEN_HEATER);
				img_Heater.setBackgroundResource(R.drawable.btn_heater_open);
				DataProvide.HEATER_STATE = "O";
				break;
			case R.id.btn_heater_close:
				SerialSendDataService.getInstance().SendData(
						SerialCommand.CLOSE_HEATER);
				img_Heater.setBackgroundResource(R.drawable.btn_heater_close);
				DataProvide.HEATER_STATE = "C";
				break;
			case R.id.btn_water_open:
				SerialSendDataService.getInstance().SendData(
						SerialCommand.OPEN_WATE);
				img_Water.setBackgroundResource(R.drawable.btn_water_open);
				DataProvide.WATER_STATE = "O";
				break;
			case R.id.btn_water_close:
				SerialSendDataService.getInstance().SendData(
						SerialCommand.CLOSE_WATE);
				img_Water.setBackgroundResource(R.drawable.btn_water_close);
				DataProvide.WATER_STATE = "C";
				break;
			default:
				break;
			}
		}
	}

	/**
	 * 初始化界面上的按钮文本框等控件
	 */
	private void initWidget(View view) {
		edt_Air_Temp = (EditText) view.findViewById(R.id.edt_air_temp);
		edt_Air_Humidity = (EditText) view.findViewById(R.id.edt_air_humidity);
		edt_Soil_Temp = (EditText) view.findViewById(R.id.edt_soil_temp);
		edt_Soil_Humidity = (EditText) view
				.findViewById(R.id.edt_soil_humidity);
		img_Heater = (ImageButton) view.findViewById(R.id.img_heater);
		img_Water = (ImageButton) view.findViewById(R.id.img_water);
		
		heater_Open = (Button) view.findViewById(R.id.btn_heater_open);
		heater_Close = (Button) view.findViewById(R.id.btn_heater_close);
		water_Open = (Button) view.findViewById(R.id.btn_water_open);
		water_Close = (Button) view.findViewById(R.id.btn_water_close);
		// 绑定监听事件
		heater_Open.setOnClickListener(new MyClickListener());
		heater_Close.setOnClickListener(new MyClickListener());
		water_Open.setOnClickListener(new MyClickListener());
		water_Close.setOnClickListener(new MyClickListener());
	}
}
