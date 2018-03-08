package com.github.vicianm.stickylinearlayout.demo;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by miso on 3/7/18.
 */

public class TestViewPager extends ViewPager {

    public TestViewPager(Context context) {
        super(context);
    }

    public TestViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = super.onTouchEvent(event);
        Log.d("TestViewPager", "### TestViewPager.onTouchEvent: " + result);
        return result;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        boolean intercepted = super.onInterceptTouchEvent(event);
        Log.d("TestViewPager", "### TestViewPager.onInterceptTouchEvent: " + intercepted);
        return intercepted;
    }
}
