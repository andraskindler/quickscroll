package com.andraskindler.quickscrollsample.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.andraskindler.quickscroll.Scrollable;
import com.andraskindler.quickscrollsample.model.Contacts;

public class ContactAdapter extends BaseAdapter implements Scrollable {

	private final List<String> mContacts;
	private final Context mContext;

	public ContactAdapter(final Context context) {
		mContext = context;

		mContacts = new ArrayList<String>();
		for (String name : Contacts.sContacts) {
			mContacts.add(name);
		}

	}

	@Override
	public int getCount() {
		return mContacts.size();
	}

	@Override
	public Object getItem(int position) {
		return mContacts.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final TextView currentRow = new TextView(mContext);
		currentRow.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.WRAP_CONTENT));
		currentRow.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
		currentRow.setPadding(24, 10, 0, 10);
		currentRow.setText(mContacts.get(position));
		return currentRow;
	}

	@Override
	public String getIndicatorForPosition(int childposition, int groupposition) {
		return Character.toString(mContacts.get(childposition).charAt(0));
	}

	@Override
	public int getScrollPosition(int childposition, int groupposition) {
		return childposition;
	}

}
