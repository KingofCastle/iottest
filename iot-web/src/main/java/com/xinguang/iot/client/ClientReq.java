package com.xinguang.iot.client;/**
 * Created by hanyong on 2017/1/4.
 */

import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @author hanyong
 * @Date 2017/1/4
 */
public class ClientReq {
    @ApiModelProperty("apiKey")
    private String apiKey;


    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
