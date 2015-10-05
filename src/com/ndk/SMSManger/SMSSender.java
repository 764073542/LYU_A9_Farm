package com.ndk.SMSManger;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ndk.serialport.R;
import com.ndk.utils.DataProvide;

public class SMSSender extends Activity {
	private EditText SMSText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.smssend);
		SMSText = (EditText) findViewById(R.id.edt_smscontent);
	}

	/**
	 * 发送短信按钮事件
	 * 
	 * @param view
	 */
	public void SendMesage(View view) {
		SendSMS(DataProvide.PHONE_NUMBER, SMSText.getText().toString().trim());
	}

	/**
	 * 短信发送函数
	 * 
	 * @param number
	 * @param message
	 */
	private void SendSMS(String number, String message) {
		String SENT = "sms_sent";
		String DELIVERED = "sms_delivered";

		PendingIntent sentPI = PendingIntent.getActivity(this, 0, new Intent(
				SENT), 0);
		PendingIntent deliveredPI = PendingIntent.getActivity(this, 0,
				new Intent(DELIVERED), 0);

		registerReceiver(new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				switch (getResultCode()) {
				case Activity.RESULT_OK:
					Log.i("====>", "Activity.RESULT_OK");
					break;
				case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
					Log.i("====>", "RESULT_ERROR_GENERIC_FAILURE");
					break;
				case SmsManager.RESULT_ERROR_NO_SERVICE:
					Log.i("====>", "RESULT_ERROR_NO_SERVICE");
					break;
				case SmsManager.RESULT_ERROR_NULL_PDU:
					Log.i("====>", "RESULT_ERROR_NULL_PDU");
					break;
				case SmsManager.RESULT_ERROR_RADIO_OFF:
					Log.i("====>", "RESULT_ERROR_RADIO_OFF");
					break;
				}
			}
		}, new IntentFilter(SENT));

		registerReceiver(new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				switch (getResultCode()) {
				case Activity.RESULT_OK:
					Toast.makeText(SMSSender.this, "短信发送成功", 0).show();
					Log.i("====>", "RESULT_OK");
					break;
				case Activity.RESULT_CANCELED:
					Log.i("=====>", "RESULT_CANCELED");
					break;
				}
			}
		}, new IntentFilter(DELIVERED));

		SmsManager smsm = SmsManager.getDefault();
		smsm.sendTextMessage(number, null, message, sentPI, deliveredPI);
	}
}