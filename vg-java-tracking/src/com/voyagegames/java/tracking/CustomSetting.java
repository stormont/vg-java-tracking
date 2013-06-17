package com.voyagegames.java.tracking;


/**
 * A custom-defined setting for tracking special-purpose settings
 */
public class CustomSetting extends KeyValueToJSON {

	/**
	* Creates the CustomSetting object
	* @param key   A uniquely identifiable key for tracked data
	* @param value The event data to track
	*/
	public CustomSetting(final String key, final String value) {
		super(key, value, "customsetting");
	}

}
