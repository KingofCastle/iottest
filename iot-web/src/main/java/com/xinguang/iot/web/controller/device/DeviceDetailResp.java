package com.xinguang.iot.web.controller.device;

import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * Created by jinx on 2017/4/26.
 */
public class DeviceDetailResp {
    @ApiModelProperty("设备类型")
    private String type;
    @ApiModelProperty("设备名称")
    private String name;
    @ApiModelProperty("设备简介")
    private String introduction;
    @ApiModelProperty("设备标签")
    private String tags;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
