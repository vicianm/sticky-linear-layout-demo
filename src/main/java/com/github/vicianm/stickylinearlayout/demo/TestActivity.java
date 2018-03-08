package com.github.vicianm.stickylinearlayout.demo;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.eftimoff.viewpagertransformers.RotateUpTransformer;

public class TestActivity extends AppCompatActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setPageTransformer(true, new RotateUpTransformer());
        viewPager.setAdapter(new TestPagerAdapter(getBaseContext()));
    }

}
