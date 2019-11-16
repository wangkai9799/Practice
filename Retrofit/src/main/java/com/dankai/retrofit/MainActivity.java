package com.dankai.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dankai.retrofit.net.RestClient;
import com.dankai.retrofit.net.callback.RestCallback;

public class MainActivity extends AppCompatActivity {

    Button mButton;
    TextView mTextView;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = findViewById(R.id.button);
        mTextView = findViewById(R.id.text);
        mContext = this;
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RestClient.builder()
                        //如果这里是完整的地址，那么Retrofit中的baseUrl就没有作用
                        //如果这里不是完整地址，请求地址为baseUrl+url
                        .url("http://beauty.wangdankai.cn/beauty (1).jpg")
                        //请求参数
                        .params("name", "dankai")
                        .loader(mContext)
                        //请求成功回调
                        .success(new RestCallback.ISuccess() {
                            @Override
                            public void onSuccess(String response) {
                                //请求成功后的操作

                            }
                        })
                        //请求错误回调
                        .error(new RestCallback.IError() {
                            @Override
                            public void onError(int code, String msg) {
                                //请求错误后的操作
                                Toast.makeText(MainActivity.this, "code:" + code + "message" + msg, Toast.LENGTH_SHORT).show();
                            }
                        })
                        //请求失败回调
                        .failure(new RestCallback.IFailure() {
                            @Override
                            public void onFailure() {
                                //请求失败后的操作
                                Toast.makeText(MainActivity.this, "失败", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .build()
                        .get();
            }
        });
    }
}
