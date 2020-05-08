package com.kbit.kbnetworklib.network;

import android.text.TextUtils;

import com.kbit.kbbaselib.context.ContextProvider;
import com.kbit.kbbaselib.encrypt.EncryptTool;
import com.kbit.kbbaselib.util.PackageUtil;
import com.kbit.kbnetworklib.config.NetworkSetting;
import com.kbit.kbnetworklib.status.NetworkStatus;

import java.io.IOException;
import java.net.ConnectException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {

    public HeaderInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        if (!NetworkStatus.isConnected(ContextProvider.getContext())) {
            throw new ConnectException();
        }

        long time = System.currentTimeMillis() / 1000;
        String accessToken = NetworkSetting.getToken();
        if (accessToken == null) {
            accessToken = "";
        }

        String pushToken = NetworkSetting.getPushToken();
        if (pushToken == null) {
            pushToken = "";
        }
        String key = EncryptTool.get32MD5(accessToken + time + NetworkSetting.getAppKey());
        if (TextUtils.isEmpty(key)) {
            key = "";
        }

        String appId = NetworkSetting.getAppId();
        if (appId == null) {
            appId = "";
        }

        Request.Builder builder = chain.request().newBuilder();
        builder.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        builder.addHeader("Cache-Control", "no-cache");
        builder.addHeader("version", PackageUtil.getVersionName(ContextProvider.getContext()));
        builder.addHeader("system", "android");
        builder.addHeader("timestamp", time + "");
        builder.addHeader("appid", appId);
        builder.addHeader("encrypt", key);
        builder.addHeader("token", accessToken);
        builder.addHeader("pushtoken", pushToken);
        return chain.proceed(builder.build());

    }
}
