package com.example.kbnetworklib;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.kbit.kbbaselib.util.JsonUtil;
import com.kbit.kbnetworklib.config.NetworkSetting;
import com.kbit.kbnetworklib.network.HttpCallback;
import com.kbit.kbnetworklib.network.HttpResponse;
import com.kbit.kbnetworklib.network.HttpService;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NetworkSetting.setAppId("1JpJePhKKke2ls3L");
        NetworkSetting.setAppKey("w6QK2LBvy9TRtBrfJMw90pU8Mk8zhvz9");
        NetworkSetting.setBaseUrl("http://zsccyun-sf-2020-test.kmzscc.com/");
        NewsApiService service = HttpService.createInstance("").create(NewsApiService.class);
        Call<HttpResponse<ConfigResponse>> call = service.configApp();
        call.enqueue(new HttpCallback<ConfigResponse>(false) {
            @Override
            public void onSuccess(ConfigResponse configResponse) {
                if (configResponse != null) {
                    Log.e("network", "config response is " + JsonUtil.bean2Json(configResponse));
                }
            }

            @Override
            public void onFailMessage(String msg, int code) {
                Log.e("network", "fail message " + msg);
            }

            @Override
            public void onUnAuthorize() {

            }
        });
    }
}
