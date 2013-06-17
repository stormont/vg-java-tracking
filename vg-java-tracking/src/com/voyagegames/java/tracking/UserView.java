package com.voyagegames.java.tracking;


/**
 * A class for tracking data about user viewing of elements
 */
public class UserView extends KeyValueToJSON {

	/**
	* Creates the UserView object
	* @param key   A uniquely identifiable key for tracked data
	* @param value The event data to track
	*/
	public UserView(final String key, final String value) {
		super(key, value, "userview");
	}

}
