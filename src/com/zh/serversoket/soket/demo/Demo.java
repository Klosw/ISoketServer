package com.zh.serversoket.soket.demo;

import com.zh.serversoket.soket.utils.Log;

public class Demo {
	int i = 0;
	HMessage mmmmm;

	public Demo() {
		Log.d(123 + "");
		Integer i1 = 100;
		Integer i2 = 100;
		Integer i3 = 200;
		Integer i4 = 200;
		Log.d((i1 == i2) + "");

		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					HMessage hessage = null;

					hessage = new HMessage();
					hessage.recycle();
					i++;
					try {
						Thread.sleep(1 * 1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		// thread.start();
	}
}
