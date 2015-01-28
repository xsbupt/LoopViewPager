package com.xs.demo.adater;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.xs.view.R;

import java.util.ArrayList;

/**
 * Created by xs on 15/1/4.
 */
public class ListViewFragment extends Fragment {

    private ViewPager mViewPager;

    private ListView mListView;

    private String mTitle;

    static ArrayList<String> data = new ArrayList<String>();

    static {
        for (int i = 0; i < 30; i++) {
            data.add("here" + String.valueOf(i));
        }
    }

    public ListViewFragment() {
    }

    public ListViewFragment(String title) {
        mTitle = title;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mListView = new ListView(getActivity());

        LinearLayout layout = new LinearLayout(getActivity());
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        layout.setGravity(Gravity.CENTER);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layout.addView(mListView, params);

        mListView.setAdapter(mAdater);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.v("xs", "item click---->" + position);
            }
        });

        mListView.setFocusable(false);
        return layout;
    }

    class Holder extends GridListVIewAdater.ViewHolder<String> implements Cloneable {

        private TextView title;

        @Override
        public GridListVIewAdater.ViewHolder newInstance() {
            return new Holder();
        }

        @Override
        public View createView(int index, LayoutInflater inflater) {
            View tempView = inflater.inflate(R.layout.list_item, null);
            title = (TextView) tempView.findViewById(R.id.title);
            return tempView;
        }

        @Override
        public void showData(int index, String data) {
            title.setText(mTitle + "--->" + data);
        }
    }

    private GridListVIewAdater mAdater = new GridListVIewAdater(1, data, new Holder()) {
        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }
    };


}
