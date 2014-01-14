package com.andraskindler.quickscrollsample.adapter;

import java.util.List;

import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.andraskindler.quickscroll.Scrollable;
import com.andraskindler.quickscrollsample.model.MarvelMovie;

public class MovieAdapter extends BaseAdapter implements Scrollable {

	private List<MarvelMovie> movies;

	public MovieAdapter(final List<MarvelMovie> list) {
		movies = list;
	}

	@Override
	public int getCount() {
		return movies.size();
	}

	@Override
	public MarvelMovie getItem(int position) {
		return movies.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final MarvelMovie movie = movies.get(position);

		String htmltext = "<big>" + movie.getTitle() + "</big><br/>" + movie.getYear();

		TextView textview = null;
		if (convertView != null)
			textview = TextView.class.cast(convertView);
		else {
			textview = new TextView(parent.getContext());
			textview.setPadding(10, 10, 10, 10);
		}

		textview.setText(Html.fromHtml(htmltext));
		return textview;
	}

	@Override
	public String getIndicatorForPosition(int arg0, int arg1) {
		return movies.get(arg0).getIndicator();
	}

	@Override
	public int getScrollPosition(int arg0, int arg1) {
		return arg0;
	}

}
