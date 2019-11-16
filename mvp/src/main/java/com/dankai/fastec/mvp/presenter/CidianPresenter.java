package com.dankai.fastec.mvp.presenter;
/*
 *  项目名:    Practice
 *  包名：     com.dankai.fastec.mvp.presenter
 *  文件名:    CidianPresenter
 *  创建者:    WK
 *  时间：     2019/8/16 8:52
 *  描述：     Presenter层的实现类：CidianPresenter类
 *            由于CidianPresenter是对应Presenter层的实现类，所以要实现Presenter层的接口
 */

import android.content.Context;

import com.dankai.fastec.mvp.OnFanyiListener;
import com.dankai.fastec.mvp.model.FanyiModel;
import com.dankai.fastec.mvp.view.IFanyiView;

public class CidianPresenter implements ICidianPresenter, OnFanyiListener {

    //声明View层对应接口、Model层对应的类
    IFanyiView mIFanyiView = null;
    FanyiModel mFanyiModel = null;

    //重构函数，初始化View实例接口、Model实例
    public CidianPresenter(IFanyiView fanyiView) {
        this.mIFanyiView = fanyiView;
        mFanyiModel = new FanyiModel();
    }

    //将View层获得的数据传入Model层，注意要传递this,当前类
    @Override
    public void inputToModel(String input, Context context) {
        mFanyiModel.handleData(input, context, this);
    }


    @Override
    public void onSuccess(String str) {
        mIFanyiView.setInfo(str);
    }

    @Override
    public void onError() {
        mIFanyiView.setError();
    }

    //注：
    //a. 保留IFanyiView的引用，就可直接在CidianPresenter当前类进行UI操作而不用在Activity操作
    //b. 保留了Model层的引用就可以将View层的数据传递到Model层
}
