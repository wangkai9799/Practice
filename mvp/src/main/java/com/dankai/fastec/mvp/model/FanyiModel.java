package com.dankai.fastec.mvp.model;
/*
 *  项目名:    Practice
 *  包名：     com.dankai.fastec.mvp.model
 *  文件名:    FanyiModel
 *  创建者:    WK
 *  时间：     2019/8/16 9:02
 *  描述：     Model层的实现类：FanyiModel类
 *            注：由于FanyiModel是对应Model层的实现类，所以要实现Model层的接口
 */

import android.content.Context;

import com.dankai.fastec.mvp.OnFanyiListener;

public class FanyiModel implements IFanyi {
    
    @Override
    public void handleData(String input, Context context, OnFanyiListener listener) {

    }

    @Override
    public String fanyiToString(IFanyi fanyi) {
        return null;
    }
}
