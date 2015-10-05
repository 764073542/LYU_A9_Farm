package com.ndk.utils;

public class JsonDataClass {
	int Lux;
	int CO2;		
	double AIR_TEMP;
	double SOIL_HUMIDITY;
	int RING;
	int WINDSPEED;
	public int getLux() {
		return Lux;
	}
	public void setLux(int lux) {
		Lux = lux;
	}
	public int getCO2() {
		return CO2;
	}
	public void setCO2(int cO2) {
		CO2 = cO2;
	}
	public double getAIR_TEMP() {
		return AIR_TEMP;
	}
	public void setAIR_TEMP(double aIR_TEMP) {
		AIR_TEMP = aIR_TEMP;
	}
	public double getSOIL_HUMIDITY() {
		return SOIL_HUMIDITY;
	}
	public void setSOIL_HUMIDITY(double sOIL_HUMIDITY) {
		SOIL_HUMIDITY = sOIL_HUMIDITY;
	}
	public int getRING() {
		return RING;
	}
	public void setRING(int rING) {
		RING = rING;
	}
	public int getWINDSPEED() {
		return WINDSPEED;
	}
	public void setWINDSPEED(int wINDSPEED) {
		WINDSPEED = wINDSPEED;
	}
	
}
