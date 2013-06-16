package com.voyagegames.java.tracking;

public class KeyValue {
	
	public final long timestamp;
	public final String key;
	public final String value;
	
	public KeyValue(final String key, final String value) {
		this.timestamp = System.currentTimeMillis();
		this.key = key;
		this.value = value;
	}

}
