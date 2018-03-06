package com.github.vicianm.stickylinearlayout.demo;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class TestScrollView extends NestedScrollView implements GestureDetector.OnGestureListener {

    private TestScrollViewListener testScrollViewListener;

    private GestureDetectorCompat gestureDetectorCompat;

    public TestScrollView(Context context) {
        super(context);
        init();
    }

    public TestScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TestScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    protected void init() {
        this.gestureDetectorCompat = new GestureDetectorCompat(getContext(), this);
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        Log.d("TestScrollView", "@@@ onScrollChanged");
    }

    @Override
    public boolean onDown(MotionEvent e) {
        Log.d("TestScrollView", "@@@ onDown");
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Log.d("TestScrollView", "@@@ onShowPress");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.d("TestScrollView", "@@@ onSingleTapUp");
        return false;
    }

    private boolean initialized = false;

    @Override
    public boolean onScroll(MotionEvent initEvent, MotionEvent currentEvent, float distanceX, float distanceY) {
        Log.d("TestScrollView", "@@@ onScroll, scrollY:"+getScrollY()+" distanceY:"+distanceY+" "
                +(initEvent == null ? null : initEvent.getEventTime())+" "
                +(currentEvent == null ? null : currentEvent.getEventTime()));

        if (initialized || (distanceY < 0 && getScrollY() == 0)) {
            initialized = true;
            parentViewPager.onTouchEvent(currentEvent);
            return true;
        }

        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Log.d("TestScrollView", "@@@ onLongPress");
    }

    @Override
    public boolean onFling(MotionEvent initEvent, MotionEvent currentEvent, float velocityX, float velocityY) {
        Log.d("TestScrollView", "@@@ onFling");
        if (getScrollY() == 0) {
            parentViewPager.onTouchEvent(currentEvent);
            return true;
        }
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (parentViewPager.onInterceptTouchEvent(ev)) {
            Log.d("TestScrollView", "@@@ parentViewPager.onInterceptTouchEvent");
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("TestScrollView", "@@@ onTouchEvent: " + event.getEventTime());
        if (this.gestureDetectorCompat != null && this.gestureDetectorCompat.onTouchEvent(event)) {
            return true;
        }
        return super.onTouchEvent(event);
    }

//    float touchX = 0;
//    float touchY = 0;

    ViewPager parentViewPager;

    public void setParentViewPager(ViewPager viewPager) {
        this.parentViewPager = viewPager;
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent ev) {
//        switch (ev.getActionMasked()){
//            case MotionEvent.ACTION_DOWN:
//                touchX = ev.getX();
//                touchY = ev.getY();
//                return super.onTouchEvent(ev);
//            case MotionEvent.ACTION_MOVE:
//                if(Math.abs(touchX-ev.getX())<40){
//                    return super.onTouchEvent(ev);
//                }else{
//                    if (parentViewPager==null) {
//                        return false;
//                    } else {
//                        return parentViewPager.onTouchEvent(ev);
//                    }
//                }
//            case MotionEvent.ACTION_CANCEL:
//            case MotionEvent.ACTION_UP:
//                touchX=0;
//                touchY=0;
//                break;
//        }
//        return super.onTouchEvent(ev);
//    }
}
