package com.xinguang.iot.client.freezer;

import com.wordnik.swagger.annotations.ApiModelProperty;
import com.xinguang.iot.client.ClientReq;

/**
 * Created by jinx on 2017/4/27.
 */
public class FreezerEditReq extends ClientReq{

    @ApiModelProperty("门店ID")
    private String storeId;
    @ApiModelProperty("冰柜型号")
    private String model;
    @ApiModelProperty("体积")
    private String volume;
    @ApiModelProperty("简介")
    private String introduction;
    @ApiModelProperty("编号")
    private String freezerCode;

    public String getFreezerCode() {
        return freezerCode;
    }

    public void setFreezerCode(String freezerCode) {
        this.freezerCode = freezerCode;
    }
    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
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
