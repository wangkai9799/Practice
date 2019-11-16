package com.dankai.loader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //进入就显示Dialog
        MyLoader.showLoading(this);
        //显示两秒后隐藏dialog
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                MyLoader.stopLoading();
            }
        }, 2000);
    }
}