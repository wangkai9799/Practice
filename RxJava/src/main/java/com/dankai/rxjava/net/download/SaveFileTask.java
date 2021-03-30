package com.dankai.rxjava.net.download;
/*
 *  项目名:    Practice
 *  包名：     com.dankai.retrofit.net.download
 *  文件名:    SaveFileTask
 *  创建者:    WK
 *  时间：     2019/8/21 14:48
 *  描述：     异步下载程序
 */

import android.os.AsyncTask;

import com.dankai.rxjava.net.callback.RestCallback;
import com.dankai.rxjava.net.util.FileUtil;

import java.io.File;
import java.io.InputStream;

import okhttp3.ResponseBody;

public class SaveFileTask extends AsyncTask<Object, Void, File> {

    private final RestCallback.IRequest REQUEST;
    private final RestCallback.ISuccess SUCCESS;
    private final RestCallback.IFailure FAILURE;
    private final RestCallback.IError ERROR;

    public SaveFileTask(RestCallback.IRequest request,
                        RestCallback.ISuccess success,
                        RestCallback.IFailure failure,
                        RestCallback.IError error
    ) {
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
    }

    @Override
    protected File doInBackground(Object... objects) {
        String downloadDir = (String) objects[0];
        String extension = (String) objects[1];
        String name = (String) objects[2];
        final ResponseBody body = (ResponseBody) objects[3];
        final InputStream is = body.byteStream();
        if (downloadDir == null || downloadDir.equals("")) {
            downloadDir = "download_dir";
        }
        if (extension == null || extension.equals("")) {
            extension = "";
        }
        if (name == null || name.equals("")) {
            return FileUtil.writeToDisk(is, downloadDir, extension.toUpperCase(), extension);
        } else {
            return FileUtil.writeToDisk(is, downloadDir, name);
        }
    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if (SUCCESS != null) {
            SUCCESS.onSuccess(file.getPath());
        }
        if (REQUEST != null) {
            //执行请求结束后的操作
            REQUEST.onRequestEnd();
        }
    }
}
