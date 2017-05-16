package com.xinguang.iot.client.datapoint;

import com.wordnik.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by jinx on 2017/4/26.
 */
public class DataPointHistoryResp {
    @ApiModelProperty("历史数据点")
    private List<ResItem> values;

    public List<ResItem> getValues() {
        return values;
    }

    public void setValues(List<ResItem> values) {
        this.values = values;
    }

    public static class ResItem {
        @ApiModelProperty("时间戳")
        private Long time;
        @ApiModelProperty("数值")
        private String value;

        public Long getTime() {
            return time;
        }

        public void setTime(Long time) {
            this.time = time;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
