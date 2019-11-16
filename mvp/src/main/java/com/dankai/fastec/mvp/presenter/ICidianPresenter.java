package com.dankai.fastec.mvp.presenter;
/*
 *  项目名:    Practice
 *  包名：     com.dankai.fastec.mvp.presenter
 *  文件名:    ICidianPresenter
 *  创建者:    WK
 *  时间：     2019/8/16 8:49
 *  描述：     Presenter接口： ICidianPresenter   需定义在实现类中需要用到的方法
 */

import android.content.Context;

public interface ICidianPresenter {

    //将View层获得的数据传入Model层
    void inputToModel(String input, Context context);

}
