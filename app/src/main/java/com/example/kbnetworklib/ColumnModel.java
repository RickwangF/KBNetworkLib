package com.example.kbnetworklib;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ColumnModel {
    @SerializedName("order_id")
    private int orderId;

    @SerializedName("cat_id")
    private int catId;

    @SerializedName("cat_pid")
    private int parentId;

    private String title;

    private String logo;

    @SerializedName("children")
    private List<ColumnModel> children;

    @SerializedName("open_type")
    private int openType;

    @SerializedName("open_value")
    private String openValue;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public List<ColumnModel> getChildren() {
        return children;
    }

    public void setChildren(List<ColumnModel> children) {
        this.children = children;
    }

    public int getOpenType() {
        return openType;
    }

    public void setOpenType(int openType) {
        this.openType = openType;
    }

    public String getOpenValue() {
        return openValue;
    }

    public void setOpenValue(String openValue) {
        this.openValue = openValue;
    }
}
