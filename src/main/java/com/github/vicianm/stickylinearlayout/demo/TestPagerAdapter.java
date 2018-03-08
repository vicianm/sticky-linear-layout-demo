package com.github.vicianm.stickylinearlayout.demo;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * {@link PagerAdapter} with the ability to return currently active
 * page container (container of a primary item).
 *
 * @see #getPrimaryItemObject()
 */
class TestPagerAdapter extends PagerAdapter {

    private Context mContext;

    private Object primaryItemObject;

    public TestPagerAdapter(Context context) {
        mContext = context;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        this.primaryItemObject = object;
        super.setPrimaryItem(container, position, object);
    }

    public Object getPrimaryItemObject() {
        return primaryItemObject;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, final int position) {

        int layoutResId = 0;
        switch (position) {
            case 0:
                layoutResId = R.layout.test1_fragment;
                break;
            case 1:
                layoutResId = R.layout.test2_fragment;
                break;
            case 2:
                layoutResId = R.layout.test3_fragment;
                break;
            case 3:
                layoutResId = R.layout.test4_fragment;
                break;
            default:
                throw new RuntimeException("Invalid index: " + position);
        }

        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup layout = (ViewGroup) inflater.inflate(layoutResId, collection, false);
        collection.addView(layout);

        return layout;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

}
