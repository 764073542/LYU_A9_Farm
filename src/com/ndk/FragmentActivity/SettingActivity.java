package com.ndk.FragmentActivity;

import com.ndk.serialport.R;
import com.ndk.utils.DataProvide;
import com.ndk.utils.MyPrefer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SettingActivity extends Activity {
	private Spinner spinner_Serial, spinner_BaudRate;
	private EditText edt_Ip_Send, edt_Port_send, edt_port_Recv, edt_Ip_Native,
			edt_IP_Wifi, edt_Url, edt_Phone_Number;
	private String[] items_Serial = { "/dev/ttySAC0", "/dev/ttySAC1",
			"/dev/ttySAC2", "/dev/ttySAC3" };
	private String[] items_Rate = { "2400", "4800", "9600", "19200", "56000",
			"115200" };
	private String Serial = "/dev/ttySAC0", Baudrate = "9600";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		initView();
		initSpinner();
		initValues();
		/**
		 * 串口下拉列表点击事件
		 */
		spinner_Serial.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				String str = parent.getItemAtPosition(position).toString();
				Serial = str;
				DataProvide.SPINNER_SERIAL_POSITION = position;
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
		/**
		 * 波特率下拉列表点击事件
		 */
		spinner_BaudRate
				.setOnItemSelectedListener(new OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						String str = parent.getItemAtPosition(position)
								.toString();
						Baudrate = str;
						DataProvide.SPINNER_BAUDRATE_POSITION = position;
					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {

					}
				});
	}

	private void initValues() {
		edt_Ip_Send.setText(DataProvide.NET_SEND_IP);
		edt_Port_send.setText(DataProvide.NET_SEND_PORT + "");
		edt_port_Recv.setText(DataProvide.NET_RECV_PORT + "");
		edt_Phone_Number.setText(DataProvide.PHONE_NUMBER);
		spinner_BaudRate.setSelection(DataProvide.SPINNER_BAUDRATE_POSITION);
		spinner_Serial.setSelection(DataProvide.SPINNER_SERIAL_POSITION);
		edt_Ip_Native.setText(DataProvide.NET_NATIVE_IP);
		edt_IP_Wifi.setText(DataProvide.NET_WIFI_IP);
		edt_Url.setText(DataProvide.URL);
	}

	private void initSpinner() {
		// 创建适配器对象
		ArrayAdapter<String> adapter_Serial = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, items_Serial);
		ArrayAdapter<String> adapter_Rate = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, items_Rate);
		adapter_Serial
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner_Serial.setAdapter(adapter_Serial);
		adapter_Rate
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner_BaudRate.setAdapter(adapter_Rate);
	}

	/**
	 * 初始化控件
	 */
	private void initView() {
		// 初始化Spinner，以及添加适配器
		spinner_Serial = (Spinner) findViewById(R.id.spinner_serial);
		spinner_BaudRate = (Spinner) findViewById(R.id.spinner_rate);
		edt_Ip_Send = (EditText) findViewById(R.id.edt_ip_send);
		edt_Ip_Native = (EditText) findViewById(R.id.edt_ip_native);
		edt_IP_Wifi = (EditText) findViewById(R.id.edt_ip_wifi);
		edt_Port_send = (EditText) findViewById(R.id.edt_port_send);
		edt_port_Recv = (EditText) findViewById(R.id.edt_port_recv);
		edt_Phone_Number = (EditText) findViewById(R.id.edt_phone_number);
		edt_Url = (EditText) findViewById(R.id.edt_url);

	}

	/**
	 * 保存按钮点击事件
	 * 
	 * @param view
	 */
	public void Setting_Save(View view) {
		MyPrefer prefer = new MyPrefer(this);
		String IP = (edt_Ip_Send.getText() + "").trim();
		String Sendport = (edt_Port_send.getText() + "").trim();
		String Recvport = (edt_port_Recv.getText() + "").trim();
		String PhoneNumber = (edt_Phone_Number.getText() + "").trim();
		String URL = (edt_Url.getText() + "").trim();
		int sport = 0;
		int rport = 0;
		int baudrate = 0;
		try {
			sport = Integer.parseInt(Sendport);
			rport = Integer.parseInt(Recvport);
			baudrate = Integer.parseInt(Baudrate);
		} catch (NumberFormatException e) {
			Log.e("Farm", "整数数据转换发生异常");
			Toast.makeText(this, "以上数据不能为空", 0).show();
			return;
		}
		/**
		 * 判断数据是否正常
		 */
		/* 判断数据是否为空 */
		if (IP.equals("") || Sendport.equals("") || Recvport.equals("")
				|| PhoneNumber.equals("") || URL.equals("")) {
			Toast.makeText(this, "以上数据不能为空", 0).show();
			return;
		}
		/* 判断端口号是否满足 */
		if (sport > 65534 || sport < 1000 || rport < 1000 || rport > 65534) {
			Toast.makeText(this, "请填写端口号范围在1000-65534之间的数据", 0).show();
			return;
		}
		/* 判断手机号码是否正确 */
		if (PhoneNumber.length() != 11 || !(PhoneNumber.startsWith("1"))) {
			Toast.makeText(this, "手机号码不正确", 0).show();
			return;
		}

		/* 将数据更新到SharedPreferences中 */
		prefer.setIP(IP);
		prefer.setSendPort(Sendport);
		prefer.setRecvPort(Recvport);
		prefer.setSerial(Serial);
		prefer.setBaudRate(Baudrate);
		prefer.setPhoneNumber(PhoneNumber);

		prefer.setUrl(URL);

		/* 将数据更新到DataProvide中 */
		DataProvide.NET_SEND_IP = IP;
		DataProvide.NET_SEND_PORT = sport;
		DataProvide.NET_RECV_PORT = rport;
		DataProvide.PHONE_NUMBER = PhoneNumber;
		DataProvide.SERIAL_PORT = Serial;
		DataProvide.BAUD_RATE = baudrate;
		DataProvide.URL = URL;

		Toast.makeText(this, "保存成功", 0).show();

	}

	public void Setting(View view) {
	}

	public void AboutUS(View view) {
		startActivity(new Intent(SettingActivity.this, DebugActivity.class));
	}
}
