package com.ndk.httpService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URLEncoder;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;

import android.util.Log;

import com.ndk.utils.DataProvide;
import com.ndk.utils.JsonUtils;

public class HttpSendJson extends Thread {

	private static final String APPLICATION_JSON = "application/json";

	private static final String CONTENT_TYPE_TEXT_JSON = "text/json";

	private Thread httpJsonSendThread = null;

	public static void httpPostWithJSON(String url, String json)
			throws Exception {
		// 将JSON进行UTF-8编码,以便传输中文
		String encoderJson = URLEncoder.encode(json, HTTP.UTF_8);

		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		httpPost.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);

		StringEntity se = new StringEntity(encoderJson);
		se.setContentType(CONTENT_TYPE_TEXT_JSON);
		se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
				APPLICATION_JSON));
		httpPost.setEntity(se);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		int httpCode = httpResponse.getStatusLine().getStatusCode();
		if (httpCode == HttpURLConnection.HTTP_OK && httpResponse != null) {
			Log.i("Farm", "发送成功");
		} else {
			Log.i("Farm", "发送失败" + httpCode);
		}
	}

	/**
	 * 读取服务器返回来的数据
	 * 
	 * @param httpResponse
	 * @throws IOException
	 */
	private static void ReadWebFeedBack(HttpResponse httpResponse)
			throws IOException {
		Header[] headers = httpResponse.getAllHeaders();

		HttpEntity entity = httpResponse.getEntity();

		Header header = httpResponse.getFirstHeader("content-type");

		// 读取服务器返回的json数据（接受json服务器数据）

		InputStream inputStream = entity.getContent();

		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

		BufferedReader reader = new BufferedReader(inputStreamReader);// 读字符串用的。
		StringBuffer result = new StringBuffer();
		String s;

		while (((s = reader.readLine()) != null)) {
			result.append(s);
		}
		reader.close(); // 关闭输入流
	}

	@Override
	public void run() {
		while (Thread.currentThread() == httpJsonSendThread) {
			try {
				httpPostWithJSON(DataProvide.URL, JsonUtils.MakeJson());
				Thread.sleep(1000);
			} catch (Exception e) {
				Log.e("Farm", "发送失败");
				e.printStackTrace();
			}
		}

	}

	/**
	 * 自定义线程开启方法
	 */
	public void startSendJson() {
		if (httpJsonSendThread == null) {

			httpJsonSendThread = new Thread(this);
			httpJsonSendThread.start();
		}
	}

	/**
	 * 自定义线程关闭方法
	 */
	public void stopThread() {
		if (httpJsonSendThread != null) {
			httpJsonSendThread.stop();
			httpJsonSendThread = null;
		}
	}

}
