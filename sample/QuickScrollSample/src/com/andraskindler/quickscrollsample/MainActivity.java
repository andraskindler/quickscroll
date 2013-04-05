package com.andraskindler.quickscrollsample;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import com.andraskindler.quickscrollsample.adapter.FastTrackPagerAdapter;
import com.andraskindler.quickscrollsample.fragment.ContactListFragment;
import com.andraskindler.quickscrollsample.fragment.CountryFragment;
import com.andraskindler.quickscrollsample.fragment.EventListFragment;
import com.andraskindler.quickscrollsample.fragment.InfoFragment;
import com.andraskindler.quickscrollsample.fragment.MovieListFragment;

public class MainActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final ViewGroup layout = (ViewGroup) getLayoutInflater().inflate(R.layout.activity_main, null);

		final ViewPager pager = (ViewPager) layout.findViewById(R.id.main_pager);

		final FastTrackPagerAdapter adapter = new FastTrackPagerAdapter(getSupportFragmentManager(), this);
		adapter.addPage("base_popup_holo_nohandle", ContactListFragment.class, null);
		adapter.addPage("expandable_indicator_holo_nohandle", CountryFragment.class, null);
		adapter.addPage("base_indicator_holo_handle", EventListFragment.class, null);
		adapter.addPage("base_popup_customlayout_colored_nohandle", MovieListFragment.class, null);
		adapter.addPage("info", InfoFragment.class, null);

		pager.setAdapter(adapter);

		setContentView(layout);
	}

}
