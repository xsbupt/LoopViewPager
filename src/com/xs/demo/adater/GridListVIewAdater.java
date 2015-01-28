package com.xs.demo.adater;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xs on 14/11/23.
 */
public abstract class GridListVIewAdater<ItemDataType> extends BaseAdapter {

    private int mCol = 1;

    private ArrayList<ItemDataType> mData;

    private GridListVIewAdater.ViewHolder mHolder;

    public GridListVIewAdater(int col, ArrayList<ItemDataType> data, GridListVIewAdater.ViewHolder holder) {
        mCol = col;
        mData = data;
        mHolder = holder;
    }

    @Override
    public int getCount() {
        return mData.size() / mCol + (mData.size() % mCol == 0 ? 0 : 1);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GridHolder holder = new GridHolder();
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            LinearLayout row = new LinearLayout(parent.getContext());
            convertView = row;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.weight = 1;
            for (int i = 0; i < mCol; i++) {
                ViewHolder tempHolder = mHolder.newInstance();
                View tempView = tempHolder.createView(position * mCol + i, inflater);
                row.addView(tempView, params);
                holder.gridHolder.add(tempHolder);
            }
            convertView.setTag(holder);
        } else {
            holder = (GridHolder) convertView.getTag();
        }
        for (int i = 0; i < mCol; i++) {
            int index = position*mCol+i;
            if (index < mData.size()) {
                ViewHolder item = holder.gridHolder.get(i);
                item.showData(index, mData.get(index));
            }
        }

        return convertView;
    }

    static class GridHolder {
        List<ViewHolder> gridHolder = new ArrayList<ViewHolder>();
    }

    public abstract static class ViewHolder<ItemDataType> {

        public abstract ViewHolder newInstance();

        public abstract View createView(int index, LayoutInflater inflater);

        public abstract void showData(int index, ItemDataType data);


    }

}
