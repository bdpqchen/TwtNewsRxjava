package com.example.cdc.twtnewsrxjava.presenter;

import android.content.Context;

import com.example.cdc.twtnewsrxjava.NewsApiClient;
import com.example.cdc.twtnewsrxjava.api.NewsApi;
import com.example.cdc.twtnewsrxjava.model.NewsApiSubscriber;
import com.example.cdc.twtnewsrxjava.api.OnNextListener;
import com.example.cdc.twtnewsrxjava.bean.NewsBean;
import com.example.cdc.twtnewsrxjava.common.Presenter;

import java.util.List;

/**
 * Created by cdc on 16-11-13.
 */

public class NewsPresenter extends Presenter{

    private NewsController mController;


    public NewsPresenter(Context context, NewsController controller) {
        super(context);
        mController = controller;

    }

    public void initNewsDataByType(int type, int page) {
        NewsApiClient.getInstance().getNewsList(mContext, new NewsApiSubscriber(mContext, mNewsListOnNextListener), type, page);
    }

    private OnNextListener<List<NewsBean>> mNewsListOnNextListener = new OnNextListener<List<NewsBean>>() {
        @Override
        public void onNext(List<NewsBean> newsList) {

            mController.bindNewsList(newsList);
        }
    };

    public void refreshNewsDataByType(int type, int page) {
        NewsApiClient.getInstance().getNewsList(mContext, new NewsApiSubscriber(mContext, mRefreshNewsListOnNextListener), type, page);
    }

    private OnNextListener<List<NewsBean>> mRefreshNewsListOnNextListener = new OnNextListener<List<NewsBean>>() {
        @Override
        public void onNext(List<NewsBean> newsList) {
            mController.refreshNewsList(newsList);
        }
    };

    public void loadMoreNewses(int type, int page) {
        NewsApiClient.getInstance().getNewsList(mContext, new NewsApiSubscriber(mContext, mLoadMoreNewsesOnNextListener), type, page);
    }

    private OnNextListener<List<NewsBean>> mLoadMoreNewsesOnNextListener = new OnNextListener<List<NewsBean>>() {
        @Override
        public void onNext(List<NewsBean> newsList) {
            mController.onLoadMoreNewses(newsList);
        }
    };


}
