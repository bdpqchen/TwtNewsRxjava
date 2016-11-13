package com.example.cdc.twtnewsrxjava.presenter;

import android.content.Context;

import com.example.cdc.twtnewsrxjava.NewsApiClient;
import com.example.cdc.twtnewsrxjava.common.Presenter;
import com.example.cdc.twtnewsrxjava.view.NewsFragment;

/**
 * Created by cdc on 16-11-13.
 */

public class NewsPresenter extends Presenter{

    private NewsController mController;


    public NewsPresenter(Context context, NewsFragment newsFragment) {
        super(context);

    }

    public void initNewsDataByType(int type, int page) {
        NewsApiClient.getInstance().getNewsList(mContext, page);
        /*
        * 继续写 NewsApiSubscriber
        * onNext
        * onError (在NewsApiSubscriber 里统一处理
        * onCompleted
        * */



    }
}
