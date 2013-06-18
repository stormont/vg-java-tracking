package com.voyagegames.java.tracking.tests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.voyagegames.java.tracking.AppEvent;
import com.voyagegames.java.tracking.AppSetting;
import com.voyagegames.java.tracking.CustomEvent;
import com.voyagegames.java.tracking.CustomSetting;
import com.voyagegames.java.tracking.IAccumulator;
import com.voyagegames.java.tracking.IDispatcher;
import com.voyagegames.java.tracking.KeyValue;
import com.voyagegames.java.tracking.Tracker;
import com.voyagegames.java.tracking.UserAction;
import com.voyagegames.java.tracking.UserView;

public class TrackerTest {
	
	private class TestAccumulator implements IAccumulator {
		
		private List<KeyValue> data = new ArrayList<KeyValue>();

		@Override
		public List<KeyValue> getTrackingData() {
			return data;
		}

		@Override
		public void resetTrackingData() {
			data.clear();
		}

		@Override
		public void trackAppEvent(AppEvent data) {
			this.data.add(data);
		}

		@Override
		public void trackAppSetting(AppSetting data) {
			this.data.add(data);
		}

		@Override
		public void trackCustomEvent(CustomEvent data) {
			this.data.add(data);
		}

		@Override
		public void trackCustomSetting(CustomSetting data) {
			this.data.add(data);
		}

		@Override
		public void trackUserAction(UserAction data) {
			this.data.add(data);
		}

		@Override
		public void trackUserView(UserView data) {
			this.data.add(data);
		}
		
	}
	
	private class TestDispatcher implements IDispatcher {
		
		private List<KeyValue> data = new ArrayList<KeyValue>();
		
		public List<KeyValue> getTrackingData() {
			return data;
		}

		@Override
		public boolean dispatch() {
			// no-op
			return false;
		}

		@Override
		public boolean dispatch(IAccumulator accumulator) {
			data.addAll(accumulator.getTrackingData());
			return true;
		}
		
	}
	
	private Tracker<TestAccumulator, TestDispatcher> buildTracker() {
		final TestAccumulator acc = new TestAccumulator();
		final TestDispatcher disp = new TestDispatcher();
		return new Tracker<TestAccumulator, TestDispatcher>(acc, disp);
	}
	
	private void trackValue(Tracker<?, ?> tracker) {
		tracker.trackAppEvent(new AppEvent("testkey", "testvalue"));
	}

	@Test
	public void testTracker() {
		final Tracker<?, ?> tracker = buildTracker();
		assertTrue(null != tracker);
	}

	@Test
	public void testDispatch() {
		final TestAccumulator acc = new TestAccumulator();
		final TestDispatcher disp = new TestDispatcher();
		final Tracker<?, ?> tracker = new Tracker<TestAccumulator, TestDispatcher>(acc, disp);
		
		trackValue(tracker);
		assertTrue(1 == acc.getTrackingData().size());
		assertTrue(0 == disp.getTrackingData().size());
		tracker.dispatch();
		assertTrue(0 == acc.getTrackingData().size());
		assertTrue(1 == disp.getTrackingData().size());
	}

	@Test
	public void testDispatch_IAccumulator() {
		final TestAccumulator acc = new TestAccumulator();
		final TestDispatcher disp = new TestDispatcher();
		final Tracker<?, ?> tracker = new Tracker<TestAccumulator, TestDispatcher>(acc, disp);

		assertTrue(!tracker.dispatch(acc));
		assertTrue(!tracker.dispatch(null));
		assertTrue(!tracker.dispatch(new TestAccumulator()));
	}

	@Test
	public void testGetTrackingData() {
		final TestAccumulator acc = new TestAccumulator();
		final TestDispatcher disp = new TestDispatcher();
		final Tracker<?, ?> tracker = new Tracker<TestAccumulator, TestDispatcher>(acc, disp);

		assertTrue(0 == acc.getTrackingData().size());
		trackValue(tracker);
		assertTrue(1 == acc.getTrackingData().size());
	}

	@Test
	public void testResetTrackingData() {
		final TestAccumulator acc = new TestAccumulator();
		final TestDispatcher disp = new TestDispatcher();
		final Tracker<?, ?> tracker = new Tracker<TestAccumulator, TestDispatcher>(acc, disp);

		trackValue(tracker);
		assertTrue(1 == acc.getTrackingData().size());
		tracker.resetTrackingData();
		assertTrue(0 == acc.getTrackingData().size());
	}

	@Test
	public void testTrackAppEvent() {
		final TestAccumulator acc = new TestAccumulator();
		final TestDispatcher disp = new TestDispatcher();
		final Tracker<?, ?> tracker = new Tracker<TestAccumulator, TestDispatcher>(acc, disp);

		assertTrue(0 == acc.getTrackingData().size());
		tracker.trackAppEvent(new AppEvent("testkey", "testvalue"));
		assertTrue(1 == acc.getTrackingData().size());
	}

	@Test
	public void testTrackAppSetting() {
		final TestAccumulator acc = new TestAccumulator();
		final TestDispatcher disp = new TestDispatcher();
		final Tracker<?, ?> tracker = new Tracker<TestAccumulator, TestDispatcher>(acc, disp);

		assertTrue(0 == acc.getTrackingData().size());
		tracker.trackAppSetting(new AppSetting("testkey", "testvalue"));
		assertTrue(1 == acc.getTrackingData().size());
	}

	@Test
	public void testTrackCustomEvent() {
		final TestAccumulator acc = new TestAccumulator();
		final TestDispatcher disp = new TestDispatcher();
		final Tracker<?, ?> tracker = new Tracker<TestAccumulator, TestDispatcher>(acc, disp);

		assertTrue(0 == acc.getTrackingData().size());
		tracker.trackCustomEvent(new CustomEvent("testkey", "testvalue"));
		assertTrue(1 == acc.getTrackingData().size());
	}

	@Test
	public void testTrackCustomSetting() {
		final TestAccumulator acc = new TestAccumulator();
		final TestDispatcher disp = new TestDispatcher();
		final Tracker<?, ?> tracker = new Tracker<TestAccumulator, TestDispatcher>(acc, disp);

		assertTrue(0 == acc.getTrackingData().size());
		tracker.trackCustomSetting(new CustomSetting("testkey", "testvalue"));
		assertTrue(1 == acc.getTrackingData().size());
	}

	@Test
	public void testTrackUserAction() {
		final TestAccumulator acc = new TestAccumulator();
		final TestDispatcher disp = new TestDispatcher();
		final Tracker<?, ?> tracker = new Tracker<TestAccumulator, TestDispatcher>(acc, disp);

		assertTrue(0 == acc.getTrackingData().size());
		tracker.trackUserAction(new UserAction("testkey", "testvalue"));
		assertTrue(1 == acc.getTrackingData().size());
	}

	@Test
	public void testTrackUserView() {
		final TestAccumulator acc = new TestAccumulator();
		final TestDispatcher disp = new TestDispatcher();
		final Tracker<?, ?> tracker = new Tracker<TestAccumulator, TestDispatcher>(acc, disp);

		assertTrue(0 == acc.getTrackingData().size());
		tracker.trackUserView(new UserView("testkey", "testvalue"));
		assertTrue(1 == acc.getTrackingData().size());
	}

}
