package com.dankai.rxjava.net.rx;
/*
 *  项目名:    Practice
 *  包名：     com.dankai.retrofit.net
 *  文件名:    RestClient
 *  创建者:    WK
 *  时间：     2019/8/20 18:45
 *  描述：     进行网络请求的具体实现类
 *            使用建造者模式实现
 */

import android.content.Context;

import androidx.annotation.ColorInt;

import com.dankai.rxjava.loader.LoaderStyleEnum;
import com.dankai.rxjava.loader.MyLoader;
import com.dankai.rxjava.net.HttpMethod;
import com.dankai.rxjava.net.RestCreator;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * 在每一次Builder build的时候都会生成一个全新的实例
 * 这里面的参数是一次构建完毕，不允许更改
 */
public class RxRestClient {

    //创建RestClient需要的一些参数
    private final String URL;
    //请求参数
    private static final HashMap<String, Object> PARAMS = RestCreator.getParams();
    //请求回调
    private final RequestBody BODY;
    //文件上传参数
    private final File FILE;
    //Loader参数
    private final LoaderStyleEnum LOADER_STYLE;
    private final Context CONTEXT;
    @ColorInt
    private final int COLOR;

    public RxRestClient(String url,
                        Map<String, Object> params,
                        RequestBody body,
                        File file,
                        Context context,
                        LoaderStyleEnum loaderStyleEnum,
                        @ColorInt int color) {
        this.URL = url;
        PARAMS.putAll(params);
        this.BODY = body;
        this.FILE = file;
        this.CONTEXT = context;
        this.LOADER_STYLE = loaderStyleEnum;
        this.COLOR = color;
    }

    //建造者模式构建RestClient
    public static RxRestClientBuilder builder() {
        return new RxRestClientBuilder();
    }

    //具体的请求，根据method判断
    private Observable<String> request(HttpMethod method) {
        final RxRestService service = RestCreator.getRxRestService();
        Observable<String> observable = null;

        if (LOADER_STYLE != null) {
            MyLoader.showLoading(CONTEXT, LOADER_STYLE, COLOR);
        }

        switch (method) {
            case GET:
                observable = service.get(URL, PARAMS);
                break;
            case POST:
                observable = service.post(URL, PARAMS);
                break;
            case POST_RAW:
                observable = service.postRaw(URL, BODY);
                break;
            case PUT:
                observable = service.put(URL, PARAMS);
                break;
            case PUT_RAW:
                observable = service.putRaw(URL, BODY);
                break;
            case DELETE:
                observable = service.delete(URL, PARAMS);
                break;
            case UPLOAD:
                //文件上传
                final RequestBody requestBody =
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                observable = RestCreator.getRxRestService().upload(URL, body);
                break;
            default:
                break;
        }
        return observable;
    }

    //GET请求
    public final Observable<String> get() {
        return request(HttpMethod.GET);
    }

    //GET请求
    public final Observable<String> post() {
        if (BODY == null) {
            return request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("Params must be null!");
            }
            return request(HttpMethod.POST_RAW);
        }
    }

    //GET请求
    public final Observable<String> put() {
        if (BODY == null) {
            return request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("Params must be null!");
            }
            return request(HttpMethod.PUT_RAW);
        }

    }

    //GET请求
    public final Observable<String> delete() {
        return request(HttpMethod.DELETE);
    }

    //文件上传
    public final Observable<String> upload() {
        return request(HttpMethod.UPLOAD);
    }

    //文件下载
    public final Observable<ResponseBody> download() {
        return RestCreator.getRxRestService().download(URL, PARAMS);
    }
}
