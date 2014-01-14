package com.andraskindler.quickscrollsample.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.andraskindler.quickscroll.QuickScroll;
import com.andraskindler.quickscrollsample.R;
import com.andraskindler.quickscrollsample.adapter.CountryAdapter;

public class CountryFragment extends Fragment {

	public CountryFragment() {
		super();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.fragment_countrylist, container, false);

		final CountryAdapter adapter = new CountryAdapter(getActivity());

		final ExpandableListView list = ExpandableListView.class.cast(layout.findViewById(R.id.fragment_country_list));

		list.setAdapter(adapter);
		
		final QuickScroll quickscroll = (QuickScroll) layout.findViewById(R.id.quickscroll);
		quickscroll.init(QuickScroll.TYPE_POPUP, list, adapter, QuickScroll.STYLE_HOLO);

		return layout;
	}

}
