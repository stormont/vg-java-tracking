package com.voyagegames.java.tracking;

import org.codehaus.jettison.json.JSONObject;

public class AppSetting extends KeyValue implements IToJSON {

	public AppSetting(final String key, String value) {
		super(key, value);
	}

	@Override
	public String toJSON() {
		final StringBuilder sb = new StringBuilder();
		
		sb.append("{\"appsetting\":");
		sb.append("{\"timestamp\":" + this.timestamp);
		sb.append(",\"id\":" + JSONObject.quote(this.key));
		sb.append(",\"value\":" + JSONObject.quote(this.value));
		sb.append("}}");
		
		return sb.toString();
	}

}
