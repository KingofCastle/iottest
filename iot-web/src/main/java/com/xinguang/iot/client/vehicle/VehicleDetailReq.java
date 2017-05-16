package com.xinguang.iot.client.vehicle;

import com.wordnik.swagger.annotations.ApiModelProperty;
import com.xinguang.iot.client.ClientReq;

/**
 * Created by jinx on 2017/4/27.
 */
public class VehicleDetailReq extends ClientReq{
    @ApiModelProperty("车牌")
    private String plant;

    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }
}
