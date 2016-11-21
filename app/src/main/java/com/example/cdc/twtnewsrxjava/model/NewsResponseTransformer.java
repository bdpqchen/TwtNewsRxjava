package com.example.cdc.twtnewsrxjava.model;

import com.example.cdc.twtnewsrxjava.api.ApiException;
import com.example.cdc.twtnewsrxjava.bean.ApiResponse;

import rx.functions.Func1;

/**
 * Created by jcy on 2016/8/7.
 */

public class NewsResponseTransformer<T> implements Func1<ApiResponse<T>, T> {
    @Override
    public T call(ApiResponse<T> tApiResponse) {
        if (tApiResponse.getError_code()!=-1){
            throw new ApiException(tApiResponse);
        }
        return tApiResponse.getData();
    }
}
