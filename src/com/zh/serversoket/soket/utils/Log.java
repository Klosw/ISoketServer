package com.zh.serversoket.soket.utils;

import com.zh.serversoket.ui.InfoLisener.OnInfoLisener;

public class Log {
	private static String Tag = "LOG";
	private static OnInfoLisener mInfo;

	public static OnInfoLisener getInfo() {
		return mInfo;
	}

	public static void setInfo(OnInfoLisener mInfo) {
		Log.mInfo = mInfo;
	}

	public static void d(String Tag, String msg) {
		System.out.println(Tag + "\t: " + msg);
		if (mInfo != null) {
			mInfo.onInfo(Tag + "\t: " + msg);
		}
	}

	public static void d(String msg) {
		System.out.println(Tag + "\t: " + msg);
		if (mInfo != null) {
			mInfo.onInfo(Tag + "\t: " + msg);
		}
	}

	public static void e(String Tag, String msg) {
		System.err.println(Tag + "\t: " + msg);
		if (mInfo != null) {
			mInfo.onError(Tag + "\t: " + msg);
		}
	}

	public static void e(String msg) {
		System.err.println(Tag + "\t: " + msg);
		if (mInfo != null) {
			mInfo.onError(Tag + "\t: " + msg);
		}
	}
}
