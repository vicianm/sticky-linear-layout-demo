package com.github.vicianm.stickylinearlayout.demo;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class TestScrollView3 extends ScrollView implements GestureDetector.OnGestureListener {

    private GestureDetectorCompat gestureDetectorCompat;

    private ViewPager viewPager;

    public TestScrollView3(Context context) {
        super(context);
        init();
    }

    public TestScrollView3(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TestScrollView3(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public TestScrollView3(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    protected void init() {
        Log.d("TestScrollView4", "@@@ init");
        this.gestureDetectorCompat = new GestureDetectorCompat(getContext(), this);
    }

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("TestScrollView4", "@@@ onTouchEvent");
        gestureDetectorCompat.onTouchEvent(event);
//        if (gestureDetectorCompat.onTouchEvent(event)) {
//            return true;
//        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        Log.d("TestScrollView4", "@@@ onDown");
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Log.d("TestScrollView4", "@@@ onShowPress");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.d("TestScrollView4", "@@@ onSingleTapUp");
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.d("TestScrollView4", "@@@ onScroll");
        if (distanceY < 0 && getScrollY() == 0) {
            Log.d("TestScrollView4", "@@@ viewPager.onTouchEvent");
            MotionEvent pagerMotionEvent = MotionEvent.obtain(e2);
            float width = getWidth();
            float height = getHeight();
            float newX = (pagerMotionEvent.getY() / height) * width;
            float newY = (pagerMotionEvent.getX() / width) * height;
            pagerMotionEvent.setLocation(newX, newY);
            viewPager.onTouchEvent(pagerMotionEvent);
            return true;
        }
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Log.d("TestScrollView4", "@@@ onLongPress");
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.d("TestScrollView4", "@@@ onFling");
        return false;
    }

}

