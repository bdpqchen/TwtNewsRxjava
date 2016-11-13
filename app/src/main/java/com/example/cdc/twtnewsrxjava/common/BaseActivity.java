package com.example.cdc.twtnewsrxjava.common;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.ButterKnife;

/**
 * Created by cdc on 16-11-13.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected abstract int getLayout();
    protected abstract void actionStart(Context context);
    protected abstract int getStatusBasColor();
    protected abstract void initView();
    protected abstract Toolbar getToolbar();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(getLayout());

        ButterKnife.inject(this);
        actionStart(this);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setStatusBarColor(getResources().getColor(getStatusBasColor()));
        }

        Toolbar toolbar = getToolbar();
        if(toolbar != null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        initView();

    }




}
