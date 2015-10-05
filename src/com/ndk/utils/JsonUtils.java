package com.ndk.utils;

import android.util.Log;

import com.google.gson.Gson;

public class JsonUtils {
	
	public static String MakeJson() {

		Gson gson = new Gson();
		JsonDataClass dataValue = new JsonDataClass();
		dataValue.setAIR_TEMP(DataProvide.AIR_TEMP);
		dataValue.setLux(DataProvide.LUX);
		dataValue.setRING(DataProvide.RING);
		dataValue.setSOIL_HUMIDITY(DataProvide.SOIL_HUMIDITY);
		dataValue.setWINDSPEED(DataProvide.WINDSPEED);
		try {
			String json = gson.toJson(dataValue);
			Log.i("Farm", json);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public static JsonDataClass CheckJson(String json) {
		try {
			
			Gson gson = new Gson();				
			JsonDataClass jData;
			jData = gson.fromJson(json, JsonDataClass.class);			

			return jData;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
