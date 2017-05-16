package com.xinguang.iot.response;

import java.io.Serializable;

/**
 * Created by jinx on 2017/5/15.
 */
public class IotResponse<D> implements Serializable {
    private boolean succeeded;
    private String errorCode;
    private String message;
    private D data;

    /**
     * succeeded
     * @param data
     */
    public IotResponse(D data) {
        this.data = data;
        this.succeeded = true;
    }

    /**
     * failed
     * @param errorCode
     */
    public IotResponse(ErrorCode errorCode) {
        this.errorCode = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.succeeded = false;
    }

    public IotResponse(ErrorCode errorCode, String message) {
        this.errorCode = errorCode.getCode();
        if (null == message || message.length() == 0) {
            this.message = errorCode.getMessage();
        } else {
            this.message = message;
        }
        this.succeeded = false;
    }

    public enum ErrorCode {
        ERROR_SYSTEM_ERROR("9000001", "系统错误"),

        ERROR_DEVICE_NOT_EXIST("0000001", "该设备未注册"),
        ERROR_DEVICE_NOT_VALID("0000002", "该设备未通过鉴权"),
        ERROR_DATAPOINT_NOT_EXIST("0000003", "数据点不存在"),
        ERROR_DATAPOINT_INSERT_FAIL("0000004", "创建数据点失败"),
        ERROR_PARAM_ILLEGAL("0000014", "参数异常");

        ErrorCode(String code, String message) {
            this.code = code;
            this.message = message;
        }
        private String code;
        private String message;

        public String getCode() {
            return code;
        }
        public String getMessage() {
            return message;
        }
    }

    public boolean isSucceeded() {
        return succeeded;
    }

    public void setSucceeded(boolean succeeded) {
        this.succeeded = succeeded;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public D getData() {
        return data;
    }

    public void setData(D data) {
        this.data = data;
    }
}
