package com.andraskindler.quickscroll;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class QuickScroll extends View {

	// type statics
	public static final int TYPE_POPUP = 0;
	public static final int TYPE_INDICATOR = 1;
	public static final int TYPE_POPUP_WITH_HANDLE = 2;
	public static final int TYPE_INDICATOR_WITH_HANDLE = 3;

	// style statics
	public static final int STYLE_NONE = 0;
	public static final int STYLE_HOLO = 1;

	// base colors
	public static final int GREY_DARK = Color.parseColor("#e0585858");
	public static final int GREY_LIGHT = Color.parseColor("#f0888888");
	public static final int GREY_SCROLLBAR = Color.parseColor("#64404040");
	public static final int BLUE_LIGHT = Color.parseColor("#FF33B5E5");
	public static final int BLUE_LIGHT_SEMITRANSPARENT = Color.parseColor("#8033B5E5");

	// base variables
	private boolean mScrolling;

	private AlphaAnimation mFadeIn, mFadeOut;
	private TextView mScrollIndicatorText;
	private Scrollable mScrollable;
	private ListView mList;

	private int mGroupPosition;
	private int mItemCount;
	private long mFadeDuration;

	private int mType;

	// handlebar variables
	private View mHandlebar;
	private static final int mScrollbarMargin = 10;

	// indicator variables
	private RelativeLayout mScrollIndicator;

	// default constructors
	public QuickScroll(Context context) {
		super(context);
	}

	public QuickScroll(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public QuickScroll(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	/**
	 * Initializing the QuickScroll, this function must be called.
	 * <p>
	 * 
	 * @param type
	 *            the QuickScroll type. Available inputs: <b>QuickScroll.TYPE_POPUP</b> or <b>QuickScroll.TYPE_INDICATOR</b>
	 * @param list
	 *            the ListView
	 * @param scrollable
	 *            the adapter, must implement Scrollable interface
	 */
	public void init(final int type, final ListView list, final Scrollable scrollable, final int style) {

		mType = type;
		mList = list;
		mScrollable = scrollable;
		mGroupPosition = -1;
		mFadeIn = new AlphaAnimation(.0f, 1.0f);
		mFadeIn.setFillAfter(true);
		mFadeOut = new AlphaAnimation(1.0f, .0f);
		mFadeOut.setFillAfter(true);
		mFadeOut.setAnimationListener(new AnimationListener() {

			public void onAnimationStart(Animation animation) {
			}

			public void onAnimationRepeat(Animation animation) {
			}

			public void onAnimationEnd(Animation animation) {
				mScrolling = false;
			}
		});
		setFadeDuration(150);
		mScrolling = false;

		final float density = getResources().getDisplayMetrics().density;

		mList.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				if (mScrolling && (event.getAction() == MotionEvent.ACTION_MOVE || event.getAction() == MotionEvent.ACTION_DOWN)) {
					return true;
				}
				return false;
			}
		});

		if (mType == TYPE_INDICATOR_WITH_HANDLE || mType == TYPE_POPUP_WITH_HANDLE)
			mList.setOnScrollListener(new OnScrollListener() {

				public void onScrollStateChanged(AbsListView view, int scrollState) {

				}

				@SuppressLint("NewApi")
				public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
					if (!mScrolling && totalItemCount - visibleItemCount > 0) {
						moveHandlebar(getHeight() * firstVisibleItem / (totalItemCount - visibleItemCount));
					}
				}
			});

		final LayoutParams containerparams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		final RelativeLayout container = new RelativeLayout(getContext());
		container.setBackgroundColor(Color.TRANSPARENT);
		container.setLayoutParams(containerparams);

		if (mType == TYPE_POPUP || mType == TYPE_POPUP_WITH_HANDLE) {

			mScrollIndicatorText = new TextView(getContext());
			mScrollIndicatorText.setTextColor(Color.WHITE);
			mScrollIndicatorText.setVisibility(View.INVISIBLE);
			mScrollIndicatorText.setGravity(Gravity.CENTER);
			final RelativeLayout.LayoutParams popupparams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

			popupparams.addRule(RelativeLayout.CENTER_IN_PARENT);
			mScrollIndicatorText.setLayoutParams(popupparams);

			setPopupColor(GREY_LIGHT, GREY_DARK, 1, Color.WHITE, 1);
			setTextPadding(4, 4, 4, 4);

			container.addView(mScrollIndicatorText);
		} else if (mType == TYPE_INDICATOR || mType == TYPE_INDICATOR_WITH_HANDLE) {
			mScrollIndicator = (RelativeLayout) ((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.indicator, null);
			mScrollIndicatorText = (TextView) mScrollIndicator.findViewById(R.id.quickscroll_indicator_container_text);

			((Pin) mScrollIndicator.findViewById(R.id.quickscroll_indicator_container_pin)).getLayoutParams().width = 25;

			container.addView(mScrollIndicator);
		}

		// setting scrollbar width
		getLayoutParams().width = (int) (30 * density);
		mScrollIndicatorText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 32);

		// setting up indicator and popup container
		final RelativeLayout layout = new RelativeLayout(getContext());
		final RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		params.addRule(RelativeLayout.ALIGN_LEFT, getId());
		params.addRule(RelativeLayout.ALIGN_TOP, getId());
		params.addRule(RelativeLayout.ALIGN_RIGHT, getId());
		params.addRule(RelativeLayout.ALIGN_BOTTOM, getId());
		layout.setLayoutParams(params);

		// scrollbar setup
		if (style != STYLE_NONE) {
			final View scrollbar = new View(getContext());
			scrollbar.setBackgroundColor(GREY_SCROLLBAR);
			final RelativeLayout.LayoutParams scrollbarparams = new RelativeLayout.LayoutParams(1, LayoutParams.MATCH_PARENT);
			scrollbarparams.addRule(RelativeLayout.CENTER_HORIZONTAL);
			scrollbarparams.topMargin = mScrollbarMargin;
			scrollbarparams.bottomMargin = mScrollbarMargin;
			scrollbar.setLayoutParams(scrollbarparams);
			layout.addView(scrollbar);
			((ViewGroup) mList.getParent()).addView(layout);
			// creating the handlebar
			if (mType == TYPE_INDICATOR_WITH_HANDLE || mType == TYPE_POPUP_WITH_HANDLE) {
				mHandlebar = new View(getContext());
				setHandlebarColor(BLUE_LIGHT, BLUE_LIGHT, BLUE_LIGHT_SEMITRANSPARENT);
				final RelativeLayout.LayoutParams handleparams = new RelativeLayout.LayoutParams((int) (12 * density), (int) (36 * density));
				mHandlebar.setLayoutParams(handleparams);
				((RelativeLayout.LayoutParams) mHandlebar.getLayoutParams()).addRule(RelativeLayout.CENTER_HORIZONTAL);
				layout.addView(mHandlebar);
			}
		}

		((ViewGroup) mList.getParent()).addView(container);
	}

	// TODO: count == 0
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		mItemCount = mList.getAdapter().getCount();

		if (mScrolling && event.getAction() == MotionEvent.ACTION_CANCEL) {
			System.out.println("az");
			if (mType == TYPE_POPUP || mType == TYPE_POPUP_WITH_HANDLE) {
				if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
					mScrolling = false;
					mScrollIndicatorText.setVisibility(View.GONE);
				} else
					mScrollIndicatorText.startAnimation(mFadeOut);
			} else {
				if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
					mScrolling = false;
					mScrollIndicator.setVisibility(View.GONE);
				} else
					mScrollIndicator.startAnimation(mFadeOut);
			}
			if (mType == TYPE_INDICATOR_WITH_HANDLE || mType == TYPE_POPUP_WITH_HANDLE)
				mHandlebar.setSelected(false);
		}

		switch (mType) {
		case TYPE_POPUP:
			return PopupTouchEvent(event);
		case TYPE_POPUP_WITH_HANDLE:
			return PopupTouchEvent(event);
		case TYPE_INDICATOR:
			return IndicatorTouchEvent(event);
		case TYPE_INDICATOR_WITH_HANDLE:
			return IndicatorTouchEvent(event);
		default:
			break;
		}
		return false;
	}

	@SuppressLint("NewApi")
	private boolean IndicatorTouchEvent(final MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB)
				mScrollIndicator.setVisibility(View.VISIBLE);
			else
				mScrollIndicator.startAnimation(mFadeIn);
			mScrollIndicator.setPadding(0, 0, getWidth(), 0);
			scroll(event.getY());
			mScrolling = true;
			return true;
		case MotionEvent.ACTION_MOVE:
			scroll(event.getY());
			return true;
		case MotionEvent.ACTION_UP:
			if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
				mScrollIndicator.setVisibility(View.GONE);
				mScrolling = false;
			} else
				mScrollIndicator.startAnimation(mFadeOut);

			if (mType == TYPE_INDICATOR_WITH_HANDLE || mType == TYPE_POPUP_WITH_HANDLE)
				mHandlebar.setSelected(false);
			return true;
		default:
			break;
		}
		return false;
	}

	private boolean PopupTouchEvent(final MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB)
				mScrollIndicatorText.setVisibility(View.VISIBLE);
			else
				mScrollIndicatorText.startAnimation(mFadeIn);
			mScrolling = true;
			scroll(event.getY());
			return true;
		case MotionEvent.ACTION_MOVE:
			scroll(event.getY());
			return true;
		case MotionEvent.ACTION_UP:
			if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
				mScrollIndicatorText.setVisibility(View.GONE);
				mScrolling = false;
			} else
				mScrollIndicatorText.startAnimation(mFadeOut);

			if (mType == TYPE_INDICATOR_WITH_HANDLE || mType == TYPE_POPUP_WITH_HANDLE)
				mHandlebar.setSelected(false);
			return true;
		default:
			break;
		}
		return false;
	}

	@SuppressLint("NewApi")
	private void scroll(final float height) {
		if (mType == TYPE_INDICATOR || mType == TYPE_INDICATOR_WITH_HANDLE) {
			float move = height - mScrollIndicator.getHeight() / 2;

			if (move < 0)
				move = 0;
			else if (move > getHeight() - mScrollIndicator.getHeight())
				move = getHeight() - mScrollIndicator.getHeight();
			if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB)
				mScrollIndicator.startAnimation(moveCompat(move));
			else
				mScrollIndicator.setTranslationY(move);
		}

		if (mType == TYPE_INDICATOR_WITH_HANDLE || mType == TYPE_POPUP_WITH_HANDLE) {
			mHandlebar.setSelected(true);
			moveHandlebar(height - (mHandlebar.getHeight() / 2));
		}

		int postition = (int) ((height / getHeight()) * mItemCount);
		if (mList instanceof ExpandableListView) {
			final int grouppos = ExpandableListView.getPackedPositionGroup(((ExpandableListView) mList).getExpandableListPosition(postition));
			if (grouppos != -1)
				mGroupPosition = grouppos;
		}

		if (postition < 0)
			postition = 0;
		else if (postition >= mItemCount)
			postition = mItemCount - 1;
		mScrollIndicatorText.setText(mScrollable.getIndicatorForPosition(postition, mGroupPosition));
		mList.setSelection(mScrollable.getScrollPosition(postition, mGroupPosition));
	}

	@SuppressLint("NewApi")
	private void moveHandlebar(final float where) {
		float move = where;
		if (move < mScrollbarMargin)
			move = mScrollbarMargin;
		else if (move > getHeight() - mHandlebar.getHeight() - mScrollbarMargin)
			move = getHeight() - mHandlebar.getHeight() - mScrollbarMargin;

		if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB)
			mHandlebar.startAnimation(moveCompat(move));
		else
			mHandlebar.setTranslationY(move);
	}

	/**
	 * Sets the fade in and fade out duration of the indicator; default is 150 ms.
	 * <p>
	 * 
	 * @param millis
	 *            the fade duration in milliseconds
	 */
	public void setFadeDuration(long millis) {
		mFadeDuration = millis;
		mFadeIn.setDuration(mFadeDuration);
		mFadeOut.setDuration(mFadeDuration);
	}

	/**
	 * Sets the indicator colors, when QuickScroll.TYPE_INDICATOR is selected as type.
	 * <p>
	 * 
	 * @param background
	 *            the background color of the square
	 * @param tip
	 *            the background color of the tip triangle
	 * @param text
	 *            the color of the text
	 */
	public void setIndicatorColor(final int background, final int tip, final int text) {
		if (mType == TYPE_INDICATOR || mType == TYPE_INDICATOR_WITH_HANDLE) {
			((Pin) mScrollIndicator.findViewById(R.id.quickscroll_indicator_container_pin)).setColor(tip);
			mScrollIndicatorText.setTextColor(text);
			mScrollIndicatorText.setBackgroundColor(background);
		}
	}

	/**
	 * Sets the popup colors, when QuickScroll.TYPE_POPUP is selected as type.
	 * <p>
	 * 
	 * @param background
	 *            the background color of the TextView
	 * @param border
	 *            the background color of the border surrounding the TextView
	 * @param textcolor
	 *            the color of the text
	 */
	@SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	public void setPopupColor(final int backgroundcolor, final int bordercolor, final int borderwidthDPI, final int textcolor, float cornerradiusDPI) {

		final GradientDrawable popupbackground = new GradientDrawable();
		popupbackground.setCornerRadius(cornerradiusDPI * getResources().getDisplayMetrics().density);
		popupbackground.setStroke((int) (borderwidthDPI * getResources().getDisplayMetrics().density), bordercolor);
		popupbackground.setColor(backgroundcolor);

		if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN)
			mScrollIndicatorText.setBackgroundDrawable(popupbackground);
		else
			mScrollIndicatorText.setBackground(popupbackground);

		mScrollIndicatorText.setTextColor(textcolor);
	}

	/**
	 * Sets the width and height of the TextView containing the indicatortext. Default is WRAP_CONTENT, WRAP_CONTENT.
	 * <p>
	 * 
	 * @param widthDP
	 *            width in DP
	 * @param heightDP
	 *            height in DP
	 */
	public void setSize(final int widthDP, final int heightDP) {
		final float density = getResources().getDisplayMetrics().density;
		mScrollIndicatorText.getLayoutParams().width = (int) (widthDP * density);
		mScrollIndicatorText.getLayoutParams().height = (int) (heightDP * density);
	}

	/**
	 * Sets the padding of the TextView containing the indicatortext. Default is 4 dp.
	 * <p>
	 * 
	 * @param paddingLeftDP
	 *            left padding in DP
	 * @param paddingTopDP
	 *            top param in DP
	 * @param paddingBottomDP
	 *            bottom param in DP
	 * @param paddingRightDP
	 *            right param in DP
	 */
	public void setTextPadding(final int paddingLeftDP, final int paddingTopDP, final int paddingBottomDP, final int paddingRightDP) {
		final float density = getResources().getDisplayMetrics().density;
		mScrollIndicatorText.setPadding((int) (paddingLeftDP * density), (int) (paddingTopDP * density), (int) (paddingRightDP * density), (int) (paddingBottomDP * density));

	}

	/**
	 * Turns on fixed size for the TextView containing the indicatortext. Do not use with setSize()! This mode looks good if the indicatortext length is fixed, e.g. it's always two characters long.
	 * <p>
	 * 
	 * @param sizeEMS
	 *            number of characters in the indicatortext
	 */
	public void setFixedSize(final int sizeEMS) {
		mScrollIndicatorText.setEms(sizeEMS);
	}

	@SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	public void setHandlebarColor(final int inactive, final int activebase, final int activestroke) {
		final float density = getResources().getDisplayMetrics().density;
		final GradientDrawable bg_inactive = new GradientDrawable();
		bg_inactive.setCornerRadius(density);
		bg_inactive.setColor(inactive);
		bg_inactive.setStroke((int) (5 * density), Color.TRANSPARENT);
		final GradientDrawable bg_active = new GradientDrawable();
		bg_active.setCornerRadius(density);
		bg_active.setColor(activebase);
		bg_active.setStroke((int) (5 * density), activestroke);
		final StateListDrawable states = new StateListDrawable();
		states.addState(new int[] { android.R.attr.state_selected }, bg_active);
		states.addState(new int[] { android.R.attr.state_enabled }, bg_inactive);

		if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN)
			mHandlebar.setBackgroundDrawable(states);
		else
			mHandlebar.setBackground(states);
	}

	private TranslateAnimation moveCompat(final float toYDelta) {
		final TranslateAnimation anim = new TranslateAnimation(0, 0, toYDelta, toYDelta);
		anim.setFillAfter(true);
		anim.setDuration(0);
		return anim;
	}

}
