package com.example.mahiti.json3;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SuccessCase {

    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("data")
    @Expose
    private List<Data> data = null;
    @SerializedName("serverdatetime")
    @Expose
    private String serverdatetime;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public String getServerdatetime() {
        return serverdatetime;
    }

    public void setServerdatetime(String serverdatetime) {
        this.serverdatetime = serverdatetime;
    }
}


