package com.example.cdc.twtnewsrxjava;


import com.example.cdc.twtnewsrxjava.api.ApiUtils;
import com.example.cdc.twtnewsrxjava.api.NewsApi;
import com.example.cdc.twtnewsrxjava.model.NewsResponseTransformer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by cdc on 16-11-13.
 */

public class NewsApiClient {

    private Map<Object, CompositeSubscription> mSubscriptionsMap = new HashMap<>();
    private NewsApi mService;
    private NewsResponseTransformer mResponseTransformer;
    private Retrofit mRetrofit;

    private NewsApiClient(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(sRequestInterceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://open.twtstudio.com/api/v1/news/")
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mService = mRetrofit.create(NewsApi.class);

        mResponseTransformer = new NewsResponseTransformer();
    }


    private static Interceptor sRequestInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {

            Request originRequest = chain.request();

            Request.Builder builder = originRequest
                    .newBuilder();
            //        .addHeader("Authorization", PrefUtils.getReadToken());

            Request request = builder.build();
            return chain.proceed(request);
        }
    };

    private static class SingletonHolder {
        private static final NewsApiClient INSTANCE = new NewsApiClient();
    }

    /**
     * APIclient的单例模式
     *
     * @return 返回APIclient的单例
     */
    public static NewsApiClient getInstance() {
        return SingletonHolder.INSTANCE;
    }


    /**
     * 取消订阅关系，类似与cancel网络请求，操作在presenter的destroy方法里面被调用
     *
     * @param tag 传入presenter的实例
     */
    public void unSubscribe(Object tag) {
        if (mSubscriptionsMap.containsKey(tag)) {
            CompositeSubscription subscriptions = mSubscriptionsMap.get(tag);
            subscriptions.unsubscribe();
            mSubscriptionsMap.remove(tag);
        }
    }

    /**
     * 添加订阅关系，同时rxjava自动发起网络请求
     *
     * @param tag          presenter的实例
     * @param subscription 创建好的订阅关系
     */
    protected void addSubscription(Object tag, Subscription subscription) {
        if (tag == null) {
            return;
        }
        CompositeSubscription subscriptions;
        if (mSubscriptionsMap.containsKey(tag)) {
            subscriptions = mSubscriptionsMap.get(tag);
        } else {
            subscriptions = new CompositeSubscription();
        }
        subscriptions.add(subscription);
        //Log.i("subscriptions","----");

        mSubscriptionsMap.put(tag, subscriptions);

    }

    /*
    * 获取新闻列表api
    * @param tag        presenter，需继承common包里面的presenter，自动绑定订阅关系
    * @param subscriber 订阅者， 也是在presenter中初始化
    * @param type       新闻类型
    * @param page       新闻页数
    * */
    public void getNewsList(Object tag, Subscriber subscriber, int type, int page){
        Subscription subscription = mService.getNewsList(type + "", page + "")
                .map(mResponseTransformer)
                .compose(ApiUtils.applySchedulers())
                .subscribe(subscriber);
        addSubscription(tag, subscription);
    }


}
