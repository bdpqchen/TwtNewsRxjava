package com.example.cdc.twtnewsrxjava.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cdc on 16-11-13.
 */

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    protected List<T> mDataSet = new ArrayList<>();
    protected Context mContext;

    protected boolean isShowFooter = true;

    public void showFooter() {
        isShowFooter = true;
    }

    public void hideFooter() {
        isShowFooter = false;
    }

    public BaseAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getItemCount() {
        if (isShowFooter) {
            return mDataSet.size() + 1;
        }
        return mDataSet.size();
    }

    public void refreshItems(List<T> items) {
        mDataSet.clear();
        mDataSet.addAll(items);
        hideFooter();
        notifyDataSetChanged();
    }

    public void addItems(List<T> items) {
        mDataSet.addAll(items);
        notifyDataSetChanged();
    }


}
