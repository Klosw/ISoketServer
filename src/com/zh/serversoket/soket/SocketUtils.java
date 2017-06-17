package com.zh.serversoket.soket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketUtils {
//	/** 3次握手 */
//	public static String isConnect(Socket socket) {
//		String str = getMsgSoket(socket);
//		if (str != null) {
//			sendMsgSoket(socket, "OK");
//			return str;
//		} else {
//			return null;
//		}
//	}

//	/** 得到IP地址 */
//	public static String getSoketHostName(Socket socket) {
//		if (socket != null && socket.isConnected()) {
//			return socket.getInetAddress().getHostName();
//		}
//		return null;
//	}

	/**
	 * 得到客服端返回数据
	 * 
	 * @throws IOException
	 */
	public static String getMsgSoket1(Socket socket) throws IOException {
		if (socket != null && socket.isConnected()) {
			InputStream in = socket.getInputStream();
			DataInputStream din = new DataInputStream(in);
			String name = din.readUTF();
			return name;
		}
		return null;

	}

	/** 得到客服端返回数据 */
	public static String getMsgSoket(Socket socket) {
		if (socket != null && socket.isConnected()) {
			// socket.getLocalAddress().getHostAddress()
			InputStream in = null;
			DataInputStream din = null;
			try {
				in = socket.getInputStream();
				din = new DataInputStream(in);
				String name = din.readUTF();
				return name;
			} catch (IOException e) {
				try {
					if (din != null)
						din.close();
					if (in != null)
						in.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
		} else {
			System.err.println("Soket not's Connect!");
		}
		return null;
	}

	/** 发送消息 */
	public static boolean sendMsgSoket(Socket soket, String msg) {
		if (soket != null && soket.isConnected()) {
			OutputStream out = null;
			DataOutputStream dos = null;
			try {
				out = soket.getOutputStream();
				dos = new DataOutputStream(out);
				dos.writeUTF(msg);
				out.flush();
				return true;
			} catch (IOException e) {
				try {
					if (dos != null)
						dos.close();
					if (out != null)
						out.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
		} else {
			System.err.println("Soket not's Connect!");
		}
		return false;
	}
}
