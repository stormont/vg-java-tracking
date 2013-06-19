package com.voyagegames.java.tracking.tests;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

import com.voyagegames.java.tracking.AppEvent;
import com.voyagegames.java.tracking.BasicAccumulator;
import com.voyagegames.java.tracking.IDispatchCallback;
import com.voyagegames.java.tracking.JsonDispatcher;
import com.voyagegames.java.tracking.JsonDispatcherConfig;

public class JsonDispatcherTest {
	
	private class TestDispatchCallback implements IDispatchCallback {
		
		public byte[] output;
		public boolean errorCalled = false;

		@Override
		public void debugTrack(byte[] trackingOutput) {
			output = trackingOutput;
		}

		@Override
		public void error(String error) {
			errorCalled = true;
		}
		
	}

	@Test
	public void testDispatch() {
		final JsonDispatcherConfig config = new JsonDispatcherConfig();
		config.callback = new TestDispatchCallback();
		
		final JsonDispatcher disp = new JsonDispatcher(config);

		assertTrue(!disp.dispatch());
	}

	@Test
	public void testDispatchIAccumulator() {
		final BasicAccumulator acc = new BasicAccumulator();
		final JsonDispatcherConfig config = new JsonDispatcherConfig();
		final TestDispatchCallback callback = new TestDispatchCallback();
		final AppEvent event = new AppEvent("testkey", "testvalue");

		acc.trackAppEvent(event);
		config.callback = callback;
		config.url = "testurl";
		config.debugMode = true;
		
		final JsonDispatcher disp = new JsonDispatcher(config);
		
		assertTrue(!callback.errorCalled);
		assertTrue(null == callback.output);
		assertTrue(disp.dispatch(acc));
		assertTrue(null != callback.output);
		
		final String output = "{\"data\":[" + event.toJSON() + "]}";
		assertTrue(output.length() > callback.output.length);
	}

	@Test
	public void testDispatchIAccumulator_noCompression() {
		final BasicAccumulator acc = new BasicAccumulator();
		final JsonDispatcherConfig config = new JsonDispatcherConfig();
		final TestDispatchCallback callback = new TestDispatchCallback();
		final AppEvent event = new AppEvent("testkey", "testvalue");

		acc.trackAppEvent(event);
		config.callback = callback;
		config.url = "testurl";
		config.debugMode = true;
		config.useCompression = false;
		
		final JsonDispatcher disp = new JsonDispatcher(config);
		
		assertTrue(!callback.errorCalled);
		assertTrue(null == callback.output);
		assertTrue(disp.dispatch(acc));
		assertTrue(!callback.errorCalled);
		
		try {
			final String output = "{\"data\":[" + event.toJSON() + "]}";
			assertArrayEquals(output.getBytes("UTF-8"), callback.output);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void testDispatchIAccumulator_withHeader() {
		final BasicAccumulator acc = new BasicAccumulator();
		final JsonDispatcherConfig config = new JsonDispatcherConfig();
		final TestDispatchCallback callback = new TestDispatchCallback();
		final AppEvent header = new AppEvent("testheader", "testheadervalue");
		final AppEvent event = new AppEvent("testkey", "testvalue");

		acc.trackAppEvent(event);
		config.callback = callback;
		config.url = "testurl";
		config.debugMode = true;
		config.useCompression = false;
		config.trackingHeader.add(header);
		
		final JsonDispatcher disp = new JsonDispatcher(config);
		
		assertTrue(!callback.errorCalled);
		assertTrue(null == callback.output);
		assertTrue(disp.dispatch(acc));
		assertTrue(!callback.errorCalled);
		
		try {
			final String output = "{\"data\":[" + header.toJSON() + "," + event.toJSON() + "]}";
			assertArrayEquals(output.getBytes("UTF-8"), callback.output);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void testDispatchIAccumulator_noUrl() {
		final BasicAccumulator acc = new BasicAccumulator();
		final JsonDispatcherConfig config = new JsonDispatcherConfig();
		final TestDispatchCallback callback = new TestDispatchCallback();

		acc.trackAppEvent(new AppEvent("testkey", "testvalue"));
		config.callback = callback;
		
		final JsonDispatcher disp = new JsonDispatcher(config);
		
		assertTrue(!callback.errorCalled);
		assertTrue(!disp.dispatch(acc));
		assertTrue(callback.errorCalled);
	}

	@Test
	public void testDispatchIAccumulator_maxUploadSize() {
		final BasicAccumulator acc = new BasicAccumulator();
		final JsonDispatcherConfig config = new JsonDispatcherConfig();
		final TestDispatchCallback callback = new TestDispatchCallback();

		acc.trackAppEvent(new AppEvent("testkey", "testvalue"));
		config.callback = callback;
		config.url = "testurl";
		config.maxUploadSize = 1;
		
		final JsonDispatcher disp = new JsonDispatcher(config);
		
		assertTrue(!callback.errorCalled);
		assertTrue(!disp.dispatch(acc));
		assertTrue(callback.errorCalled);
	}

}
