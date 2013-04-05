package com.andraskindler.quickscrollsample.fragment;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.andraskindler.quickscroll.QuickScroll;
import com.andraskindler.quickscrollsample.R;
import com.andraskindler.quickscrollsample.adapter.ContactAdapter;


public class ContactListFragment extends ListFragment{

	public ContactListFragment() {
		super();
	}

	public ContactListFragment newInstance() {
		return new ContactListFragment();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.fragment_contactlist, container, false);
		
		final ContactAdapter adapter = new ContactAdapter(getActivity());
		setListAdapter(adapter);
		
		final ListView list = (ListView) layout.findViewById(android.R.id.list);
		
		final QuickScroll quickscroll = (QuickScroll) layout.findViewById(R.id.quickscroll);
		quickscroll.init(QuickScroll.TYPE_INDICATOR_WITH_HANDLE, list, adapter, QuickScroll.STYLE_HOLO);
		quickscroll.setFixedSize(1);
		quickscroll.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 48);
		return layout;
	}
	
}
