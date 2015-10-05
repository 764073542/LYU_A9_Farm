package com.ndk.utils;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.ndk.Constant.NetCommand;

/**
 * ���õ������ģʽ
 * 
 * @author ����С����
 * 
 */
public class DataQueue {
	List<String> dataList = new ArrayList<String>();
	private final static DataQueue dataQueue = new DataQueue();

	/**
	 * ˽�л����캯��
	 */
	private DataQueue() {

	}

	/**
	 * �����ṩ�����ȡ����
	 * 
	 * @return
	 */
	public static DataQueue getInstance() {
		return dataQueue;
	}

	/**
	 * ���Ԫ�ط���
	 * 
	 * @param str
	 *            boolean add(E e)��ָ����Ԫ����ӵ����б��β����
	 */
	public void addElement(String str) {
		// Log.i("Farm", "���г���Ϊ��" + dataList.size());
		/* �жϵ�ǰ������� */
		// ���Խ���Ҫ���͵�������ӵ����ף����ȷ��ͣ��ڶ��й�������������
		// l>5,���
		if (dataList.size() > 10) {
			clearQueue();
		}
		/**
		 * רҵһ��ʹ���һ���࣬�����ַ���ֵ�������ݰ�����������ʱ��
		 */

		dataList.add(str);
	}

	/**
	 * ɾ�����һ��Ԫ��
	 */
	private void deleteLastElement() {
		dataList.remove(dataList.size() - 1);
	}

	/**
	 * ɾ�����е�һ��Ԫ�ط��� E remove(int index) �Ƴ����б���ָ��λ���ϵ�Ԫ�ء�
	 */
	public void deleteElement() {
		if (dataList.size() > 1) {
			dataList.remove(dataList.size() - 1);
		}

	}

	/**
	 * ��ն��з��� void clear() �Ƴ����б��е�����Ԫ�ء�
	 */
	public void clearQueue() {
		dataList.clear();
	}

	/**
	 * ���ط������ݺ���������0��Ԫ�ص��ַ�ֵ
	 */
	public String getValue() {
		try {
			return dataList.get(0);
		} catch (Exception e) {
			return NetCommand.NULL;
		}
	}

	/**
	 * �����б�ĳ���
	 * 
	 * @return
	 */
	public int getSize() {
		return dataList.size();
	}
}
