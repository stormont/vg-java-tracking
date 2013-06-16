package com.voyagegames.java.tracking.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.voyagegames.java.tracking.KeyValue;

public class KeyValueTest {

	@Test
	public void testKeyValue() {
		final long start = System.currentTimeMillis();
		final KeyValue kv = new KeyValue("testkey", "testvalue");
		final long end = System.currentTimeMillis();
		
		assertTrue(kv.key.contentEquals("testkey"));
		assertTrue(kv.value.contentEquals("testvalue"));
		assertTrue(kv.timestamp >= start);
		assertTrue(kv.timestamp <= end);
	}

}
