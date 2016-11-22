package com.example.cdc.twtnewsrxjava.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.cdc.twtnewsrxjava.R;
import com.example.cdc.twtnewsrxjava.bean.NewsContentBean;
import com.example.cdc.twtnewsrxjava.common.BaseActivity;
import com.example.cdc.twtnewsrxjava.presenter.NewsContentController;
import com.example.cdc.twtnewsrxjava.presenter.NewsContentPresenter;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by cdc on 16-11-22.
 */

public class NewsContentActivity extends BaseActivity implements NewsContentController{


    @InjectView(R.id.toolbar_content)
    Toolbar mToolbar;
    @InjectView(R.id.subject_content)
    TextView subjectContent;
    @InjectView(R.id.newscome_content)
    TextView newsComeContent;
    @InjectView(R.id.webview)
    WebView webview;
    @InjectView(R.id.gonggao)
    TextView gonggao;
    @InjectView(R.id.shengao)
    TextView shengao;
    @InjectView(R.id.sheying)
    TextView sheying;

    private Bundle bundle;

    @Override
    protected int getLayout() {
        return R.layout.news_content_activity;
    }

    @Override
    public void actionStart(Context context) {
    }

    @Override
    protected int getStatusBasColor() {
        return R.color.colorPrimary;
    }

    @Override
    protected void initView() {
        NewsContentPresenter mPresenter = new NewsContentPresenter(this, this);
        Intent intent = getIntent();
        bundle = intent.getExtras();
        mPresenter.initNewsContent(bundle.getInt("index"));

    }

    @Override
    protected Toolbar getToolbar() {
        mToolbar.setTitle("新闻详情");
        return mToolbar;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
    }

    @Override
    public void onInitNewsContent(NewsContentBean newsContent) {
        subjectContent.setText(bundle.getString("subject"));
        webview.loadData(newsContent.content, "text/html;charset=utf-8", null);
        if(!newsContent.gonggao.equals(""))gonggao.setText("供稿:" + newsContent.gonggao);
        if(!newsContent.shengao.equals(""))shengao.setText("审稿:" + newsContent.shengao);
        if(!newsContent.sheying.equals(""))sheying.setText("摄影:" + newsContent.sheying);
    }
}
