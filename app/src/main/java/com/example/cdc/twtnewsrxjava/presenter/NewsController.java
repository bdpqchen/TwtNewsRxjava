package com.example.cdc.twtnewsrxjava.presenter;

import com.example.cdc.twtnewsrxjava.bean.NewsBean;
import com.example.cdc.twtnewsrxjava.common.IViewController;

import java.util.List;

/**
 * Created by cdc on 16-11-13.
 */
public interface NewsController extends IViewController {

    void bindNewsList(List<NewsBean> newsList);

    void onLoadMoreNewses(List<NewsBean> newsList);

    void refreshNewsList(List<NewsBean>newsList);


}
