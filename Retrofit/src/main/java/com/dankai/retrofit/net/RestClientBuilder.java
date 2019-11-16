package com.dankai.retrofit.net;
/*
 *  项目名:    Practice
 *  包名：     com.dankai.retrofit.net
 *  文件名:    RestClientBuilder
 *  创建者:    WK
 *  时间：     2019/8/20 19:09
 *  描述：     建造者和宿主类分开，不使用静态内部类
 */

import android.content.Context;
import android.graphics.Color;

import androidx.annotation.ColorInt;

import com.dankai.retrofit.loader.LoaderStyleEnum;
import com.dankai.retrofit.net.callback.RestCallback;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class RestClientBuilder {

    private String mUrl = null;
    private static final Map<String, Object> PARAMS = RestCreator.getParams();
    private RestCallback.IRequest mRequest = null;
    private RestCallback.ISuccess mSuccess = null;
    private RestCallback.IFailure mFailure = null;
    private RestCallback.IError mError = null;
    private RequestBody mBody = null;
    //文件上传的参数
    private File mFile = null;
    //文件下载的参数
    private String mDownloadDir = null;
    private String mExtension = null;
    private String mName = null;
    //Loader参数
    private Context mContext = null;
    private LoaderStyleEnum mLoaderStyleEnum = null;
    @ColorInt
    private int mColor;

    //不允许外部的类去直接New这个类，只允许RestClient去new它
    RestClientBuilder() {
    }

    //设置Url
    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    //添加请求参数，传入map
    public final RestClientBuilder params(HashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    //重载，传入键值对
    public final RestClientBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    //上传的文件
    public final RestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    //要上传文件的路径
    public final RestClientBuilder file(String filePath) {
        this.mFile = new File(filePath);
        return this;
    }

    //所下载文件存放的目录
    public final RestClientBuilder dir(String downloadDir) {
        this.mDownloadDir = downloadDir;
        return this;
    }

    //所下载文件的后缀名
    public final RestClientBuilder extension(String extension) {
        this.mExtension = extension;
        return this;
    }

    //所下载文件的文件名
    public final RestClientBuilder name(String name) {
        this.mName = name;
        return this;
    }

    //如果传入的是原始数据(json数据)
    public final RestClientBuilder jsonRaw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    //网络请求成功的回调
    public final RestClientBuilder request(RestCallback.IRequest iRequest) {
        this.mRequest = iRequest;
        return this;
    }

    //网络请求成功的回调
    public final RestClientBuilder success(RestCallback.ISuccess iSuccess) {
        this.mSuccess = iSuccess;
        return this;
    }

    //网络请求失败的回调
    public final RestClientBuilder failure(RestCallback.IFailure iFailure) {
        this.mFailure = iFailure;
        return this;
    }

    //网络请求错误的回调
    public final RestClientBuilder error(RestCallback.IError iError) {
        this.mError = iError;
        return this;
    }

    public final RestClientBuilder loader(Context context, LoaderStyleEnum style, @ColorInt int color) {
        this.mContext = context;
        this.mLoaderStyleEnum = style;
        this.mColor = color;
        return this;
    }

    public final RestClientBuilder loader(Context context, LoaderStyleEnum style) {
        this.mContext = context;
        this.mLoaderStyleEnum = style;
        this.mColor = Color.WHITE;
        return this;
    }

    public final RestClientBuilder loader(Context context, @ColorInt int color) {
        this.mContext = context;
        this.mLoaderStyleEnum = LoaderStyleEnum.BALL_CLIP_ROTATE_PULSE_INDICATOR;
        this.mColor = color;
        return this;
    }

    public final RestClientBuilder loader(Context context) {
        this.mContext = context;
        this.mLoaderStyleEnum = LoaderStyleEnum.BALL_CLIP_ROTATE_PULSE_INDICATOR;
        this.mColor = Color.WHITE;
        return this;
    }

    //创建出RestClient
    public final RestClient build() {
        return new RestClient(
                mUrl, PARAMS, mRequest, mSuccess,
                mFailure, mError, mBody, mFile,
                mDownloadDir, mExtension, mName,
                mContext, mLoaderStyleEnum, mColor
        );
    }
}
