package com.xinguang.iot.web.controller;

import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * Created by jinx on 2017/5/12.
 */
public class BaseReq {
    @ApiModelProperty("token")
    private String token;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
