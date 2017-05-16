package com.xinguang.iot.client.freezer;

import com.wordnik.swagger.annotations.ApiModelProperty;
import com.xinguang.iot.client.ClientReq;

/**
 * Created by jinx on 2017/4/27.
 */
public class FreezerDetailReq extends ClientReq{
    @ApiModelProperty("编号")
    private String freezerCode;

    public String getFreezerCode() {
        return freezerCode;
    }

    public void setFreezerCode(String freezerCode) {
        this.freezerCode = freezerCode;
    }
}
