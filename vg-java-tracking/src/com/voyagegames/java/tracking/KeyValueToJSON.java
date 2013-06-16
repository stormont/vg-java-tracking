package com.voyagegames.java.tracking;

import org.codehaus.jettison.json.JSONObject;

public abstract class KeyValueToJSON extends KeyValue implements IToJSON {
	
	public final String type;

	public KeyValueToJSON(final String key, final String value, final String type) {
		super(key, value);
		this.type = type;
	}

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
