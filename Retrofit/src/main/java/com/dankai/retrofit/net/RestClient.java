package com.dankai.retrofit.net;
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

import com.dankai.retrofit.loader.LoaderStyleEnum;
import com.dankai.retrofit.loader.MyLoader;
import com.dankai.retrofit.net.callback.RequestCallbacks;
import com.dankai.retrofit.net.callback.RestCallback;
import com.dankai.retrofit.net.download.DownloadHandler;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * 在每一次Builder build的时候都会生成一个全新的实例
 * 这里面的参数是一次构建完毕，不允许更改
 */
public class RestClient {

    //创建RestClient需要的一些参数
    private final String URL;
    //请求参数
    private static final HashMap<String, Object> PARAMS = RestCreator.getParams();
    //请求回调
    private final RestCallback.IRequest REQUEST;
    private final RestCallback.ISuccess SUCCESS;
    private final RestCallback.IFailure FAILURE;
    private final RestCallback.IError ERROR;
    private final RequestBody BODY;
    //文件上传参数
    private final File FILE;
    //文件下载参数
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;
    //Loader参数
    private final LoaderStyleEnum LOADER_STYLE;
    private final Context CONTEXT;
    @ColorInt
    private final int COLOR;

    public RestClient(String url,
                      Map<String, Object> params,
                      RestCallback.IRequest request,
                      RestCallback.ISuccess success,
                      RestCallback.IFailure failure,
                      RestCallback.IError error,
                      RequestBody body,
                      File file,
                      String downloadDir,
                      String extension,
                      String name,
                      Context context,
                      LoaderStyleEnum loaderStyleEnum,
                      @ColorInt int color) {
        this.URL = url;
        PARAMS.putAll(params);
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.BODY = body;
        this.FILE = file;
        this.DOWNLOAD_DIR = downloadDir;
        this.EXTENSION = extension;
        this.NAME = name;
        this.CONTEXT = context;
        this.LOADER_STYLE = loaderStyleEnum;
        this.COLOR = color;
    }

    //建造者模式构建RestClient
    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    //具体的请求，根据method判断
    private void request(HttpMethod method) {
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;

        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }

        if (LOADER_STYLE != null) {
            MyLoader.showLoading(CONTEXT, LOADER_STYLE, COLOR);
        }

        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case POST_RAW:
                call = service.postRaw(URL, BODY);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case PUT_RAW:
                call = service.putRaw(URL, BODY);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            case UPLOAD:
                //文件上传
                final RequestBody requestBody =
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                call = RestCreator.getRestService().upload(URL, body);
                break;
            default:
                break;
        }

        if (call != null) {
            call.enqueue(getRequestCallback());
        }
    }

    private Callback<String> getRequestCallback() {
        return new RequestCallbacks(
                REQUEST,
                SUCCESS,
                FAILURE,
                ERROR,
                LOADER_STYLE
        );
    }

    //GET请求
    public final void get() {
        request(HttpMethod.GET);
    }

    //GET请求
    public final void post() {
        if (BODY == null) {
            request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("Params must be null!");
            }
            request(HttpMethod.POST_RAW);
        }
    }

    //GET请求
    public final void put() {
        if (BODY == null) {
            request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("Params must be null!");
            }
            request(HttpMethod.PUT_RAW);
        }

    }

    //GET请求
    public final void delete() {
        request(HttpMethod.DELETE);
    }

    //文件上传
    public final void upload() {
        request(HttpMethod.UPLOAD);
    }

    //文件下载
    public final void download() {
        new DownloadHandler(
                URL, REQUEST, SUCCESS, FAILURE,
                ERROR, DOWNLOAD_DIR, EXTENSION, NAME
        ).handleDownload();
    }
}
