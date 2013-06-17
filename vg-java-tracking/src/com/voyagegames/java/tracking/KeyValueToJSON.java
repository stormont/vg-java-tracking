package com.voyagegames.java.tracking;

import org.codehaus.jettison.json.JSONObject;


/**
 * An abstract class for creating trackable data and converting to JSON format
 */
public abstract class KeyValueToJSON extends KeyValue implements IToJSON {
	
	/**
	 * The type of the key/value pair
	 */
	public final String type;

	/**
	 * Creates the key/value paired data
	 * @param key   The unique key for the data
	 * @param value The value to track
	 * @param type  The type of the key/value pair
	 */
	public KeyValueToJSON(final String key, final String value, final String type) {
		super(key, value);
		this.type = type;
	}

	/**
	 * Converts the object data to well-formatted JSON
	 * @return The JSON representation of the object
	 */
	@Override
	public String toJSON() {
		final StringBuilder sb = new StringBuilder();
		
		sb.append("{" + JSONObject.quote(this.type) + ":");
		sb.append("{\"timestamp\":" + this.timestamp);
		sb.append(",\"id\":" + JSONObject.quote(this.key));
		sb.append(",\"value\":" + JSONObject.quote(this.value));
		sb.append("}}");
		
		return sb.toString();
	}

}
