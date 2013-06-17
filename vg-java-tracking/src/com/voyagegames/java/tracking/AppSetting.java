package com.voyagegames.java.tracking;


/**
* A class for defining a global and/or permanent app setting
*/
public class AppSetting extends KeyValueToJSON {

	/**
	* Creates the AppSetting object
	* @param key   A uniquely identifiable key for tracked data
	* @param value The event data to track
	*/
	public AppSetting(final String key, final String value) {
		super(key, value, "appsetting");
	}

}
