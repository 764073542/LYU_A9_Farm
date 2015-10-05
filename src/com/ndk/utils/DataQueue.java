package com.ndk.utils;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.ndk.Constant.NetCommand;

/**
 * 采用单例设计模式
 * 
 * @author 傲娇小笼包
 * 
 */
public class DataQueue {
	List<String> dataList = new ArrayList<String>();
	private final static DataQueue dataQueue = new DataQueue();

	/**
	 * 私有化构造函数
	 */
	private DataQueue() {

	}

	/**
	 * 对外提供对象获取方法
	 * 
	 * @return
	 */
	public static DataQueue getInstance() {
		return dataQueue;
	}

	/**
	 * 添加元素方法
	 * 
	 * @param str
	 *            boolean add(E e)将指定的元素添加到此列表的尾部。
	 */
	public void addElement(String str) {
		// Log.i("Farm", "队列长度为：" + dataList.size());
		/* 判断当前队列情况 */
		// 可以讲需要发送的数据添加到队首，优先发送，在队列过长，清空再添加
		// l>5,清空
		if (dataList.size() > 10) {
			clearQueue();
		}
		/**
		 * 专业一点就创建一个类，包含字符串值，和数据包（对象）生存时间
		 */

		dataList.add(str);
	}

	/**
	 * 删除最后一个元素
	 */
	private void deleteLastElement() {
		dataList.remove(dataList.size() - 1);
	}

	/**
	 * 删除队列第一个元素方法 E remove(int index) 移除此列表中指定位置上的元素。
	 */
	public void deleteElement() {
		if (dataList.size() > 1) {
			dataList.remove(dataList.size() - 1);
		}

	}

	/**
	 * 清空队列方法 void clear() 移除此列表中的所有元素。
	 */
	public void clearQueue() {
		dataList.clear();
	}

	/**
	 * 返回发送数据函数，即第0个元素的字符值
	 */
	public String getValue() {
		try {
			return dataList.get(0);
		} catch (Exception e) {
			return NetCommand.NULL;
		}
	}

	/**
	 * 返回列表的长度
	 * 
	 * @return
	 */
	public int getSize() {
		return dataList.size();
	}
}
