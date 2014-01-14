package com.andraskindler.quickscrollsample.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andraskindler.quickscrollsample.R;

public class InfoFragment extends Fragment {
	
	private static final String URI= "https://github.com/andraskindler/quickscroll";

	public InfoFragment() {
		super();
	}

	public static InfoFragment newInstance() {
		return new InfoFragment();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View root = inflater.inflate(R.layout.fragment_info, container, false);
		
		TextView.class.cast(root.findViewById(R.id.tv_title)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri.parse(URI));
				startActivity(intent);
			}
		});
		
		return root;
	}

}
