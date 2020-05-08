package com.example.kbnetworklib;

import com.google.gson.annotations.SerializedName;

public class ConfigModel {
    @SerializedName("app_status")
    private String appStatus;

    @SerializedName("is_gray")
    private String isGray;

    @SerializedName("app_user_protocol_url")
    private String userProtocolUrl;

    @SerializedName("app_network_protocol_url")
    private String networkProtocolUrl;

    @SerializedName("app_privacy_policy_url")
    private String privacyPolicyUrl;

    @SerializedName("app_about_us_url")
    private String aboutUsUrl;

    @SerializedName("app_api_http")
    private String apiHttp;

    @SerializedName("app_open_time")
    private String openTime;

    @SerializedName("default_share_icon")
    private String defaultShareIcon;

    @SerializedName("app_img_http")
    private String imgHttp;

    @SerializedName("is_login_sina")
    private String isLoginSina;

    @SerializedName("is_login_qq")
    private String isLoginQQ;

    @SerializedName("is_login_wexin")
    private String isLoginWexin;

    @SerializedName("app_name")
    private String appName;

    @SerializedName("app_start_image")
    private String startImage;

    @SerializedName("app_start_image_open_type")
    private String startImageOpenType;

    @SerializedName("app_start_image_open_value")
    private String startImageOpenValue;

    @SerializedName("has_user_model")
    private String hasUserModel;

    public ConfigModel() {
    }

    public String getAppStatus() {
        return appStatus;
    }

    public void setAppStatus(String appStatus) {
        this.appStatus = appStatus;
    }

    public String getIsGray() {
        return isGray;
    }

    public void setIsGray(String isGray) {
        this.isGray = isGray;
    }

    public String getUserProtocolUrl() {
        return userProtocolUrl;
    }

    public void setUserProtocolUrl(String userProtocolUrl) {
        this.userProtocolUrl = userProtocolUrl;
    }

    public String getNetworkProtocolUrl() {
        return networkProtocolUrl;
    }

    public void setNetworkProtocolUrl(String networkProtocolUrl) {
        this.networkProtocolUrl = networkProtocolUrl;
    }

    public String getPrivacyPolicyUrl() {
        return privacyPolicyUrl;
    }

    public void setPrivacyPolicyUrl(String privacyPolicyUrl) {
        this.privacyPolicyUrl = privacyPolicyUrl;
    }

    public String getAboutUsUrl() {
        return aboutUsUrl;
    }

    public void setAboutUsUrl(String aboutUsUrl) {
        this.aboutUsUrl = aboutUsUrl;
    }

    public String getApiHttp() {
        return apiHttp;
    }

    public void setApiHttp(String apiHttp) {
        this.apiHttp = apiHttp;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getDefaultShareIcon() {
        return defaultShareIcon;
    }

    public void setDefaultShareIcon(String defaultShareIcon) {
        this.defaultShareIcon = defaultShareIcon;
    }

    public String getImgHttp() {
        return imgHttp;
    }

    public void setImgHttp(String imgHttp) {
        this.imgHttp = imgHttp;
    }

    public String getIsLoginSina() {
        return isLoginSina;
    }

    public void setIsLoginSina(String isLoginSina) {
        this.isLoginSina = isLoginSina;
    }

    public String getIsLoginQQ() {
        return isLoginQQ;
    }

    public void setIsLoginQQ(String isLoginQQ) {
        this.isLoginQQ = isLoginQQ;
    }

    public String getIsLoginWexin() {
        return isLoginWexin;
    }

    public void setIsLoginWexin(String isLoginWexin) {
        this.isLoginWexin = isLoginWexin;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getStartImage() {
        return startImage;
    }

    public void setStartImage(String startImage) {
        this.startImage = startImage;
    }

    public String getStartImageOpenType() {
        return startImageOpenType;
    }

    public void setStartImageOpenType(String startImageOpenType) {
        this.startImageOpenType = startImageOpenType;
    }

    public String getStartImageOpenValue() {
        return startImageOpenValue;
    }

    public void setStartImageOpenValue(String startImageOpenValue) {
        this.startImageOpenValue = startImageOpenValue;
    }

    public String getHasUserModel() {
        return hasUserModel;
    }

    public void setHasUserModel(String hasUserModel) {
        this.hasUserModel = hasUserModel;
    }
}
