package com.dankai.fastec.mvp.model;
/*
 *  项目名:    Practice
 *  包名：     com.dankai.fastec.mvp.model
 *  文件名:    IFanyi
 *  创建者:    WK
 *  时间：     2019/8/16 9:01
 *  描述：     TODO
 */

import android.content.Context;

import com.dankai.fastec.mvp.OnFanyiListener;

public interface IFanyi {

    void handleData(String input, Context context, final OnFanyiListener listener);

    String fanyiToString(IFanyi fanyi);

}
