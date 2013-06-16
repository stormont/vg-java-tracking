package com.voyagegames.java.tracking;


public class CustomEvent extends KeyValueToJSON {

	public CustomEvent(final String key, final String value) {
		super(key, value, "customevent");
	}

}
