package com.ndk.FragmentActivity;

import com.ndk.Constant.NetCommand;
import com.ndk.DataListenerService.SerialSendDataService;
import com.ndk.camera.CameraActivity;
import com.ndk.serialport.R;
import com.ndk.utils.DataProvide;
import com.ndk.utils.DataQueue;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DebugActivity extends Activity {
	private EditText edt_Serial_Send, edt_Serial_Recv, edt_Net_Send,
			edt_Net_Recv, edt_Net_EditkSend, edt_Serial_Recv_Hex;
	private TextView tv_Net_State;
	private String Serial_Msg;
	/**
	 * 创建Handler
	 */
	private Handler handler = new Handler() {
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
	/**
	 * 更新UI线程
	 */
	Runnable runnable = new Runnable() {
		public void run() {
			initValues();
			handler.postDelayed(runnable, 1000);
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_debug);
		intiView();
		initValues();
		handler.postDelayed(runnable, 1000);
	}

	/**
	 * 初始化文本框的初始值 应该用 Handler 来实时更新UI
	 */
	private void initValues() {
		// 设置为串口接收到的数据，并且应该转化为16进制数据
		edt_Serial_Recv.setText(DataProvide.SERIAL_RECV_MSG);
		edt_Net_Send.setText(DataProvide.NET_SEND_MSG);
		char[] serial_Msg = DataProvide.NET_RECV_MSG.toCharArray();

		for (int i = 0; i < serial_Msg.length; i++) {
			Serial_Msg += Integer.toHexString(serial_Msg[i]);
		}
		Serial_Msg += "\n";		
		edt_Serial_Recv_Hex.setText(Serial_Msg);
		if(Serial_Msg.length() >= 154){// 默认只让他显示一次数据
			Serial_Msg = "";
		}
		// 设置为通过网络发送的数据
		edt_Net_Send.setText(DataQueue.getInstance().getValue());
		if (DataProvide.NET_STATE == true) {
			tv_Net_State.setText("已连接");
			tv_Net_State.setTextColor(Color.rgb(0, 255, 0));
		} else {
			tv_Net_State.setText("未连接");
			tv_Net_State.setTextColor(Color.rgb(255, 0, 0));
		}

	}

	/**
	 * 初始化界面控件
	 */
	private void intiView() {
		edt_Serial_Send = (EditText) findViewById(R.id.edt_debug_serial_send);
		edt_Serial_Recv = (EditText) findViewById(R.id.edt_debug_serial_recv);
		edt_Net_Send = (EditText) findViewById(R.id.edt_debug_net_send);
		edt_Net_Recv = (EditText) findViewById(R.id.edt_debug_net_recv);
		edt_Net_EditkSend = (EditText) findViewById(R.id.edt_debug_net_editsend);
		tv_Net_State = (TextView) findViewById(R.id.tv_debug_net_state);
		edt_Serial_Recv_Hex = (EditText) findViewById(R.id.edt_debug_serial_recv_hex);
	}

	/**
	 * 网络测试按钮点击事件
	 * 
	 * @param view
	 */
	public void NetStateTest(View view) {
		// 添加一条测试语句，防止数据丢失
		DataQueue.getInstance().addElement(NetCommand.NET_STATE_TEST);

	}

	/**
	 * 串口发送按钮点击事件
	 * 
	 * @param view
	 */
	public void SendSerialUserData(View view) {
		String data = edt_Net_EditkSend.getText().toString().trim();
		if (!data.equals("")) {
			char[] dataArray = data.toCharArray();
			SerialSendDataService.getInstance().SendData(dataArray);
		} else {
			Toast.makeText(DebugActivity.this, "发送内容不能为空", 0).show();
		}

	}

	/**
	 * 网络发送按钮点击事件
	 * 
	 * @param view
	 */
	public void SendNetUserData(View view) {
		String data = edt_Net_Send.getText().toString().trim();
		if (!data.equals("")) {
			DataQueue.getInstance().addElement(data);
		} else {
			Toast.makeText(DebugActivity.this, "发送内容不能为空", 0).show();
		}

	}

	public void Setting(View view) {
		startActivity(new Intent(DebugActivity.this, SettingActivity.class));
	}

	public void AboutUS(View view) {
	}

	public void CameraTest(View view) {
		startActivity(new Intent(DebugActivity.this, CameraActivity.class));
	}

}
