package com.zh.serversoket.soket.demo;

import java.io.Serializable;

public class HMessage implements Serializable {
	private static final long serialVersionUID = -1871955703126910506L;

	/**
	 * User-defined message code so that the recipient can identify what this
	 * message is about. Each {@link Handler} has its own name-space for message
	 * codes, so you do not need to worry about yours conflicting with other
	 * handlers.
	 */
	public int what;

	/**
	 * arg1 and arg2 are lower-cost alternatives to using
	 * {@link #setData(Bundle) setData()} if you only need to store a few
	 * integer values.
	 */
	public int arg1;

	/**
	 * arg1 and arg2 are lower-cost alternatives to using
	 * {@link #setData(Bundle) setData()} if you only need to store a few
	 * integer values.
	 */
	public int arg2;

	/**
	 * An arbitrary object to send to the recipient. When using
	 * {@link Messenger} to send the message across processes this can only be
	 * non-null if it contains a Parcelable of a framework class (not one
	 * implemented by the application). For other data transfer use
	 * {@link #setData}.
	 * 
	 * <p>
	 * Note that Parcelable objects here are not supported prior to the
	 * {@link android.os.Build.VERSION_CODES#FROYO} release.
	 */
	public Object obj;

	public HMessage() {
	}

	HMessage next;

	private static final Object sPoolSync = new Object();

	private static HMessage sPool;
	private static int sPoolSize = 0;

	private static final int MAX_POOL_SIZE = 50;

	/**
	 * Return a new Message instance from the global pool. Allows us to avoid
	 * allocating new objects in many cases.
	 */
	public static HMessage obtain() {
		synchronized (sPoolSync) {
			if (sPool != null) {
				HMessage m = sPool;
				sPool = m.next;
				m.next = null;
				sPoolSize--;
				return m;
			}
		}
		return new HMessage();
	}

	/**
	 * Same as {@link #obtain()}, but copies the values of an existing message
	 * (including its target) into the new one.
	 * 
	 * @param orig
	 *            Original message to copy.
	 * @return A Message object from the global pool.
	 */
	public static HMessage obtain(HMessage orig) {
		HMessage m = obtain();
		m.what = orig.what;
		m.arg1 = orig.arg1;
		m.arg2 = orig.arg2;
		m.obj = orig.obj;

		return m;
	}

	/**
	 * Return a Message instance to the global pool. You MUST NOT touch the
	 * Message after calling this function -- it has effectively been freed.
	 */
	public void recycle() {
		clearForRecycle();

		synchronized (sPoolSync) {
			if (sPoolSize < MAX_POOL_SIZE) {
				next = sPool;
				sPool = this;
				sPoolSize++;
			}
		}
	}

	void clearForRecycle() {
	}
}
