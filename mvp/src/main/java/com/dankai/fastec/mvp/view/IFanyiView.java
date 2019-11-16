package com.dankai.fastec.mvp.view;
/*
 *  项目名:    Practice
 *  包名：     com.dankai.fastec.mvp.view
 *  文件名:    IFanyiView
 *  创建者:    WK
 *  时间：     2019/8/16 8:44
 *  描述：     View接口      需定义在实现类中需要用到的方法
 */

public interface IFanyiView {

    //初始化
    void init();

    //输出翻译信息
    void setInfo(String str);

    //输出出错信息
    void setError();

}
