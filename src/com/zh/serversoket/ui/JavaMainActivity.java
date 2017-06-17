package com.zh.serversoket.ui;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import com.zh.serversoket.listener.OnWindowListener;
import com.zh.serversoket.soket.SocketThread;
import com.zh.serversoket.soket.utils.Log;
import com.zh.serversoket.ui.InfoLisener.OnInfoLisener;

public class JavaMainActivity implements OnInfoLisener, OnWindowListener {
	public JavaMainActivity() {
		init();
	}

	private JavaMainView mJavaView;
	// private SocketServerT serverT;
	private SocketThread mSoketServer;

	public void init() {
		Log.setInfo(this);
		Log.d("开启程序");
		mJavaView = new JavaMainView();
		mJavaView.shows();
		Log.d("\n" + getNet());

		mSoketServer = new SocketThread();
		mSoketServer.start();

		mJavaView.setOnWindowListener(this);

	}

	private String getNet() {
		String string = "";
		Enumeration<NetworkInterface> interfs = null;
		try {
			interfs = NetworkInterface.getNetworkInterfaces();
		} catch (SocketException e) {
			e.printStackTrace();
		}
		while (interfs.hasMoreElements()) {
			NetworkInterface interf = interfs.nextElement();
			Enumeration<InetAddress> addres = interf.getInetAddresses();
			while (addres.hasMoreElements()) {
				InetAddress in = addres.nextElement();
				if (in instanceof Inet4Address) {
					// Log.d("IP v4:" + in.getHostAddress());
					string = in.getHostAddress() + "\n" + string;
				} else if (in instanceof Inet6Address) {
					// Log.d("v6:" + in.getHostAddress());
				}
			}
		}
		return string;

	}

	@Override
	public void onInfo(String string) {
		if (mJavaView != null)
			mJavaView.append(string);
	}

	@Override
	public void onError(String string) {

	}

	@Override
	public boolean onClosed() {
		// javaMain.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		// javaMain.setText("123456");
		// serverT.stopServerSocket();
		if (mSoketServer != null) {
			mSoketServer.stopServer();
		}
		Log.d("关闭程序");
		System.exit(0);
		return false;
	}

}
