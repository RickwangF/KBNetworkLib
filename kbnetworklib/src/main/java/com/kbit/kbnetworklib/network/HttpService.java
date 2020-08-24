package com.kbit.kbnetworklib.network;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kbit.kbnetworklib.config.NetworkSetting;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpService {
    private static final int DEFAULT_TIME_OUT = 30;//超时时间 3s
    private static final int DEFAULT_READ_TIME_OUT = 30;
    private Retrofit mRetrofit = null;

    private HttpService(String baseUrl, Interceptor headers, Interceptor commonParams, Interceptor cache) {
        // 创建 OKHttpClient
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);//连接超时时间
        builder.writeTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS);//写操作 超时时间
        builder.readTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS);//读操作超时时间
        // 公共的请求头拦截器
        if (headers == null) {
            builder.addInterceptor(new HeaderInterceptor());
        } else {
            builder.addInterceptor(headers);
        }
        // 添加log拦截器
        HttpLoggingInterceptor.Logger DEFAULT = new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                HttpService.log(message);
            }
        };
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(DEFAULT);
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(loggingInterceptor);
        // 添加公共参数拦截器
        // builder.addInterceptor(new ParamsInterceptor());
        //缓存拦截器
        //builder.addNetworkInterceptor(new CacheInterceptor(context));
        // 创建配置过的gson对象
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeNulls();
        Gson gson = gsonBuilder.create();

        if (baseUrl == null || baseUrl.equals("")) {
            if (NetworkSetting.getBaseUrl() != null && !NetworkSetting.getBaseUrl().equals("")) {
                baseUrl = NetworkSetting.getBaseUrl();
            } else {
                baseUrl = "";
            }
        }

        // 创建Retrofit
        mRetrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(baseUrl)
                .build();
    }

    /**
     * 获取RetrofitServiceManager
     *
     * @return
     */
    public static HttpService createInstance(String baseUrl) {
        return new HttpService(baseUrl, null, null, null);
    }

    public static HttpService createInstance(String baseUrl, Interceptor headers, Interceptor commonParams, Interceptor cache) {
        return new HttpService(baseUrl, headers, commonParams, cache);
    }

    /**
     * 获取对应的Service
     *
     * @param service Service 的 class
     * @param <T>
     * @return
     */
    public <T> T create(Class<T> service) {
        return mRetrofit.create(service);
    }

    public static void log(String message) {
        if (message.length() > 4000) {
            // integer division
            int chunkCount = message.length() / 4000;
            for (int i = 0; i <= chunkCount; i++) {
                int max = 4000 * (i + 1);
                if (max >= message.length()) {
                    Log.e("======", "chunk " + i + " of " + chunkCount + ":" + message.substring(4000 * i));
                } else {
                    Log.e("======", "chunk " + i + " of " + chunkCount + ":" + message.substring(4000 * i, max));
                }
            }
        } else {
            Log.e("======", message);
        }
    }
}
