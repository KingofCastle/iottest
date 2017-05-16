package com.xinguang.iot.web.controller.sensor;

import com.wordnik.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by jinx on 2017/4/26.
 */
public class SensorListResp {
    @ApiModelProperty("传感器列表")
    private List<SensorItem> sensors;

    public List<SensorItem> getSensors() {
        return sensors;
    }

    public void setSensors(List<SensorItem> sensors) {
        this.sensors = sensors;
    }
    public static class SensorItem {
        @ApiModelProperty("传感器编码")
        private String code;
        @ApiModelProperty("设备id")
        private String deviceId;
        @ApiModelProperty("传感器类型")
        private String type;// '类型',
        @ApiModelProperty("名称")
        private String name;// '名称',
        @ApiModelProperty("简介")
        private String introduction;//'简介',

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

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
    }
}
