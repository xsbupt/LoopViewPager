package com.xs.demo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.widget.Button;
import com.xs.demo.adater.ListViewFragment;
import com.xs.view.LoopViewPager;
import com.xs.view.R;

/**
 * Created by xs on 15/1/23.
 */
public class LoopViewPagerDemo extends FragmentActivity {

    private static final String[] CONTENT = new String[] {"A", "B", "C", "D"};
    private LoopViewPager mLoopViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.loop_viewpager);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoopViewPager.setCurrentItem(3);
            }
        });

        FragmentPagerAdapter adapter = new TestAdapter(getSupportFragmentManager());

        mLoopViewPager = (LoopViewPager) findViewById(R.id.loop_viewpager);
        mLoopViewPager.setAdapter(adapter);
        mLoopViewPager.setLoopEnable(true);
    }

    class TestAdapter extends FragmentPagerAdapter {

        public TestAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            position = (CONTENT.length + position%CONTENT.length)%CONTENT.length;
            ListViewFragment fragment = new ListViewFragment(CONTENT[position]);
            return fragment;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return CONTENT[position % CONTENT.length].toUpperCase();
        }

        @Override
        public int getCount() {
            return CONTENT.length;
        }
    }

}
