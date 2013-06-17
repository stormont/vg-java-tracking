package com.voyagegames.java.tracking;

/**
 * A class for tracking time-stamped key/value data
 */
public class KeyValue {
	
	/**
	 * The timestamp at which this key/value object was created
	 */
	public final long timestamp;
	/**
	 * The unique key for this data
	 */
	public final String key;
	/**
	 * The data of interest to track
	 */
	public final String value;
	
	/**
	 * Creates the key/value paired data
	 * @param key   The unique key for the data
	 * @param value The value to track
	 */
	public KeyValue(final String key, final String value) {
		this.timestamp = System.currentTimeMillis();
		this.key = key;
		this.value = value;
	}

}
