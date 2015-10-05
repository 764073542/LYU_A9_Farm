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
					Log.e("Farm", "����ʧ��");
				}
			}
			if (soil_Humidity != 0) {

				autoControlSoilHumidity();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					Log.e("Farm", "����ʧ��");
				}
			}
			if (Lux != 0) {
				autoControlLux();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					Log.e("Farm", "����ʧ��");
				}
			}
			if (CO2 != 0) {

				autoControlCO2();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					Log.e("Farm", "����ʧ��");
				}
			}
			handler.postDelayed(runnable, 1000);
		}

		private void autoControlCO2() {
			// CO2����
			if (CO2 > DataProvide.CO2) { // ��ǰ��CO2�����û��趨��CO2Ũ�ȣ��û��趨��Ҫά�ֵĺ㶨ֵ��
				// �������ش��� + �ط���
				SerialSendDataService.getInstance().SendData(
						SerialCommand.CLOSE_FAN);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					Log.e("Farm", "����ʧ��");
				}
				SerialSendDataService.getInstance().SendData(
						SerialCommand.CLOSE_WINDOWS);
			} else if (CO2 <= DataProvide.CO2) { // ��ǰ��CO2Ũ�ȸ����û��趨��CO2Ũ��
				// ������������ + ������
				SerialSendDataService.getInstance().SendData(
						SerialCommand.OPEN_FAN);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					Log.e("Farm", "����ʧ��");
				}
				SerialSendDataService.getInstance().SendData(
						SerialCommand.OPEN_WINDOWS);
			}
		}

		private void autoControlLux() {
			// ����ǿ���Զ�����
			if (Lux > DataProvide.LUX) {// ��ǰ����ǿ�ȵ����û��趨�Ĺ���ǿ�ȣ��趨Ϊ�û���Ҫά�ֵĺ㶨ֵ��
				// ���������յ�
				SerialSendDataService.getInstance().SendData(
						SerialCommand.OPEN_LIGHT);
			} else if (Lux <= DataProvide.LUX) {// ��ǰ����ǿ�ȵ����û��趨�Ĺ���ǿ�ȣ��趨Ϊ�û���Ҫά�ֵĺ㶨ֵ��
				// ���������յ�
				SerialSendDataService.getInstance().SendData(
						SerialCommand.CLOSE_LIGHT);
			}
		}

		private void autoControlSoilHumidity() {
			// ����ʪ���Զ�����
			if (soil_Humidity > DataProvide.SOIL_HUMIDITY) { // ��ǰ����ʪ�ȵ����û��趨��ʪ��ֵ���趨Ϊ�û���Ҫά�ֵĺ㶨�¶ȣ�
				// ��������ˮ��
				SerialSendDataService.getInstance().SendData(
						SerialCommand.OPEN_WATE);
			} else if (soil_Humidity <= DataProvide.SOIL_HUMIDITY) { // ��ǰ����ʪ�ȵ����û��趨��ʪ��ֵ���趨Ϊ�û���Ҫά�ֵĺ㶨�¶ȣ�
				// ��������ˮ��
				SerialSendDataService.getInstance().SendData(
						SerialCommand.CLOSE_WATE);
			}
		}

		private void autoControlAirTemp() {
			// �����¶��Զ�����
			if (air_Temp > DataProvide.AIR_TEMP) {// �������¶ȵ����û��趨���¶ȣ��趨Ϊ�¶���Ҫά�ֵĺ㶨ֵ��
				// ������ �򿪼�����
				SerialSendDataService.getInstance().SendData(
						SerialCommand.OPEN_HEATER);
			} else {
				// �������رռ�����
				SerialSendDataService.getInstance().SendData(
						SerialCommand.OPEN_HEATER);
			}
			if ((air_Temp + 5) < DataProvide.AIR_TEMP) {// ����¶ȳ�����5��
				// ������������ + ������
				SerialSendDataService.getInstance().SendData(
						SerialCommand.OPEN_FAN);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					Log.e("Farm", "����ʧ��");
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
	 * ��ʼ������
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
	 * ������ť����¼�
	 * 
	 * @author QQ764073542 �򴮿ڷ����ض�����
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
	 * ��ȡ����ֵ
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
			Toast.makeText(this.getActivity(), "���ݲ���ȫ��Ϊ��", 0).show();
		} else {
			Toast.makeText(this.getActivity(), "�ѿ����Զ�����", 0).show();
		}

	}

}
