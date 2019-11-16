# 一些常用框架的使用
## Okhttp3
### 1.添加依赖
    implementation 'com.squareup.okhttp3:okhttp:4.0.1'
Okhttp3官方地址 [Okhttp3](https://github.com/square/okhttp)
### 2.在Manifest中添加网络权限
    <uses-permission android:name="android.permission.INTERNET"/>
### 3.使用
以下代码全部在子线程中运行
#### 1.默认请求
    //默认请求为get
    OkHttpClient okHttpClient = new OkHttpClient();
    Request request = new Request.Builder()
        .url(strings[0])
        .build();
    Response response = okHttpClient.newCall(request).execute();
    String responseData = response.body().string();
#### 2.post表单请求
    //使用RequestBody添加表单
    OkHttpClient okHttpClient = new OkHttpClient();
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
## Retrofit
### 1.添加依赖
    //因为Retrofit是基于OKhttp3，所以还要添加OKhttp3的依赖
    implementation 'com.squareup.okhttp3:okhttp:4.0.1'
    implementation 'com.squareup.retrofit2:retrofit:2.6.0'
### 2.在Manifest中添加权限
    <uses-permission android:name="android.permission.INTERNET"/>
### 3.创建 接收服务器返回数据的类
### 4.创建 用于描述网络请求 的接口
### 5.创建 Retrofit实例
### 6.创建 网络请求接口实例 并 配置网络请求参数
### 7.发送网络请求（异步/同步）
### 8.处理服务器返回的数据