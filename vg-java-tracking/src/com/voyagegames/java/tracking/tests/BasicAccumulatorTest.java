package com.voyagegames.java.tracking.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.voyagegames.java.tracking.AppEvent;
import com.voyagegames.java.tracking.AppSetting;
import com.voyagegames.java.tracking.BasicAccumulator;
import com.voyagegames.java.tracking.CustomEvent;
import com.voyagegames.java.tracking.CustomSetting;
import com.voyagegames.java.tracking.UserAction;
import com.voyagegames.java.tracking.UserView;

public class BasicAccumulatorTest {

	@Test
	public void testBasicAccumulator() {
		final BasicAccumulator acc = new BasicAccumulator();
		
		assertTrue(0 == acc.getTrackingData().size());
	}

	@Test
	public void testGetTrackingData() {
		final BasicAccumulator acc = new BasicAccumulator();
		
		assertTrue(0 == acc.getTrackingData().size());
		acc.trackAppEvent(new AppEvent("testkey", "testvalue"));
		assertTrue(1 == acc.getTrackingData().size());
	}

	@Test
	public void testResetTrackingData() {
		final BasicAccumulator acc = new BasicAccumulator();
		
		assertTrue(0 == acc.getTrackingData().size());
		acc.trackAppEvent(new AppEvent("testkey", "testvalue"));
		assertTrue(1 == acc.getTrackingData().size());
		acc.resetTrackingData();
		assertTrue(0 == acc.getTrackingData().size());
	}

	@Test
	public void testTrackAppEvent() {
		final BasicAccumulator acc = new BasicAccumulator();
		final AppEvent data = new AppEvent("testkey", "testvalue");
		
		assertTrue(0 == acc.getTrackingData().size());
		acc.trackAppEvent(data);
		assertTrue(1 == acc.getTrackingData().size());
		assertTrue(data == acc.getTrackingData().get(0));
	}

	@Test
	public void testTrackAppSetting() {
		final BasicAccumulator acc = new BasicAccumulator();
		final AppSetting data = new AppSetting("testkey", "testvalue");
		
		assertTrue(0 == acc.getTrackingData().size());
		acc.trackAppSetting(data);
		assertTrue(1 == acc.getTrackingData().size());
		assertTrue(data == acc.getTrackingData().get(0));
	}

	@Test
	public void testTrackCustomEvent() {
		final BasicAccumulator acc = new BasicAccumulator();
		final CustomEvent data = new CustomEvent("testkey", "testvalue");
		
		assertTrue(0 == acc.getTrackingData().size());
		acc.trackCustomEvent(data);
		assertTrue(1 == acc.getTrackingData().size());
		assertTrue(data == acc.getTrackingData().get(0));
	}

	@Test
	public void testTrackCustomSetting() {
		final BasicAccumulator acc = new BasicAccumulator();
		final CustomSetting data = new CustomSetting("testkey", "testvalue");
		
		assertTrue(0 == acc.getTrackingData().size());
		acc.trackCustomSetting(data);
		assertTrue(1 == acc.getTrackingData().size());
		assertTrue(data == acc.getTrackingData().get(0));
	}

	@Test
	public void testTrackUserAction() {
		final BasicAccumulator acc = new BasicAccumulator();
		final UserAction data = new UserAction("testkey", "testvalue");
		
		assertTrue(0 == acc.getTrackingData().size());
		acc.trackUserAction(data);
		assertTrue(1 == acc.getTrackingData().size());
		assertTrue(data == acc.getTrackingData().get(0));
	}

	@Test
	public void testTrackUserView() {
		final BasicAccumulator acc = new BasicAccumulator();
		final UserView data = new UserView("testkey", "testvalue");
		
		assertTrue(0 == acc.getTrackingData().size());
		acc.trackUserView(data);
		assertTrue(1 == acc.getTrackingData().size());
		assertTrue(data == acc.getTrackingData().get(0));
	}

}
