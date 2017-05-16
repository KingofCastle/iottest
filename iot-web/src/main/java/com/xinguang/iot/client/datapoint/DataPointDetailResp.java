package com.xinguang.iot.client.datapoint;

import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * Created by jinx on 2017/4/26.
 */
public class DataPointDetailResp {
    @ApiModelProperty("请求的传感器信息")
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public DataPointDetailResp(String value) {
        this.value = value;
    }
}
