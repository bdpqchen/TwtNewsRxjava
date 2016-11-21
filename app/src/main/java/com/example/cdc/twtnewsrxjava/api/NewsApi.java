package com.example.cdc.twtnewsrxjava.api;

import com.example.cdc.twtnewsrxjava.bean.ApiResponse;
import com.example.cdc.twtnewsrxjava.bean.NewsBean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by cdc on 16-11-13.
 */

public interface NewsApi  {

    @GET("{type}/page/{pageIndex}")
    Observable<ApiResponse<List<NewsBean>>> getNewsList(@Path("type")String type, @Path("pageIndex")String pageIndex);

   /* @GET("{index}")
    Call<NewsContentBean> getNewsContent(@Path("index")String index);
*/
}
