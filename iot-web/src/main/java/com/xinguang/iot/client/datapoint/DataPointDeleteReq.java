package com.xinguang.iot.client.datapoint;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.xinguang.iot.client.ClientReq;

/**
 * Created by jinx on 2017/4/26.
 */

@ApiModel(value = "删除数据点请求参数")
public class DataPointDeleteReq extends ClientReq{
    @ApiModelProperty("传感器编号")
    private String sensorCode;
    @ApiModelProperty("时间戳")
    private Long time;
    @ApiModelProperty("数据类型:0-温度;1-湿度;2-GPS")
    private String type;

    public String getSensorCode() {
        return sensorCode;
    }

    public void setSensorCode(String sensorCode) {
        this.sensorCode = sensorCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
