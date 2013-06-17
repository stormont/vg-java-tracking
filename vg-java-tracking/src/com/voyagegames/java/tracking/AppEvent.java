package com.voyagegames.java.tracking;


/**
* A class for defining a trackable app lifecycle event
*/
public class AppEvent extends KeyValueToJSON {

	/**
	* Creates the AppEvent object
	* @param key   A uniquely identifiable key for tracked data
	* @param value The event data to track
	*/
	public AppEvent(final String key, final String value) {
		super(key, value, "appevent");
	}

}
