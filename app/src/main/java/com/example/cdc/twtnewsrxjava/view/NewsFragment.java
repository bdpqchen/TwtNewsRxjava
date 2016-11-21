package com.example.cdc.twtnewsrxjava.view;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cdc.twtnewsrxjava.R;
import com.example.cdc.twtnewsrxjava.adapter.NewsAdapter;
import com.example.cdc.twtnewsrxjava.bean.NewsBean;
import com.example.cdc.twtnewsrxjava.common.PresenterFragment;
import com.example.cdc.twtnewsrxjava.presenter.NewsController;
import com.example.cdc.twtnewsrxjava.presenter.NewsPresenter;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by cdc on 16-11-13.
 */

public class NewsFragment extends PresenterFragment<NewsPresenter> implements NewsController, SwipeRefreshLayout.OnRefreshListener {

    public static final String PAGE_INDEX = "index";
    @InjectView(R.id.recycle_view)
    RecyclerView mRecycleView;
    @InjectView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @InjectView(R.id.error_text)
    TextView mErrorText;

    NewsAdapter mNewsAdapter;
    public static final int PAGE_START = 2;
    public int page = PAGE_START;
    private boolean loading = true;
    private LinearLayoutManager linearLayoutManager;

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
        //mSwipeRefreshLayout.setRefreshing(loading);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        //上拉加载更多
        mRecycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int totalcount = linearLayoutManager.getItemCount();
                int lastcount = linearLayoutManager.findLastVisibleItemPosition();
                Log.i("totalcount", totalcount + "");
                Log.i("lastcount", lastcount + "");
                Log.i("dy", dy + "");
                Log.i("loading", loading + "");


                if(lastcount + 1 >= totalcount && dy>0 && !loading){
                    page++;
                    loading = true;
                    mPresenter.loadMoreNewses(getArguments().getInt(PAGE_INDEX), page);
                }
            }
        });
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
        linearLayoutManager = new LinearLayoutManager(getActivity());
        //在这里获取数据
        mRecycleView.setLayoutManager(linearLayoutManager);
        mNewsAdapter = new NewsAdapter(getContext());
        mNewsAdapter.showFooter();
        mRecycleView.setAdapter(mNewsAdapter);
        mPresenter.initNewsDataByType(getArguments().getInt(PAGE_INDEX), page);
        Log.i("getArguments().", String.valueOf(getArguments().getInt(PAGE_INDEX)));
        Log.i("page", page + "");
    }

    @Override
    public void bindNewsList(List<NewsBean> newsList) {
        loading = false;
        mNewsAdapter.addItems(newsList);
        mSwipeRefreshLayout.setRefreshing(loading);
    }

    @Override
    public void refreshNewsList(List<NewsBean> newsList){
        loading = false;
        mNewsAdapter.refreshItems(newsList);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        page = PAGE_START;
        loading = true;
        mPresenter.refreshNewsDataByType(getArguments().getInt(PAGE_INDEX), page);
    }


    @Override
    public void onLoadMoreNewses(List<NewsBean> newsList){
        loading = false;
        mNewsAdapter.loadMoreItems(newsList);
    }

    @Override
    public void toastMessage(String message) {

    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void showLoadingDialog(String message) {

    }

    @Override
    public void dismissLoadingDialog() {

    }

}
/*
* 错误判断
* 总体架构的了解
* 错误类型
* 点击事件的详情页
* */