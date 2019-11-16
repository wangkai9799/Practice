package com.dankai.login.model;
/*
 *  项目名:    Practice
 *  包名：     com.dankai.login.model
 *  文件名:    LoginModel
 *  创建者:    WK
 *  时间：     2019/8/23 20:30
 *  描述：     登录接口实现类
 */

import android.service.autofill.UserData;

import com.dankai.login.model.bean.LoginParam;
import com.dankai.login.model.bean.UserProfile;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginModel implements ILogin {

    @Override
    public void goLogin(final LoginParam param, final ILoginListener iLoginListener) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    RequestBody body = new FormBody.Builder()
                            .add("phone", param.getPhone())
                            .add("pwd", param.getPwd())
                            .build();
                    Request request = new Request.Builder()
                            .url("https://github.com")
                            .post(body)
                            .build();
                    Response response = client.newCall(request).execute();
                    if (response.code() == 200) {
                        UserProfile userProfile = new UserProfile("123456", "123456");
                        iLoginListener.loginSuccess(userProfile);
                    } else {
                        iLoginListener.loginFailed("没有网络");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
