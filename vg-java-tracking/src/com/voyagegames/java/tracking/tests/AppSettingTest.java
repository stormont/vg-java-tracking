package com.voyagegames.java.tracking.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.voyagegames.java.tracking.AppSetting;

public class AppSettingTest {

	@Test
	public void testAppSetting() {
		final long start = System.currentTimeMillis();
		final AppSetting as = new AppSetting("testkey", "testvalue");
		final long end = System.currentTimeMillis();
		
		assertTrue(as.key.contentEquals("testkey"));
		assertTrue(as.value.contentEquals("testvalue"));
		assertTrue(as.timestamp >= start);
		assertTrue(as.timestamp <= end);
	}

	@Test
	public void testToJSON() {
		final AppSetting as = new AppSetting("testkey", "testvalue");
		final String json = as.toJSON();
		
		assertTrue(json.contentEquals("{\"appsetting\":{\"timestamp\":" + as.timestamp + ",\"id\":\"testkey\",\"value\":\"testvalue\"}}"));
	}

}
