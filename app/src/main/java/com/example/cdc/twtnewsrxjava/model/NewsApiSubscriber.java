package com.example.cdc.twtnewsrxjava.model;

import android.content.Context;
import android.util.Log;

import com.example.cdc.twtnewsrxjava.api.ApiException;
import com.example.cdc.twtnewsrxjava.api.OnErrorListener;
import com.example.cdc.twtnewsrxjava.api.OnNextListener;
import com.example.cdc.twtnewsrxjava.bean.ApiResponse;
import com.example.cdc.twtnewsrxjava.utils.ToastUtils;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by cdc on 16-11-21.
 */

public class NewsApiSubscriber<T> extends Subscriber<T> {

    protected Context mContext;
    protected OnNextListener<T> mOnNextListener;


    protected OnErrorListener mOnErrorListener;

    // TODO: 16-11-3 debug
    protected boolean isToastError = false;

    private static final String TAG = "ReadApiSubscriber";

    public NewsApiSubscriber(Context context, OnNextListener<T> listener) {
        mContext = context;
        mOnNextListener = listener;
    }

    public NewsApiSubscriber(Context context, OnNextListener<T> listener, OnErrorListener errorListener) {
        mContext = context;
        mOnNextListener = listener;
        mOnErrorListener = errorListener;

    }


    public boolean isToastError() {
        return isToastError;
    }

    public void setToastError(boolean toastError) {
        isToastError = toastError;
    }

    @Override
    public void onCompleted() {

    }

    public void setOnErrorListener(OnErrorListener mOnErrorListener) {
        this.mOnErrorListener = mOnErrorListener;
    }

    @Override
    public void onError(Throwable e) {
        if (mOnErrorListener != null) {
            mOnErrorListener.onError(e);
            return;
        }

        if (e instanceof ConnectException) {
            toastMessage("网络中断，请检查您的网络状态");
        } else if (e instanceof SocketTimeoutException) {
            toastMessage("网络连接超时");
        } else if (e instanceof HttpException){
            toastMessage("Http错误"+((HttpException) e).code());
        }
        else if (e instanceof ApiException){
            toastMessage("错误: " + e.getMessage());
        }else if(e instanceof NullPointerException){
            toastMessage("对不起，没有数据");
        } else {
            if (isToastError){
                toastMessage(e.getMessage());
            }
            toastMessage("sorry....");
        }
    }

    private void toastMessage(String message) {
        if (isToastError()) {
            Log.d(TAG, "toastMessage: " + message);
            ToastUtils.showMessage(mContext, message);
        }
    }

    @Override
    public void onNext(T t) {
        if (mOnNextListener != null) {
                mOnNextListener.onNext(t);
        }
    }



}
