package com.voyagegames.java.tracking;


public class AppEvent extends KeyValueToJSON {

	public AppEvent(final String key, final String value) {
		super(key, value, "appevent");
	}

}
