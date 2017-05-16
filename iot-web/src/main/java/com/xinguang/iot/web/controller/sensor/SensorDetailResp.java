package com.xinguang.iot.web.controller.sensor;

import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * Created by jinx on 2017/4/26.
 */
public class SensorDetailResp {
    @ApiModelProperty("传感器类型")
    private String type;
    @ApiModelProperty("传感器名称")
    private String name;
    @ApiModelProperty("传感器简介")
    private String introduction;
    @ApiModelProperty("传感器标签")
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
