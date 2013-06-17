package com.voyagegames.java.tracking;


/**
 * A custom-defined event for tracking special-purpose events
 */
public class CustomEvent extends KeyValueToJSON {

	/**
	* Creates the CustomEvent object
	* @param key   A uniquely identifiable key for tracked data
	* @param value The event data to track
	*/
	public CustomEvent(final String key, final String value) {
		super(key, value, "customevent");
	}

}
