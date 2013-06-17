package com.voyagegames.java.tracking;


/**
 * A class for tracking specific user actions
 */
public class UserAction extends KeyValueToJSON {

	/**
	* Creates the UserAction object
	* @param key   A uniquely identifiable key for tracked data
	* @param value The event data to track
	*/
	public UserAction(final String key, final String value) {
		super(key, value, "useraction");
	}

}
