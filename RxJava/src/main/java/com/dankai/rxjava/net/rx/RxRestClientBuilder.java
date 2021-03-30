package com.dankai.rxjava.net.rx;
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

import com.dankai.rxjava.loader.LoaderStyleEnum;
import com.dankai.rxjava.net.RestClient;
import com.dankai.rxjava.net.RestCreator;
import com.dankai.rxjava.net.callback.RestCallback;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class RxRestClientBuilder {

    private String mUrl = null;
    private static final Map<String, Object> PARAMS = RestCreator.getParams();
    private RequestBody mBody = null;
    //文件上传的参数
    private File mFile = null;
    //Loader参数
    private Context mContext = null;
    private LoaderStyleEnum mLoaderStyleEnum = null;
    @ColorInt
    private int mColor;

    //不允许外部的类去直接New这个类，只允许RestClient去new它
    RxRestClientBuilder() {
    }

    //设置Url
    public final RxRestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    //添加请求参数，传入map
    public final RxRestClientBuilder params(HashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    //重载，传入键值对
    public final RxRestClientBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    //上传的文件
    public final RxRestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    //要上传文件的路径
    public final RxRestClientBuilder file(String filePath) {
        this.mFile = new File(filePath);
        return this;
    }

    //如果传入的是原始数据(json数据)
    public final RxRestClientBuilder jsonRaw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RxRestClientBuilder loader(Context context, LoaderStyleEnum style, @ColorInt int color) {
        this.mContext = context;
        this.mLoaderStyleEnum = style;
        this.mColor = color;
        return this;
    }

    public final RxRestClientBuilder loader(Context context, LoaderStyleEnum style) {
        this.mContext = context;
        this.mLoaderStyleEnum = style;
        this.mColor = Color.WHITE;
        return this;
    }

    public final RxRestClientBuilder loader(Context context, @ColorInt int color) {
        this.mContext = context;
        this.mLoaderStyleEnum = LoaderStyleEnum.BALL_CLIP_ROTATE_PULSE_INDICATOR;
        this.mColor = color;
        return this;
    }

    public final RxRestClientBuilder loader(Context context) {
        this.mContext = context;
        this.mLoaderStyleEnum = LoaderStyleEnum.BALL_CLIP_ROTATE_PULSE_INDICATOR;
        this.mColor = Color.WHITE;
        return this;
    }

    //创建出RestClient
    public final RxRestClient build() {
        return new RxRestClient(mUrl, PARAMS, mBody, mFile, mContext, mLoaderStyleEnum, mColor);
    }
}
