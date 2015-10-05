package com.ndk.utils;


/**
 * ��򵥵Ľ�������ǲ�����״̬���ݣ������Ҫ��ʾ״̬����ֱ���ж����ݽڵ�������Ƿ�Ϊ0
 * 
 * @author ����С����
 * 
 */
public class SerialDataUtils {
	// ���ݽ�������
	/**
	 * ���ݸ�ʽ�� 0A 02 00 6B 00 00 00 0x0A�����ݿ�ʼ��� 0x02��ZigBee�ڵ��ţ��������ǿ�Ƚڵ� 0x6B
	 * 0x00:����ǿ������
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
		 * ֻ��ȡ���ݽڵ�����ݣ�������ȡ���нڵ��״̬
		 */
		if (7 == buf.length) { // ���ݷ���
			temp = buf[1];

			switch (temp) {
			case 0x01:
				GetCO2Density_Process(buf); // 0x01��ʾco2Ũ��
				break;
			case 0x02:
				GetLightIntensity_Process(buf); // 0x02��ʾ����ǿ��
				break;
			case 0x03:
				GetWindSpeed_Process(buf); // 0x03��ʾ����
				break;
			case 0x04:
				GetRainSnowStatus_Process(buf); // 0x04��ʾ��ѩ
				break;
			case 0x05:
				GetTempHum_Process(buf); // 0x05��ʾ������ʪ��
				break;
			case 0x06:
				GetSoilTempHum_Process(buf); // 0x06��ʾ����ʪ��
				break;
			case 0x07:
				break;
			default:						
				break;
			}
		} 
	}

	// ��ȡ������ʪ����Ϣ
	private static void GetSoilTempHum_Process(char[] buf) {
		getsht11(buf, stemp, shum);
	}

	// ��ȡ������ʪ����Ϣ
	private static void GetTempHum_Process(char[] buf) {
		getsht11(buf, temp, hum);

	}

	// ��ȡ��ѩ����
	private static void GetRainSnowStatus_Process(char[] buf) {
		DataProvide.RING = buf[3] & 0x00FF;
	}

	// ��ȡ��������
	private static void GetWindSpeed_Process(char[] buf) {
		DataProvide.WINDSPEED = ((buf[2] & 0x00FF) * 256 + (buf[3] & 0x00FF)) / 100;
	}

	// ��ȡ��ȡ��������
	private static void GetLightIntensity_Process(char[] buf) {
		DataProvide.LUX = ((buf[2] & 0x00FF) * 256 + (buf[3] & 0x00FF)) * 10;		
	}

	// ��ȡCO2����
	private static void GetCO2Density_Process(char[] buf) {
		DataProvide.CO2 = (buf[2] & 0x00FF) * 256 + (buf[3] & 0x00FF);		
	}
	//ȫ�ֱ����������ַ����
	private static float temperature;
	private static float humidity;
	/**
	 * ��ʪ�ȼ��������㷨
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
					+ (int) ((temperature - (int) temperature) * 10));// �¶�
			Humidity = Float.parseFloat((int) humidity + "."
					+ (int) ((humidity - (int) humidity) * 10));// ʪ��

		}
		else{
			return;
		}
		if (buf[1] == 0x05) { // ������ʪ��
			DataProvide.AIR_TEMP = Temp;
			DataProvide.AIR_HUMIDITY = Humidity;		

		} else if (buf[1] == 0x06) { // ������ʪ��
			DataProvide.SOIL_TEMP = Temp;
			DataProvide.SOIL_HUMIDITY = Humidity;
		}

	}

	// ��ʪ������У��
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
