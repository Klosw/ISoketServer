package com.zh.serversoket.soket;

import java.io.IOException;
import java.net.Socket;

import com.zh.serversoket.listener.OnSocketlistener;
import com.zh.serversoket.soket.utils.Log;

public class SocketMsgThread extends Thread {
	private Socket mSocket;
	private String mkey;
	private OnSocketlistener mOnSocketlistener;
	private boolean isRun = true;

	public SocketMsgThread(Socket socket, String key, OnSocketlistener onSocketlistener) {
		mSocket = socket;
		// mkey = SocketUtils.getSoketHostName(mSocket);
		mkey = key;
		mOnSocketlistener = onSocketlistener;
		this.start();
	}

	public void stopMsg() {
		try {
			if (mSocket != null)
				mSocket.close();
			isRun = false;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		super.run();
		while (isRun) {
			try {
				String str = SocketUtils.getMsgSoket1(mSocket);
				if (mOnSocketlistener != null) {
					mOnSocketlistener.onSocketMsglistener(mkey, str);
				}
			} catch (IOException e) {
				if (mOnSocketlistener != null) {
					mOnSocketlistener.onSocketDisConnectlistener(mkey);
				}
				isRun = false;
				Log.e(e.toString());
			}
		}
	}

	public Socket getSocket() {
		return mSocket;
	}
}
