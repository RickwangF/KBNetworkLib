package com.kbit.kbnetworklib.network;

import android.os.NetworkOnMainThreadException;
import android.util.Log;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class HttpCallback<T> implements Callback<HttpResponse<T>> {
    private static final int TASK_NONETWORK = -2;//无网络
    private final int RESPONSE_FATAL_ERROR = -1; //返回其他错误

    private int errorCode = -1;
    private String errorMsg = "未知的错误！";

    private int retryTimes = 0;
    private int maxRetryTimes = 1;
    private boolean retryFlag = false;

    public HttpCallback(boolean retryFlag) {
        this.retryFlag = retryFlag;
    }

    //正确回调
    public abstract void onSuccess(T t);

    public void onUnAuthorize() {

    }

    @Override
    public void onResponse(Call<HttpResponse<T>> call, final Response<HttpResponse<T>> response) {
        if (response.isSuccessful()){
            if (response.body() == null) {
                onFailMessage("获取数据失败", RESPONSE_FATAL_ERROR);
                return;
            }
            int code = response.body().getCode();
            if (code != 0) {
                if(code > 0) {
                    String errorMsg = "";
                    if (response.body().getMessage() != null) {
                        errorMsg = response.body().getMessage();
                        Log.e("Network", "message is " + errorMsg);
                    }
                    switch (code) {
                        case 401:
                            onUnAuthorize();
                            return;
                        case 404:
                            onFailMessage("地址错误", RESPONSE_FATAL_ERROR);
                            return;
                        case 500:
                            onFailMessage("服务器错误，" + errorMsg, RESPONSE_FATAL_ERROR );
                            return;
                        case 502:
                            onFailMessage("连接服务器失败，" + errorMsg, RESPONSE_FATAL_ERROR);
                            return;
                    }
                }
                onFailMessage(response.body().getMessage(), RESPONSE_FATAL_ERROR);
                return;
            }
            onSuccess(response.body().getData());
        }
        else {
            if(retryTimes < maxRetryTimes && retryFlag) {
                retryTimes++;
                retry(call);
            }
            else {
                onFailMessage("请求出错, " + response.code() + ":" + response.message(), RESPONSE_FATAL_ERROR);
                String message = response.message();
                String errorBodyString = "";
                if (response.errorBody() != null) {
                    errorBodyString = response.errorBody().toString();
                }
                Log.e("Network", "请求出错，" + response.code() + ":" + message + "==>" + errorBodyString);
            }
        }
    }

    //错误回调
    public abstract void onFailMessage(String msg,int code);
    @Override
    public void onFailure(Call<HttpResponse<T>> call, Throwable t) {
        if(retryTimes < maxRetryTimes && retryFlag)
        {
            retryTimes++;
            retry(call);
        }
        else{
            if (t instanceof SocketTimeoutException) {  //VPN open
                errorCode = RESPONSE_FATAL_ERROR;
                errorMsg = "服务器响应超时";
            } else if (t instanceof ConnectException) {
                errorCode = TASK_NONETWORK;
                errorMsg = "网络连接异常，请检查网络";
            }else if (t instanceof UnknownHostException) {
                errorCode = RESPONSE_FATAL_ERROR;
                errorMsg = "无法解析主机，请检查网络连接";
            } else if (t instanceof UnknownServiceException) {
                errorCode = RESPONSE_FATAL_ERROR;
                errorMsg = "未知的服务器错误";
            } else if (t instanceof IOException) {   //飞行模式等
                errorCode = TASK_NONETWORK;
                errorMsg = "没有网络，请检查网络连接";
            } else if (t instanceof NetworkOnMainThreadException) {//主线程不能网络请求，这个很容易发现
                errorCode = RESPONSE_FATAL_ERROR;
                errorMsg = "主线程不能网络请求";
                // ... ...
            } else if (t instanceof RuntimeException) { //很多的错误都是extends RuntimeException
                errorCode = RESPONSE_FATAL_ERROR;
                errorMsg = "JSON数据解析错误";
            }
            onFailMessage(errorMsg,errorCode);
        }
    }



    public void retry(Call<HttpResponse<T>> call)
    {
        call.clone().enqueue(this);
    }
}
