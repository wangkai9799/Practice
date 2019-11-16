package com.dankai.retrofit.net.callback;
/*
 *  项目名:    Practice
 *  包名：     com.dankai.retrofit.net.callback
 *  文件名:    RequestCallbacks
 *  创建者:    WK
 *  时间：     2019/8/20 20:06
 *  描述：     自定义请求的回调
 */

import android.os.Handler;

import com.dankai.retrofit.loader.LoaderStyleEnum;
import com.dankai.retrofit.loader.MyLoader;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestCallbacks implements Callback<String> {

    private final RestCallback.IRequest REQUEST;
    private final RestCallback.ISuccess SUCCESS;
    private final RestCallback.IFailure FAILURE;
    private final RestCallback.IError ERROR;
    private final LoaderStyleEnum LOADER_STYLE;

    private static final Handler HANDLER = new Handler();

    public RequestCallbacks(
            RestCallback.IRequest request,
            RestCallback.ISuccess success,
            RestCallback.IFailure failure,
            RestCallback.IError error,
            LoaderStyleEnum loaderStyleEnum
    ) {
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.LOADER_STYLE = loaderStyleEnum;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        //请求成功
        if (response.isSuccessful()) {
            //call已经执行了
            if (call.isExecuted()) {
                if (SUCCESS != null) {
                    SUCCESS.onSuccess(response.body());
                }
            }
        } else {
            if (ERROR != null) {
                ERROR.onError(response.code(), response.message());
            }
        }

        if (LOADER_STYLE != null) {
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    MyLoader.stopLoading();
                }
            }, 1000);

        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (FAILURE != null) {
            FAILURE.onFailure();
        }
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }
        if (LOADER_STYLE != null) {
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    MyLoader.stopLoading();
                }
            }, 1000);

        }
    }
}
