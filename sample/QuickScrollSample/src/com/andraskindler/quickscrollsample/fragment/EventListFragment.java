package com.andraskindler.quickscrollsample.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.andraskindler.quickscroll.QuickScroll;
import com.andraskindler.quickscrollsample.R;
import com.andraskindler.quickscrollsample.adapter.EventAdapter;


public class EventListFragment extends ListFragment{
	
	private static final int CORAL = Color.parseColor("#f0f76541");
	private static final int CORAL_DARK = Color.parseColor("#e0e55b3c");
	private static final int CORAL_HANDLE = Color.parseColor("#80f76541");
	
	public EventListFragment() {
		super();
	}

	public EventListFragment newInstance() {
		return new EventListFragment();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.fragment_eventlist, container, false);
		
		final EventAdapter adapter = new EventAdapter(getActivity());
		setListAdapter(adapter);
		
		final ListView list = (ListView) layout.findViewById(android.R.id.list);
		
		final QuickScroll quickscroll = (QuickScroll) layout.findViewById(R.id.quickscroll);
		quickscroll.init(QuickScroll.TYPE_INDICATOR_WITH_HANDLE, list, adapter, QuickScroll.STYLE_HOLO);
		quickscroll.setIndicatorColor(CORAL, CORAL_DARK, Color.WHITE);
		quickscroll.setHandlebarColor(CORAL, CORAL, CORAL_HANDLE);
		return layout;
	}
	
}
