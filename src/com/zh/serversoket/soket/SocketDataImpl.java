package com.zh.serversoket.soket;

import java.io.Closeable;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.google.gson.JsonObject;
import com.zh.serversoket.listener.OnSocketlistener;
import com.zh.serversoket.soket.utils.Log;

/**
 * 存放 socket对象的类<br>
 * setOnInfoLisener 设置消息回调<br>
 * getOnInfoLisener 得到消息回调<br>
 * getInstance 获得实例<br>
 * getSoket 得到socket对象通过 KEY<br>
 * setSoket 设置socket对象通过 KEY<br>
 * getSoketMap 得到所有的socket<br>
 * getSize 得到连接个数<br>
 * close 关闭类<br>
 * onSocketMsglistener 得到socket回调消息<br>
 * onSocketDisConnectlistener 得到socket关闭消息<br>
 * containsKey 判断是否是有这个KEY<br>
 * 
 * @author han
 *
 */
public class SocketDataImpl implements OnSocketlistener, Closeable {
	private HashMap<String, SocketMsgThread> mSoketMap = null;
	private static SocketDataImpl mSoketData = null;

	private SocketDataImpl() {
		mSoketMap = new HashMap<String, SocketMsgThread>();
	}

	public static SocketDataImpl getInstance() {
		if (mSoketData == null) {
			synchronized (SocketDataImpl.class) {
				if (mSoketData == null) {
					mSoketData = new SocketDataImpl();
				}
				return mSoketData;
			}
		} else {
			return mSoketData;
		}
	}

	public Socket getSoket(String key) {
		return mSoketMap.get(key).getSocket();
	}

	public void setSoket(String key, Socket socket) {
		SocketMsgThread socketMsgThread = new SocketMsgThread(socket, key, this);
		mSoketMap.put(key, socketMsgThread);
	}

	public Map<String, SocketMsgThread> getSoketMap() {
		return mSoketMap;
	}

	@Override
	public void onSocketDisConnectlistener(String key) {
		mSoketMap.remove(key);
		Log.d("remove:" + key + "\n断开连接:" + key);
	}

	@Override
	public void onSocketMsglistener(String key, String msg) {
		Log.d("key: " + key + "\tmsg:" + msg + "\n");

		JsonObject js = new JsonObject();
		js.addProperty("key", "123");
		js.addProperty("name", "789");
		Log.d(js.toString());
		SocketUtils.sendMsgSoket(getSoket(key), js.toString());
	}

	@Override
	public void close() {
		// throws IOException
		if (mSoketMap != null) {
			if (mSoketMap.size() != 0) {
				Set<String> key = mSoketMap.keySet();
				if (key != null) {
					for (String string : key) {
						SocketMsgThread st = mSoketMap.get(string);
						st.stopMsg();
					}
				}
			}
			mSoketMap.clear();
			mSoketMap = null;
			mSoketData = null;
		}

	}

	public int getSize() {
		return mSoketMap.size();
	}

	/**
	 * 
	 * containsKey <br>
	 * public boolean containsKey(Object key)<br>
	 * <br>
	 * 
	 * Returns true if this map contains a mapping for the specified key.
	 * 
	 * @param key
	 * @return
	 */
	public boolean containsKey(Object key) {
		return mSoketMap.containsKey(key);
	}
}
