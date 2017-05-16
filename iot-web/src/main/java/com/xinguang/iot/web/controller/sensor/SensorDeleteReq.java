package com.xinguang.iot.web.controller.sensor;

import com.wordnik.swagger.annotations.ApiModelProperty;
import com.xinguang.iot.client.ClientReq;

/**
 * Created by jinx on 2017/4/26.
 */
public class SensorDeleteReq extends ClientReq{
    @ApiModelProperty("传感器编号")
    private String sensorCode;

    public String getSensorCode() {
        return sensorCode;
    }

    public void setSensorCode(String sensorCode) {
        this.sensorCode = sensorCode;
    }
}
