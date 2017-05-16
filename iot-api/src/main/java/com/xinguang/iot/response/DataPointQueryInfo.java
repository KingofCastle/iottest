package com.xinguang.iot.response;

import java.io.Serializable;

/**
 * Created by jinx on 2017/5/15.
 */
public class DataPointQueryInfo implements Serializable {

    private String sensorCode;

    private String temValue;

    private String humValue;

    public DataPointQueryInfo() {
    }

    public DataPointQueryInfo(String sensorCode, String temValue, String humValue) {
        this.sensorCode = sensorCode;
        this.temValue = temValue;
        this.humValue = humValue;
    }

    public String getSensorCode() {
        return sensorCode;
    }

    public void setSensorCode(String sensorCode) {
        this.sensorCode = sensorCode;
    }

    public String getTemValue() {
        return temValue;
    }

    public void setTemValue(String temValue) {
        this.temValue = temValue;
    }

    public String getHumValue() {
        return humValue;
    }

    public void setHumValue(String humValue) {
        this.humValue = humValue;
    }

    @Override
    public String toString() {
        return "DataPointQueryInfo{" +
                "sensorCode='" + sensorCode + '\'' +
                ", temValue='" + temValue + '\'' +
                ", humValue='" + humValue + '\'' +
                '}';
    }
}
