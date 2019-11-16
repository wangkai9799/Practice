package com.dankai.retrofit.net.download;
/*
 *  项目名:    Practice
 *  包名：     com.dankai.retrofit.net.download
 *  文件名:    DownloadHandler
 *  创建者:    WK
 *  时间：     2019/8/21 14:42
 *  描述：     处理下载请求
 */

import android.os.AsyncTask;

import com.dankai.retrofit.net.RestCreator;
import com.dankai.retrofit.net.callback.RestCallback;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DownloadHandler {

    private final String URL;
    private static final HashMap<String, Object> PARAMS = RestCreator.getParams();
    private final RestCallback.IRequest REQUEST;
    private final RestCallback.ISuccess SUCCESS;
    private final RestCallback.IFailure FAILURE;
    private final RestCallback.IError ERROR;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;

    public DownloadHandler(String url,
                           RestCallback.IRequest request,
                           RestCallback.ISuccess success,
                           RestCallback.IFailure failure,
                           RestCallback.IError error,
                           String downloadDir,
                           String extension,
                           String name
    ) {
        this.URL = url;
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.DOWNLOAD_DIR = downloadDir;
        this.EXTENSION = extension;
        this.NAME = name;
    }

    public final void handleDownload() {
        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }
        RestCreator.getRestService().download(URL, PARAMS)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            final ResponseBody responseBody = response.body();

                            final SaveFileTask task = new SaveFileTask(REQUEST, SUCCESS, FAILURE, ERROR);
                            //以线程池的形式执行
                            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, DOWNLOAD_DIR, EXTENSION, NAME, responseBody);
                            //这里一定要注意判断，否则文件下载不全
                            if (task.isCancelled()) {
                                if (REQUEST != null) {
                                    REQUEST.onRequestEnd();
                                }
                            }
                        } else {
                            if (ERROR != null) {
                                ERROR.onError(response.code(), response.message());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        if (FAILURE != null) {
                            FAILURE.onFailure();
                        }
                    }
                });
    }
}
