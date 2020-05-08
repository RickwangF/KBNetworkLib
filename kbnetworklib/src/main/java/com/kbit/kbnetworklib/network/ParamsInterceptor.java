package com.kbit.kbnetworklib.network;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ParamsInterceptor implements Interceptor {

    private String token;
    private String udid;

    public ParamsInterceptor(){
        this.token = "";
        this.udid = "";
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        if(request.method().equals("GET"))
        {
            HttpUrl httpUrl = request.url().newBuilder()
                    .addQueryParameter("udid",udid)
                    .addQueryParameter("token",token)
                    .build();
            request = request.newBuilder().url(httpUrl).build();
        }
        else if(request.method().equals("POST"))
        {
            if(request.body()instanceof FormBody)
            {
                FormBody formBody = (FormBody) request.body();

                FormBody.Builder bodyBuilder = new FormBody.Builder();
                for(int i=0;i<formBody.size();i++)
                {
                    bodyBuilder.addEncoded(formBody.encodedName(i),formBody.encodedValue(i));
                }

                formBody = bodyBuilder
                        .addEncoded("udid",udid)
                        .addEncoded("token",token)
                        .build();

                request = request.newBuilder().post(formBody).build();
            }
        }
        return chain.proceed(request);
    }
}
