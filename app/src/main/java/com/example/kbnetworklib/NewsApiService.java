package com.example.kbnetworklib;

import com.kbit.kbnetworklib.network.HttpResponse;

import retrofit2.Call;
import retrofit2.http.POST;

public interface NewsApiService {
    @POST("config/app_config")
    Call<HttpResponse<ConfigResponse>> configApp();
}
