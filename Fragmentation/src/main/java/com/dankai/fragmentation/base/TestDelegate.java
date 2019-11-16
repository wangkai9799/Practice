package com.dankai.fragmentation.base;
/*
 *  项目名:    Practice
 *  包名：     com.dankai.fragmentation.base
 *  文件名:    TestDelegate
 *  创建者:    WK
 *  时间：     2019/8/19 11:48
 *  描述：     第二个Delegate     测试Delegate
 */

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;

import com.dankai.fragmentation.R;
import com.dankai.fragmentation.base.delegate.BaseDelegate;

public class TestDelegate extends BaseDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_test;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View view) {

    }
}
