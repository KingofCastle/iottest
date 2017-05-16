package com.xinguang.iot.request;

/**
 * Created by jinx on 2017/5/15.
 */
public class DataPointAddRequest {
    private String sensorCode;
    private Long time;
    private String value;
    private String type;

    public DataPointAddRequest() {
    }

    public DataPointAddRequest(String sensorCode, Long time, String value, String type) {
        this.sensorCode = sensorCode;
        this.time = time;
        this.value = value;
        this.type = type;
    }

    public String getSensorCode() {
        return sensorCode;
    }

    public void setSensorCode(String sensorCode) {
        this.sensorCode = sensorCode;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "DataPointAddRequest{" +
                "sensorCode='" + sensorCode + '\'' +
                ", time=" + time +
                ", value='" + value + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
