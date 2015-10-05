package com.ndk.utils;


/**
 * 最简单的解决方案是不接收状态数据，如果想要显示状态可以直接判断数据节点的数据是否为0
 * 
 * @author 傲娇小笼包
 * 
 */
public class SerialDataUtils {
	// 数据解析服务
	/**
	 * 数据格式： 0A 02 00 6B 00 00 00 0x0A：数据开始标记 0x02：ZigBee节点编号，代表光照强度节点 0x6B
	 * 0x00:光照强度数据
	 */
	static char[] stemp = new char[5];
	static char[] shum = new char[5];
	static char[] temp = new char[5];
	static char[] hum = new char[5];

	public static void CheckInfo(String info) {	
		char temp = '0';
		char[] buf = info.toCharArray();
//		System.out.println("HexString");
//		for (int i = 0; i < buf.length; i++) {
//			System.out.print(Integer.toHexString(buf[i]) + "  ");
//		}
		/**
		 * 只获取数据节点的数据，而不获取所有节点的状态
		 */
		if (7 == buf.length) { // 数据分组
			temp = buf[1];

			switch (temp) {
			case 0x01:
				GetCO2Density_Process(buf); // 0x01表示co2浓度
				break;
			case 0x02:
				GetLightIntensity_Process(buf); // 0x02表示光照强度
				break;
			case 0x03:
				GetWindSpeed_Process(buf); // 0x03表示风速
				break;
			case 0x04:
				GetRainSnowStatus_Process(buf); // 0x04表示雨雪
				break;
			case 0x05:
				GetTempHum_Process(buf); // 0x05表示空气温湿度
				break;
			case 0x06:
				GetSoilTempHum_Process(buf); // 0x06表示土壤湿度
				break;
			case 0x07:
				break;
			default:						
				break;
			}
		} 
	}

	// 获取土壤温湿度信息
	private static void GetSoilTempHum_Process(char[] buf) {
		getsht11(buf, stemp, shum);
	}

	// 获取空气温湿度信息
	private static void GetTempHum_Process(char[] buf) {
		getsht11(buf, temp, hum);

	}

	// 获取雨雪数据
	private static void GetRainSnowStatus_Process(char[] buf) {
		DataProvide.RING = buf[3] & 0x00FF;
	}

	// 获取风速数据
	private static void GetWindSpeed_Process(char[] buf) {
		DataProvide.WINDSPEED = ((buf[2] & 0x00FF) * 256 + (buf[3] & 0x00FF)) / 100;
	}

	// 获取获取光照数据
	private static void GetLightIntensity_Process(char[] buf) {
		DataProvide.LUX = ((buf[2] & 0x00FF) * 256 + (buf[3] & 0x00FF)) * 10;		
	}

	// 获取CO2数据
	private static void GetCO2Density_Process(char[] buf) {
		DataProvide.CO2 = (buf[2] & 0x00FF) * 256 + (buf[3] & 0x00FF);		
	}
	//全局变量，代替地址传递
	private static float temperature;
	private static float humidity;
	/**
	 * 温湿度计算的相关算法
	 */
	private static void getsht11(char[] buf, char[] stemp2, char[] shum2) {
		int i = 0, j = 0;
		float Temp = 0, Humidity = 0;
		int calcret = 0;

		i = (buf[3] & 0x00FF) * 256 + (buf[2] & 0x00FF);
		j = (buf[5] & 0x00FF) * 256 + (buf[4] & 0x00FF);

		temperature = (float) j;
		humidity = (float) i;
		calcret = calc_sth11();
		if (((int) temperature) > 0 && 0 <= humidity && humidity <= 100
				&& calcret != 0) {
			Temp = Float.parseFloat((int) temperature + "."
					+ (int) ((temperature - (int) temperature) * 10));// 温度
			Humidity = Float.parseFloat((int) humidity + "."
					+ (int) ((humidity - (int) humidity) * 10));// 湿度

		}
		else{
			return;
		}
		if (buf[1] == 0x05) { // 空气温湿度
			DataProvide.AIR_TEMP = Temp;
			DataProvide.AIR_HUMIDITY = Humidity;		

		} else if (buf[1] == 0x06) { // 土壤温湿度
			DataProvide.SOIL_TEMP = Temp;
			DataProvide.SOIL_HUMIDITY = Humidity;
		}

	}

	// 温湿度数据校验
	private static int calc_sth11() {
		int ret = 0;

		float C1 = -4.0f; // for 12 Bit
		float C2 = 0.0405f; // for 12 Bit
		float C3 = -0.0000028f; // for 12 Bit
		float T1 = 0.01f; // for 14 Bit @ 5V
		float T2 = 0.00008f; // for 14 Bit @ 5V

		float rh = humidity; // rh: Humidity [Ticks] 12 Bit
		float t = temperature; // t: Temperature [Ticks] 14 Bit
		float rh_lin; // rh_lin: Humidity linear
		float rh_true; // rh_true: Temperature compensated humidity
		float t_C; // t_C : Temperature [C]
		t_C = (float) (t * 0.01 - 42); // calc. Temperature from ticks to [C]
		rh_lin = C3 * rh * rh + C2 * rh + C1; // calc. Humidity from ticks to
												// [%RH]
		rh_true = (t_C - 25) * (T1 + T2 * rh) + rh_lin; // calc. Temperature
														// compensated humidity
														// [%RH]
		if (rh_true > 100)
			rh_true = 100; // cut if the value is outside of
		if (rh_true < 0.1)
			rh_true = 0.1f; // the physical possible range
		temperature = t_C; // return temperature [C]
		humidity = rh_true; // return humidity[%RH]

		if (t_C > 0)
			return ret = 1;
		return ret;
	}

}
