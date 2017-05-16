package com.xinguang.iot.exception;

import com.xinguang.iot.response.IotResponse;

/**
 * Created by Administrator on 2017/4/13.
 */
public class DeviceNotValidException extends IotBaseException {
    private String sensorCode;

    @Override
    public IotResponse.ErrorCode getErrorCode() {
        return IotResponse.ErrorCode.ERROR_DEVICE_NOT_EXIST;
    }
    public DeviceNotValidException() {

    }

    public DeviceNotValidException(String sensorCode) {
        this.sensorCode = sensorCode;
    }

    public String getSensorCode() {
        return sensorCode;
    }

    public void setSensorCode(String sensorCode) {
        this.sensorCode = sensorCode;
    }

    @Override
    public String toString() {
        return "DeviceNotValidException{" +
                "sensorCode='" + sensorCode + '\'' +
                '}';
    }
}
