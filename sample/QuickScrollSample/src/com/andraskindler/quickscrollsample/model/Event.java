package com.andraskindler.quickscrollsample.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class Event {

	private String mTitle;
	private GregorianCalendar mDate;

	private static final String[] EVENT_TYPE = { "Email", "Phone call", "Conference call", "Videoconference", "Meeting" };

	public Event(String mTitle, GregorianCalendar mDate) {
		super();
		this.mTitle = mTitle;
		this.mDate = mDate;
	}

	public String getmTitle() {
		return mTitle;
	}

	public void setmTitle(String mTitle) {
		this.mTitle = mTitle;
	}

	public GregorianCalendar getmDate() {
		return mDate;
	}

	public void setmDate(GregorianCalendar mDate) {
		this.mDate = mDate;
	}

	public static List<Event> getEvents() {
		final List<Event> events = new ArrayList<Event>();

		long time = System.currentTimeMillis();

		for (int i = 0; i < 120; i++) {
			time += 2 * 3600 * 1000;
			final GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTimeInMillis(time);
			events.add(new Event(EVENT_TYPE[new Random().nextInt(4)], calendar));
		}

		return events;
	}

	public String getDateString() {
		final SimpleDateFormat format = new SimpleDateFormat("yyyy MMMM dd'\n'EEEE", Locale.US);
		return format.format(mDate.getTime());
	}
}
