package com.example.cdc.twtnewsrxjava.view;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cdc.twtnewsrxjava.R;
import com.example.cdc.twtnewsrxjava.adapter.NewsAdapter;
import com.example.cdc.twtnewsrxjava.common.PresenterFragment;
import com.example.cdc.twtnewsrxjava.presenter.NewsController;
import com.example.cdc.twtnewsrxjava.presenter.NewsPresenter;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by cdc on 16-11-13.
 */

public class NewsFragment extends PresenterFragment<NewsPresenter> implements NewsController {

    public static final String PAGE_INDEX = "index";
    @InjectView(R.id.recycle_view)
    RecyclerView mRecycleView;
    @InjectView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @InjectView(R.id.error_text)
    TextView mErrorText;

    NewsAdapter newsAdapter;
    public int page = 0;

    public static NewsFragment tabIndex(int type) {
        Bundle args = new Bundle();
        args.putInt(PAGE_INDEX, type);
        NewsFragment newsFragment = new NewsFragment();
        newsFragment.setArguments(args);
        return newsFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


    @Override
    protected NewsPresenter getPresenter() {
        return new NewsPresenter(getContext(), this);
    }

    @Override
    protected int getLayout() {
        return R.layout.page_fragment;
    }

    @Override
    protected void initView() {
        //在这里获取数据
        mRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        newsAdapter = new NewsAdapter(getContext());
        newsAdapter.showFooter();
        mRecycleView.setAdapter(newsAdapter);
        mPresenter.initNewsDataByType(getArguments().getInt(PAGE_INDEX), page);

    }
}
