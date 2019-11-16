package com.dankai.login.view;
/*
 *  项目名:    Practice
 *  包名：     com.dankai.login.view
 *  文件名:    ILoginView
 *  创建者:    WK
 *  时间：     2019/8/23 20:30
 *  描述：     View接口设计
 */

import android.service.autofill.UserData;

public interface ILoginView {

    void loginSuccess(UserData data);

    void loginFile(String result);

    void showLoading();

    void hideLoading();

}
