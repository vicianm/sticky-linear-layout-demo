package com.github.vicianm.stickylinearlayout.demo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

public class TestScrollView4 extends ScrollView {

    public TestScrollView4(Context context) {
        super(context);
    }

    public TestScrollView4(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TestScrollView4(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TestScrollView4(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean onInterceptResult = super.onInterceptTouchEvent(ev);
        Log.d("TestScrollView4", "### TestScrollView4.onInterceptTouchEvent: " + onInterceptResult);
        return onInterceptResult;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        boolean onTouchResult = super.onTouchEvent(ev);
        Log.d("TestScrollView4", "### TestScrollView4.onTouchEvent: " + onTouchResult);
        return onTouchResult;
    }

    @Override
    public boolean canScrollVertically(int direction) {
//        if (direction > 0 && getScrollY() <= 0) {
//            return false;
//        } else if (direction < 0 && getScrollY() >= 300){
//            return false;
//        }
        boolean canScrollVertically = super.canScrollVertically(direction);
//        Log.d("TestScrollView4", "### TestScrollView4.canScrollVertically: " + canScrollVertically);
        return canScrollVertically;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        Log.d("TestScrollView4", "### scrollY: " + getScrollY() + " hash: " + hashCode());
        super.onScrollChanged(l, t, oldl, oldt);
    }

    public boolean isScrolledToTop() {
        return getScrollY() == 0;
    }

    public boolean isScrolledToBottom() {
        // Grab the last child placed in the ScrollView, we need it to determinate the bottom position.
        View view = getChildAt(getChildCount()-1);

        int diff = (view.getBottom()-(getHeight()+getScrollY()));
        return diff <= 0;
    }

}
