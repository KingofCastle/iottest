package com.xinguang.iot.client.freezer;

import com.wordnik.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by jinx on 2017/4/26.
 */
public class FreezerListResp {
    @ApiModelProperty("冰柜列表")
    private List<FreezerItem> freezers;

    public List<FreezerItem> getFreezers() {
        return freezers;
    }

    public void setFreezers(List<FreezerItem> freezers) {
        this.freezers = freezers;
    }

    public static class FreezerItem {
        @ApiModelProperty("门店名称")
        private String storeName;
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

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
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

        public void setFreezerCode(String freezerCode) {
            this.freezerCode = freezerCode;
        }
    }
}
