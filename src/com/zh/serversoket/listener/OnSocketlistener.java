package com.zh.serversoket.listener;

public interface OnSocketlistener {
	/**
	 * 断开连接
	 * 
	 * @param key
	 */
	void onSocketDisConnectlistener(String key);

	/**
	 * 来自key的msg消息
	 * 
	 * @param key
	 * @param msg
	 */
	void onSocketMsglistener(String key, String msg);
}
