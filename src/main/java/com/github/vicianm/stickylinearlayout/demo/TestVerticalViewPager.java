package com.github.vicianm.stickylinearlayout.demo;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class TestVerticalViewPager extends ViewPager {

    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;

    private int mSwipeOrientation;
//    private ScrollerCustomDuration mScroller = null;

    public TestVerticalViewPager(Context context) {
        super(context);
        mSwipeOrientation = VERTICAL;
    }

    public TestVerticalViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        setSwipeOrientation(VERTICAL);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = super.onTouchEvent(mSwipeOrientation == VERTICAL ? swapXY(event) : event);
        Log.d("TestVerticalViewPager", "### TestVerticalViewPager.onTouchEvent: " + result);

//        View child = getChildAt(0);
//        if (child instanceof ScrollView) {
//            ScrollView scrollView = (ScrollView) child;
//            Log.d("TestVerticalViewPager", "### onTouchEvent.scrollView.getScrollY: " + scrollView.getScrollY());
//            Log.d("TestVerticalViewPager", "### onTouchEvent.scrollView.getScrollX: " + scrollView.getScrollX());
//        }

        return result;
    }

    @Override
    public void setAdapter(PagerAdapter adapter) {
        if (!(adapter instanceof TestPagerAdapter)) {
            throw new RuntimeException("Invalid param");
        }
        super.setAdapter(adapter);
    }

    public TestPagerAdapter getTestPagerAdapter() {
        return (TestPagerAdapter) super.getAdapter();
    }

    private float mmLastMotionY;
    boolean childTopOverScroll = false;
    boolean childBottomOverScroll = false;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {

        MotionEvent swappedMotionEvent = mSwipeOrientation == VERTICAL ? swapXY(event) : event;

        childTopOverScroll = false;
        childBottomOverScroll = false;

        final int action = event.getAction() & MotionEventCompat.ACTION_MASK;

        Object child = getTestPagerAdapter().getPrimaryItemObject();
        TestScrollView4 childScrollView = (child instanceof TestScrollView4)
                ? (TestScrollView4) child
                : null;


        if (action == MotionEvent.ACTION_MOVE) {

            // Treat scroll view differently.
            // Intercept only if ScrollView is scrolled to the very top/bottom.
            // Otherwise let ScrollView do its work first - just scroll according to user gesture.
            if (childScrollView != null) {
                TestScrollView4 scrollView = (TestScrollView4) child;

                Log.d("TestVerticalViewPager", "### onInterceptTouchEvent.scrollView.getScrollY: " + scrollView.getScrollY() + " hash: " + scrollView.hashCode());

                final float y = swappedMotionEvent.getY(0);
                final float dy = y - mmLastMotionY;
                if (scrollView.isScrolledToTop() && dy < 0) {
                    childTopOverScroll = true;
                }
                if (scrollView.isScrolledToBottom() && dy > 0) {
                    childBottomOverScroll = true;
                }

                Log.d("TestVerticalViewPager", "### onInterceptTouchEvent.#isScrolledToTop: " + scrollView.isScrolledToTop());
                Log.d("TestVerticalViewPager", "### onInterceptTouchEvent.#isScrolledToBottom: " + scrollView.isScrolledToBottom());
                Log.d("TestVerticalViewPager", "### onInterceptTouchEvent.#dy: " + dy);

            }
        }

        mmLastMotionY = swappedMotionEvent.getY(0);

        if (childScrollView == null || childTopOverScroll || childBottomOverScroll || action == MotionEvent.ACTION_DOWN) {
            boolean intercepted = super.onInterceptTouchEvent(swappedMotionEvent);
            Log.d("TestVerticalViewPager", "### onInterceptTouchEvent.#intercepted: " + intercepted);
            return intercepted;
        }

        return false;
    }

    @Override
    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        Log.d("TestVerticalViewPager", "### TestVerticalViewPager.requestDisallowInterceptTouchEvent: " + disallowIntercept);
        if (disallowIntercept) {
//            Log.d("TestVerticalViewPager", "### TestVerticalViewPager.requestDisallowInterceptTouchEvent: " + disallowIntercept);
        }
        super.requestDisallowInterceptTouchEvent(disallowIntercept);
    }

    public void setSwipeOrientation(int swipeOrientation) {
        if (swipeOrientation == HORIZONTAL || swipeOrientation == VERTICAL)
            mSwipeOrientation = swipeOrientation;
        else
            throw new IllegalStateException("Swipe Orientation can be either CustomViewPager.HORIZONTAL" +
                    " or CustomViewPager.VERTICAL");
        initSwipeMethods();
    }

//    private void setSwipeOrientation(Context context, AttributeSet attrs) {
//        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomViewPager);
//        mSwipeOrientation = typedArray.getInteger(R.styleable.CustomViewPager_swipe_orientation, 0);
//        typedArray.recycle();
//        initSwipeMethods();
//    }

    private void initSwipeMethods() {
        if (mSwipeOrientation == VERTICAL) {
            // The majority of the work is done over here
            setPageTransformer(true, new VerticalPageTransformer());
            // The easiest way to get rid of the overscroll drawing that happens on the left and right
            setOverScrollMode(OVER_SCROLL_NEVER);
        }
    }

    /**
     * Set the factor by which the duration will change~
     */
    public void setScrollDurationFactor(double scrollFactor) {
//        mScroller.setScrollDurationFactor(scrollFactor);
    }

    private MotionEvent swapXY(MotionEvent event) {

        MotionEvent clone = MotionEvent.obtain(event);

        float width = getWidth();
        float height = getHeight();

        float newX = (event.getY() / height) * width;
        float newY = (event.getX() / width) * height;

        clone.setLocation(newX, newY);
        return clone;
    }

    @Override
    protected boolean canScroll(View v, boolean checkV, int dx, int x, int y) {
        return super.canScroll(v, checkV, dx, x, y);
//        Log.d("TestVerticalViewPager", "@@@ canScroll");
//        return true;
    }

    private class VerticalPageTransformer implements ViewPager.PageTransformer {

        @Override
        public void transformPage(View page, float position) {
            if (position < -1) {
                // This page is way off-screen to the left
                page.setAlpha(0);
            } else if (position <= 1) {
                page.setAlpha(1);

                // Counteract the default slide transition
                page.setTranslationX(page.getWidth() * -position);

                // set Y position to swipe in from top
                float yPosition = position * page.getHeight();
                page.setTranslationY(yPosition);
            } else {
                // This page is way off screen to the right
                page.setAlpha(0);
            }
        }
    }

}
