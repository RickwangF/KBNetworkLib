package com.example.kbnetworklib;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ConfigResponse {
    private ConfigModel config;

    @SerializedName("bottom_btn")
    private List<ColumnModel> bottomBtn;

    public ConfigResponse() {
    }

    public ConfigModel getConfig() {
        return config;
    }

    public void setConfig(ConfigModel config) {
        this.config = config;
    }

    public List<ColumnModel> getBottomBtn() {
        return bottomBtn;
    }

    public void setBottomBtn(List<ColumnModel> bottomBtn) {
        this.bottomBtn = bottomBtn;
    }
}
