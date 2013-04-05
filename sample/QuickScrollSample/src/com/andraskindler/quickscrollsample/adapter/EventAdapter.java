package com.andraskindler.quickscrollsample.adapter;

import java.util.List;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.andraskindler.quickscroll.Scrollable;
import com.andraskindler.quickscrollsample.model.Event;

public class EventAdapter extends BaseAdapter implements Scrollable {

	private final List<Event> mEvents;
	private final Context mContext;

	public EventAdapter(final Context context) {
		mContext = context;

		mEvents = Event.getEvents();

	}

	@Override
	public int getCount() {
		return mEvents.size();
	}

	@Override
	public Object getItem(int position) {
		return mEvents.get(position);
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
		currentRow.setText(mEvents.get(position).getmTitle());
		return currentRow;
	}

	@Override
	public String getIndicatorForPosition(int childposition, int groupposition) {
		return mEvents.get(childposition).getDateString();
	}

	@Override
	public int getScrollPosition(int childposition, int groupposition) {
		return childposition;
	}

}
