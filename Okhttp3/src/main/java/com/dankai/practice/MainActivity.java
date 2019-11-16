package com.dankai.practice;

import android.os.AsyncTask;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        Button button1 = findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //异步GET请求
                new MyTask().execute("https://www.baidu.com");
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //异步POST请求
                new MyTask1().execute("https://www.baidu.com");
            }
        });
    }

    //异步POST请求
    class MyTask1 extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String responseData = null;
            try {
                OkHttpClient okHttpClient = new OkHttpClient();
                //POST表单
                RequestBody requestBody = new FormBody.Builder()
                        .add("username", "dandan")
                        .add("pwd", "123456")
                        .build();
                Request request = new Request.Builder()
                        .post(requestBody)
                        .url(strings[0])
                        .build();
                Response response = okHttpClient.newCall(request).execute();
                responseData = response.body().string();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return responseData;
        }

        @Override
        protected void onPostExecute(String s) {
            TextView textView = findViewById(R.id.text);
            textView.setText(s);
            super.onPostExecute(s);
        }
    }

    //异步GET请求
    class MyTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String responseData = null;
            try {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(strings[0])
                        .build();
                Response response = okHttpClient.newCall(request).execute();
                responseData = response.body().string();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return responseData;
        }

        @Override
        protected void onPostExecute(String s) {
            TextView textView = findViewById(R.id.text);
            textView.setText(s);
            super.onPostExecute(s);
        }
    }
}
