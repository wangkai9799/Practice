package com.dankai.fragmentation;
/*
 *  项目名:    Practice
 *  包名：     com.dankai.fragmentation.base
 *  文件名:    MainDelegate
 *  创建者:    WK
 *  时间：     2019/8/19 11:09
 *  描述：     主Delegate
 */

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.dankai.fragmentation.base.TestDelegate;
import com.dankai.fragmentation.base.delegate.BaseDelegate;

public class MainDelegate extends BaseDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_main;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View view) {
        Button button = view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startWithPop(new TestDelegate());
            }
        });
    }

}
