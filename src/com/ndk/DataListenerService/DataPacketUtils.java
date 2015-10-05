package com.ndk.DataListenerService;

import com.ndk.utils.DataProvide;
import com.ndk.utils.DataQueue;

public class DataPacketUtils {
	public static void DataPacket() {
		String dataPackage;
		/*
		 * ���ݸ�ʽ����淶�� 1.������������ �� �¶�(W)��ʪ��(S)������(G)��CO2Ũ��(C), ����(Y)[0:���� 1:����]����
		 * ���� �� DW11.2 & DY0 & DS20.5 & DG16.8 & DC17.8 & STO & SWC & SLO &��SFO
		 * & SHC
		 */
		dataPackage = "DW" + DataProvide.AIR_TEMP + "/DS"
				+ DataProvide.SOIL_HUMIDITY + "/DG" + DataProvide.LUX + "/DC"
				+ DataProvide.CO2 + "/DY" + DataProvide.RING;
		dataPackage += "/ST" + DataProvide.WATER_STATE + "/SW"
				+ DataProvide.WINDOWS_STATE + "/SL" + DataProvide.LIGHT_STATE
				+ "/SF" + DataProvide.FAN_STATE + "/SH"
				+ DataProvide.HEATER_STATE;
		DataQueue.getInstance().addElement(dataPackage);
		dataPackage = null;
	}
}
