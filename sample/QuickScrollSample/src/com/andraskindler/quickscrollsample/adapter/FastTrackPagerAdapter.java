package com.andraskindler.quickscrollsample.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FastTrackPagerAdapter extends FragmentPagerAdapter {
	
	private List<FragmentInfo> mPages;
	private Context mContext;

	public FastTrackPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	public FastTrackPagerAdapter(FragmentManager fm, Context context) {
		super(fm);
		mContext = context;
		mPages = new ArrayList<FastTrackPagerAdapter.FragmentInfo>();
	}

	protected static final class FragmentInfo {

		protected final String tag;
		protected final Class<?> clss;
		protected final Bundle args;

		FragmentInfo(final String _tag, final Class<?> _class, final Bundle _args) {
			tag = _tag;
			clss = _class;
			args = _args;
		}

	}
	
	public void addPage(final String tag, final Class<?> clss, final Bundle args) {
		mPages.add(new FragmentInfo(tag, clss, args));
	}

	@Override
	public Fragment getItem(int arg0) {
		final FragmentInfo info = mPages.get(arg0);
		return Fragment.instantiate(mContext, info.clss.getName(), info.args);
	}

	@Override
	public int getCount() {
		return mPages.size();
	}

}
