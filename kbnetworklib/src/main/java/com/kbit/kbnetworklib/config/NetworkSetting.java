package com.kbit.kbnetworklib.config;

public class NetworkSetting {
    private String appId;

    private String appKey;

    private String token;

    private String pushToken;

    private String baseUrl;

    private static NetworkSetting instance = null;

    private static NetworkSetting getInstance() {
        if (instance == null) {
            instance = new NetworkSetting();
        }
        return instance;
    }

    public static void setAppId(String appId) {
        getInstance().appId = appId;
    }

    public static void setAppKey(String appKey) {
        getInstance().appKey = appKey;
    }

    public static void setToken(String token) {
        getInstance().token = token;
    }

    public static void setPushToken(String pushToken) {
        getInstance().pushToken = pushToken;
    }

    public static void setBaseUrl(String baseUrl) {
        getInstance().baseUrl = baseUrl;
    }

    public static String getAppId() {
        return getInstance().appId;
    }

    public static String getAppKey() {
        return getInstance().appKey;
    }

    public static String getToken() {
        return getInstance().token;
    }

    public static String getPushToken() {
        return getInstance().pushToken;
    }

    public static String getBaseUrl() {
        return getInstance().baseUrl;
    }
}
