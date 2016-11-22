package com.example.cdc.twtnewsrxjava.presenter;

import android.content.Context;

import com.example.cdc.twtnewsrxjava.NewsApiClient;
import com.example.cdc.twtnewsrxjava.api.OnNextListener;
import com.example.cdc.twtnewsrxjava.bean.NewsContentBean;
import com.example.cdc.twtnewsrxjava.common.Presenter;
import com.example.cdc.twtnewsrxjava.model.NewsApiSubscriber;

/**
 * Created by cdc on 16-11-22.
 */

public class NewsContentPresenter extends Presenter {

    private NewsContentController mController;

    public NewsContentPresenter(Context context, NewsContentController controller) {
        super(context);
        mController = controller;
    }

    public void initNewsContent(int index){
        NewsApiClient.getInstance().getNewsContent(mContext, new NewsApiSubscriber(mContext, mInitNewsContentOnNextListener), index);
    }

    private OnNextListener<NewsContentBean> mInitNewsContentOnNextListener = new OnNextListener<NewsContentBean>() {
        @Override
        public void onNext(NewsContentBean newsContentBean) {
            mController.onInitNewsContent(newsContentBean);
        }
    };

}
