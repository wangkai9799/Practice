package com.dankai.login.model;
/*
 *  项目名:    Practice
 *  包名：     com.dankai.login.model
 *  文件名:    ILoginListener
 *  创建者:    WK
 *  时间：     2019/8/23 20:29
 *  描述：     登录接口监听
 */

import com.dankai.login.model.bean.UserProfile;

public interface ILoginListener {

    void loginSuccess(UserProfile data);

    void loginFailed(String result);

}
