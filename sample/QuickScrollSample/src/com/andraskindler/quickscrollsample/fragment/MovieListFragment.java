package com.andraskindler.quickscrollsample.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andraskindler.quickscroll.QuickScroll;
import com.andraskindler.quickscrollsample.R;
import com.andraskindler.quickscrollsample.adapter.MovieAdapter;
import com.andraskindler.quickscrollsample.model.MarvelMovie;

public class MovieListFragment extends ListFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final ViewGroup root = ViewGroup.class.cast(inflater.inflate(R.layout.fragment_movielist, container, false));

		final ListView list = ListView.class.cast(root.findViewById(android.R.id.list));
		final MovieAdapter adapter = new MovieAdapter(MarvelMovie.getMovies());
		list.setAdapter(adapter);

		final QuickScroll fastTrack = QuickScroll.class.cast(root.findViewById(R.id.quickscroll));
		fastTrack.init(QuickScroll.TYPE_POPUP, list, adapter, QuickScroll.STYLE_NONE);
		fastTrack.setFixedSize(2);
		fastTrack.setPopupColor(QuickScroll.BLUE_LIGHT, QuickScroll.BLUE_LIGHT_SEMITRANSPARENT, 1, Color.WHITE, 1);

		root.addView(createAlphabetTrack());

		return root;
	}

	private ViewGroup createAlphabetTrack() {
		final LinearLayout layout = new LinearLayout(getActivity());
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams((int) (30 * getResources().getDisplayMetrics().density), LayoutParams.MATCH_PARENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		params.addRule(RelativeLayout.BELOW, R.id.tv_title);
		layout.setLayoutParams(params);
		layout.setOrientation(LinearLayout.VERTICAL);

		final LinearLayout.LayoutParams textparams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		textparams.weight = 1;
		final int height = getResources().getDisplayMetrics().heightPixels;
		int iterate = 0;
		if (height >= 1024){
			iterate = 1; layout.setWeightSum(26);
		} else {
			iterate = 2; layout.setWeightSum(13);
		}
		for (char character = 'a'; character <= 'z'; character+=iterate) {
			final TextView textview = new TextView(getActivity());
			textview.setLayoutParams(textparams);
			textview.setGravity(Gravity.CENTER_HORIZONTAL);
			textview.setText(Character.toString(character));
			layout.addView(textview);
		}

		return layout;
	}
}
