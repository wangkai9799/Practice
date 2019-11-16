package com.dankai.testqiniuyun;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.qiniu.android.storage.UploadManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UploadManager manager = new UploadManager();
    }
}
