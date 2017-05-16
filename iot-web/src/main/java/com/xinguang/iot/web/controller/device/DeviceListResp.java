package com.xinguang.iot.web.controller.device;

import com.wordnik.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by jinx on 2017/4/26.
 */
public class DeviceListResp {
    @ApiModelProperty("设备列表")
    private List<DeviceItem> devices;


    public List<DeviceItem> getDevices() {
        return devices;
    }

    public void setDevices(List<DeviceItem> devices) {
        this.devices = devices;
    }

    public static class DeviceItem {
        @ApiModelProperty("设备编码")
        private String code;
        @ApiModelProperty("冰柜id")
        private String freezerId;
        @ApiModelProperty("设备类型")
        private String type;// '类型',
        @ApiModelProperty("设备名称")
        private String name;// '名称',
        @ApiModelProperty("设备简介")
        private String introduction;//'简介',

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getFreezerId() {
            return freezerId;
        }

        public void setFreezerId(String freezerId) {
            this.freezerId = freezerId;
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
