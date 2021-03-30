package com.dankai.rxjava.net;
/*
 *  项目名:    Practice
 *  包名：     com.dankai.retrofit.net
 *  文件名:    RestCreator
 *  创建者:    WK
 *  时间：     2019/8/20 18:58
 *  描述：     RetrofitService创建类
 */

import com.dankai.rxjava.net.rx.RxRestService;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RestCreator {

    /**
     * 使用单例模式获取请求的参数
     */
    private static final class ParamsHolder {
        public static final HashMap<String, Object> PARAMS = new HashMap<>();
    }

    public static HashMap<String, Object> getParams() {
        return ParamsHolder.PARAMS;
    }

    /**
     * 使用单例模式创建Retrofit
     * 获取RestService
     */
    public static RestService getRestService() {
        return RestServiceHolder.REST_SERVICE;
    }

    /**
     * 使用单例模式创建Retrofit
     * RetrofitHolder
     */
    private static final class RetrofitHolder {
        private static final String BASE_URL = StaticFields.BASE_URL;
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OkHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
    }

    /**
     * 设定其他的一些参数
     */
    private static final class OkHttpHolder {
        private static final int TIME_OUT = 60;
        private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    /**
     * 单例模式获取RestService
     */
    private static final class RestServiceHolder {
        private static final RestService REST_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
    }


    /************************** RxService接口 *******************************/
    /**
     * 使用单例模式创建Retrofit
     * 获取RestService
     */
    public static RxRestService getRxRestService() {
        return RxRestServiceHolder.REST_SERVICE;
    }

    /**
     * 单例模式获取RestService
     */
    private static final class RxRestServiceHolder {
        private static final RxRestService REST_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(RxRestService.class);
    }
}
