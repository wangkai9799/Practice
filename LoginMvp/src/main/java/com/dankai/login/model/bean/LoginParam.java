package com.dankai.login.model.bean;
/*
 *  项目名:    Practice
 *  包名：     com.dankai.login.model.bean
 *  文件名:    LoginParam
 *  创建者:    WK
 *  时间：     2019/8/23 20:28
 *  描述：     登录参数
 */

public class LoginParam {

    private String phone;

    private String pwd;

    public LoginParam(String phone, String pwd) {
        this.phone = phone;
        this.pwd = pwd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
