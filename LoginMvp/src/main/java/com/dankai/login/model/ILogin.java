package com.dankai.login.model;
/*
 *  项目名:    Practice
 *  包名：     com.dankai.login.model
 *  文件名:    ILogin
 *  创建者:    WK
 *  时间：     2019/8/23 20:28
 *  描述：     登录接口
 */

import com.dankai.login.model.bean.LoginParam;

public interface ILogin {

    void goLogin(LoginParam param, ILoginListener iLoginListener);

}
