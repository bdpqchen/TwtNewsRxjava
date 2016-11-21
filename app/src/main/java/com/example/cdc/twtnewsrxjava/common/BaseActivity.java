package com.example.cdc.twtnewsrxjava.common;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;

import com.example.cdc.twtnewsrxjava.utils.ToastUtils;

import butterknife.ButterKnife;

/**
 * Created by cdc on 16-11-13.
 */

public abstract class BaseActivity extends AppCompatActivity implements IViewController{

    protected abstract int getLayout();
    protected abstract void actionStart(Context context);
    protected abstract int getStatusBasColor();
    protected abstract void initView();
    protected abstract Toolbar getToolbar();
    protected LoadingDialog mLoadingDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

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


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showLoadingDialog() {
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog(this);
        }
        mLoadingDialog.show();
    }

    @Override
    public void showLoadingDialog(String message) {
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog(this);
        }
        mLoadingDialog.setMessage(message);
        mLoadingDialog.show();
    }

    @Override
    public void dismissLoadingDialog() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
    }

    @Override
    public void toastMessage(String message) {
        ToastUtils.showMessage(this,message);
    }




}
