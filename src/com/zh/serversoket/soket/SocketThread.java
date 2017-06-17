package com.zh.serversoket.soket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.zh.serversoket.soket.utils.Log;

/**
 * 处理 服务器 开启关闭的类<br>
 * stopServer 停止服务器<br>
 * setOnInfoLisener 设置消息回调<br>
 * start 开起线程,启动服务器的意思<br>
 * 
 * @author han
 *
 */
public class SocketThread extends Thread {
	// Service对象
	private ServerSocket mserverSocket;
	// 消息处理类
	private SocketDataImpl mDataImpl;
	// 状态
	private boolean operational = true;
	// 端口号
	private final static int mPROT = 7891;

	public SocketThread() {
		try {
			mserverSocket = new ServerSocket(mPROT);
			mDataImpl = SocketDataImpl.getInstance();
		} catch (IOException e) {
			Log.d("ServerSocket 创建失败");
			e.printStackTrace();
		}
	}

	// 停止服务器
	public void stopServer() {
		operational = false;
		if (mserverSocket != null && mserverSocket.isClosed()) {
			try {
				mserverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		mserverSocket = null;
	}

	@Override
	public void run() {
		super.run();
		while (operational) {
			try {
				Socket socket = mserverSocket.accept();
				// String str = SocketUtils.isConnect(socket);
				String str = SocketUtils.getMsgSoket(socket);
				// sendMsgSoket(socket, "OK");
				if (str != null) {

					if (!mDataImpl.containsKey(str)) {
						mDataImpl.setSoket(str, socket);
						SocketUtils.sendMsgSoket(socket, "OK");
						Log.d("新的连接:" + str);
					} else {
						SocketUtils.sendMsgSoket(socket, "昵称已经存在了!");
					}
				}
				System.err.println(mDataImpl.getSize());
			} catch (IOException e) {
				mDataImpl.close();
				operational = false;
				e.printStackTrace();
			}
		}
	}

}
