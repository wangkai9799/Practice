package com.dankai.login.model.bean;
/*
 *  项目名:    Practice
 *  包名：     com.dankai.login.model.bean
 *  文件名:    UserProfile
 *  创建者:    WK
 *  时间：     2019/8/23 20:28
 *  描述：     用户信息
 */

public class UserProfile {

    private String phone;

    private String name;

    public UserProfile(String phone, String name) {
        this.phone = phone;
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
