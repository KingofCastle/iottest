package com.xinguang.iot.client.freezer;

import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * Created by jinx on 2017/4/27.
 */
public class FreezerDetailResp {
    @ApiModelProperty("门店名称")
    private String storeName;
    @ApiModelProperty("冰柜型号")
    private String model;
    @ApiModelProperty("体积")
    private String volume;
    @ApiModelProperty("简介")
    private String introduction;

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
