package com.xinguang.iot.client.datapoint;

import com.wordnik.swagger.annotations.ApiModelProperty;
import com.xinguang.iot.client.ClientReq;

/**
 * Created by jinx on 2017/4/26.
 */
public class DataPointHistoryReq extends ClientReq{
    @ApiModelProperty("传感器编号")
    private String sensorCode;
    @ApiModelProperty("开始时间")
    private Long start;
    @ApiModelProperty("结束时间")
    private Long end;
    @ApiModelProperty("数据类型:0-温度;1-湿度;2-GPS")
    private String type;
    @ApiModelProperty("页码")
    private String pageNum;
    @ApiModelProperty("每页数量")
    private String pageSize;

    public String getSensorCode() {
        return sensorCode;
    }

    public void setSensorCode(String sensorCode) {
        this.sensorCode = sensorCode;
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getEnd() {
        return end;
    }

    public void setEnd(Long end) {
        this.end = end;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }
}
