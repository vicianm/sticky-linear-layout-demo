package com.github.vicianm.stickylinearlayout.demo;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eftimoff.viewpagertransformers.RotateUpTransformer;

import me.kaelaela.verticalviewpager.VerticalViewPager;

public class TestActivity extends AppCompatActivity {

    private VerticalViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        viewPager = (VerticalViewPager) findViewById(R.id.view_pager);
        viewPager.setPageTransformer(true, new RotateUpTransformer());
        viewPager.setAdapter(new CustomPagerAdapter(getBaseContext()));

    }

    class CustomPagerAdapter extends PagerAdapter {

        private Context mContext;

        public CustomPagerAdapter(Context context) {
            mContext = context;
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

            if (position == 1) {
                TestScrollView testScrollView = (TestScrollView) layout;
                testScrollView.setParentViewPager(viewPager);
//                testScrollView.setTestScrollViewListener(new TestScrollViewListener() {
//                    @Override
//                    public void onOverscrollUp() {
//                        if (position > 0) viewPager.setCurrentItem(position-1, true);
//                    }
//                    @Override
//                    public void onOverscrollDown() {
//                        if (position < getCount()-1) viewPager.setCurrentItem(position+1, true);
//                    }
//                });
            }

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

}
