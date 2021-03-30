package com.dankai.rxjava.net.callback;
/*
 *  项目名:    Practice
 *  包名：     com.dankai.retrofit.net
 *  文件名:    RestCallback
 *  创建者:    WK
 *  时间：     2019/8/20 19:13
 *  描述：     自定义回调接口
 */

public class RestCallback {

    /**
     * 请求成功
     */
    public interface ISuccess {
        void onSuccess(String response);
    }

    /**
     * 请求错误
     */
    public interface IError {
        void onError(int code, String msg);
    }

    /**
     * 请求失败
     */
    public interface IFailure {
        void onFailure();
    }

    /**
     * 请求方法，执行请求开始之前和请求完成以后的操作
     */
    public interface IRequest {
        void onRequestStart();

        void onRequestEnd();
    }
}
