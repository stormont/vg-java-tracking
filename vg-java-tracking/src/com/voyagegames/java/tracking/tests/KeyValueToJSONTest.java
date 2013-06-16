package com.voyagegames.java.tracking.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.voyagegames.java.tracking.KeyValueToJSON;

public class KeyValueToJSONTest {
	
	private class TestKeyValueToJSON extends KeyValueToJSON {

		public TestKeyValueToJSON(final String key, final String value) {
			super(key, value, "testkeyvaluetojson");
		}
		
	}

	@Test
	public void testAppSetting() {
		final long start = System.currentTimeMillis();
		final TestKeyValueToJSON data = new TestKeyValueToJSON("testkey", "testvalue");
		final long end = System.currentTimeMillis();
		
		assertTrue(data.key.contentEquals("testkey"));
		assertTrue(data.value.contentEquals("testvalue"));
		assertTrue(data.timestamp >= start);
		assertTrue(data.timestamp <= end);
	}

	@Test
	public void testToJSON() {
		final TestKeyValueToJSON data = new TestKeyValueToJSON("testkey", "testvalue");
		final String json = data.toJSON();
		
		assertTrue(json.contentEquals("{\"testkeyvaluetojson\":{\"timestamp\":" + data.timestamp + ",\"id\":\"testkey\",\"value\":\"testvalue\"}}"));
	}

}
