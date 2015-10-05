package com.ndk.FragmentActivity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ndk.Constant.SerialCommand;
import com.ndk.DataListenerService.SerialSendDataService;
import com.ndk.serialport.R;
import com.ndk.utils.DataProvide;

public class MainTab04 extends Fragment {
	private EditText edt_Auto_Air_Temp, edt_Auto_Soil_Humidity, edt_Auto_Lux,
			edt_Auto_CO2;
	private Button btn_Start_Autocontrol, btn_Stop_Autocontrol;
	float air_Temp, air_Humidity, soil_Temp, soil_Humidity;
	int Lux, CO2;

	public static Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				break;
			default:
				break;
			}
			super.handleMessage(msg);
		};
	};
	Runnable runnable = new Runnable() {

		@Override
		public void run() {
			if (air_Temp != 0) {

				autoControlAirTemp();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					Log.e("Farm", "休眠失败");
				}
			}
			if (soil_Humidity != 0) {

				autoControlSoilHumidity();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					Log.e("Farm", "休眠失败");
				}
			}
			if (Lux != 0) {
				autoControlLux();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					Log.e("Farm", "休眠失败");
				}
			}
			if (CO2 != 0) {

				autoControlCO2();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					Log.e("Farm", "休眠失败");
				}
			}
			handler.postDelayed(runnable, 1000);
		}

		private void autoControlCO2() {
			// CO2控制
			if (CO2 > DataProvide.CO2) { // 当前的CO2低于用户设定的CO2浓度（用户设定需要维持的恒定值）
				// 操作：关窗户 + 关风扇
				SerialSendDataService.getInstance().SendData(
						SerialCommand.CLOSE_FAN);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					Log.e("Farm", "休眠失败");
				}
				SerialSendDataService.getInstance().SendData(
						SerialCommand.CLOSE_WINDOWS);
			} else if (CO2 <= DataProvide.CO2) { // 当前的CO2浓度高于用户设定的CO2浓度
				// 操作：开风扇 + 开窗户
				SerialSendDataService.getInstance().SendData(
						SerialCommand.OPEN_FAN);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					Log.e("Farm", "休眠失败");
				}
				SerialSendDataService.getInstance().SendData(
						SerialCommand.OPEN_WINDOWS);
			}
		}

		private void autoControlLux() {
			// 光照强度自动控制
			if (Lux > DataProvide.LUX) {// 当前光照强度低于用户设定的光照强度（设定为用户需要维持的恒定值）
				// 操作：光照灯
				SerialSendDataService.getInstance().SendData(
						SerialCommand.OPEN_LIGHT);
			} else if (Lux <= DataProvide.LUX) {// 当前光照强度低于用户设定的光照强度（设定为用户需要维持的恒定值）
				// 操作：光照灯
				SerialSendDataService.getInstance().SendData(
						SerialCommand.CLOSE_LIGHT);
			}
		}

		private void autoControlSoilHumidity() {
			// 土壤湿度自动控制
			if (soil_Humidity > DataProvide.SOIL_HUMIDITY) { // 当前土壤湿度低于用户设定的湿度值（设定为用户需要维持的恒定温度）
				// 操作：开水泵
				SerialSendDataService.getInstance().SendData(
						SerialCommand.OPEN_WATE);
			} else if (soil_Humidity <= DataProvide.SOIL_HUMIDITY) { // 当前土壤湿度低于用户设定的湿度值（设定为用户需要维持的恒定温度）
				// 操作：关水泵
				SerialSendDataService.getInstance().SendData(
						SerialCommand.CLOSE_WATE);
			}
		}

		private void autoControlAirTemp() {
			// 空气温度自动控制
			if (air_Temp > DataProvide.AIR_TEMP) {// 当空气温度低于用户设定的温度（设定为温度需要维持的恒定值）
				// 操作： 打开加热器
				SerialSendDataService.getInstance().SendData(
						SerialCommand.OPEN_HEATER);
			} else {
				// 操作：关闭加热器
				SerialSendDataService.getInstance().SendData(
						SerialCommand.OPEN_HEATER);
			}
			if ((air_Temp + 5) < DataProvide.AIR_TEMP) {// 如果温度超过了5℃
				// 操作：开风扇 + 开窗户
				SerialSendDataService.getInstance().SendData(
						SerialCommand.OPEN_FAN);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					Log.e("Farm", "休眠失败");
				}
				SerialSendDataService.getInstance().SendData(
						SerialCommand.OPEN_WINDOWS);
			}
		}
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.main_tab_04, container, false);

		initWidget(view);

		return view;
	}

	/**
	 * 初始化界面
	 * 
	 * @param view
	 */
	private void initWidget(View view) {
		edt_Auto_Air_Temp = (EditText) view
				.findViewById(R.id.edt_auto_air_temp);

		edt_Auto_Soil_Humidity = (EditText) view
				.findViewById(R.id.edt_auto_soil_humidity);
		edt_Auto_Lux = (EditText) view.findViewById(R.id.edt_auto_lux);
		edt_Auto_CO2 = (EditText) view.findViewById(R.id.edt_auto_co2);

		btn_Start_Autocontrol = (Button) view.findViewById(R.id.btn_auto_start);
		btn_Stop_Autocontrol = (Button) view.findViewById(R.id.btn_auto_stop);

		btn_Start_Autocontrol.setOnClickListener(new MyClickListener());
		btn_Stop_Autocontrol.setOnClickListener(new MyClickListener());
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
			case R.id.btn_auto_start:
				getValue();
				handler.postDelayed(runnable, 1000);
				btn_Start_Autocontrol.setClickable(false);
				btn_Stop_Autocontrol.setClickable(true);
				break;
			case R.id.btn_auto_stop:
				handler.removeCallbacksAndMessages(null);
				btn_Start_Autocontrol.setClickable(true);
				btn_Stop_Autocontrol.setClickable(false);
				break;
			default:
				break;
			}
		}
	}

	/**
	 * 获取界面值
	 */
	private void getValue() {

		try {
			air_Temp = Float.parseFloat(edt_Auto_Air_Temp.getText().toString()
					.trim());
		} catch (NumberFormatException e) {
			air_Temp = 0;
		}

		try {
			soil_Humidity = Float.parseFloat(edt_Auto_Soil_Humidity.getText()
					.toString().trim());
		} catch (NumberFormatException e) {
			soil_Humidity = 0;
		}
		try {
			Lux = Integer.parseInt(edt_Auto_Lux.getText().toString().trim());
		} catch (NumberFormatException e) {
			Lux = 0;
		}
		try {
			CO2 = Integer.parseInt(edt_Auto_CO2.getText().toString().trim());
		} catch (NumberFormatException e) {
			CO2 = 0;
		}
		if (air_Temp == 0 && soil_Humidity == 0 && Lux == 0 && CO2 == 0) {
			Toast.makeText(this.getActivity(), "数据不能全部为空", 0).show();
		} else {
			Toast.makeText(this.getActivity(), "已开启自动控制", 0).show();
		}

	}

}
