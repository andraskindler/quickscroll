package com.andraskindler.quickscrollsample.adapter;

import java.util.SortedSet;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.andraskindler.quickscroll.Scrollable;
import com.andraskindler.quickscrollsample.model.Countries;
import com.google.common.collect.Iterables;
import com.google.common.collect.TreeMultimap;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class CountryAdapter extends BaseExpandableListAdapter implements Scrollable {

	private final TreeMultimap<String, String> mMap;
	private final Context mContext;

	public CountryAdapter(final Context context) {
		mMap = TreeMultimap.create();

		for (String s : Countries.sAfrica)
			mMap.put("Africa", s);

		for (String s : Countries.sAsia)
			mMap.put("Asia", s);

		for (String s : Countries.sEurope)
			mMap.put("Europe", s);

		for (String s : Countries.sNorthAmerica)
			mMap.put("North America", s);

		for (String s : Countries.sOceania)
			mMap.put("Oceania", s);

		for (String s : Countries.sSouthAmerica)
			mMap.put("South America", s);

		mContext = context;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		final String key = Iterables.get(mMap.keySet(), groupPosition);
		return Iterables.get(mMap.get(key), childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
		final String key = Iterables.get(mMap.keySet(), groupPosition);
		final String country = Iterables.get(mMap.get(key), childPosition);
		final TextView countrytext = new TextView(mContext);
		countrytext.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
		countrytext.setPadding(10, 0, 0, 0);
		countrytext.setText(country);

		return countrytext;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return mMap.get(Iterables.get(mMap.keySet(), groupPosition)).size();
	}

	@Override
	public SortedSet<String> getGroup(int groupPosition) {
		final String key = Iterables.get(mMap.keySet(), groupPosition);
		return mMap.get(key);
	}

	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @Override
	public int getGroupCount() {
		return mMap.keySet().size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		final String grouptext = Iterables.get(mMap.keySet(), groupPosition);
		final TextView grouptitle = new TextView(mContext);
		grouptitle.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 24);
		grouptitle.setText(grouptext);

		return grouptitle;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}

	@Override
    public String getIndicatorForPosition(int childposition, int groupposition) {
		return  Iterables.get(mMap.keySet(), groupposition);
    }

	@Override
    public int getScrollPosition(int childposition, int groupposition) {
	    return childposition;
    }
	
}
