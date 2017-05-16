package com.xinguang.iot.common.Exception.base;

public class BaseException extends Exception {
    private static final long serialVersionUID = 1L;

    private String errorCode;

    private String errorMSG;

    public BaseException() {
        super();
    }

    public BaseException(String errorCode, String errorMSG) {
        super(errorMSG);
        this.errorCode = errorCode;
        this.errorMSG = errorMSG;
    }

    public BaseException(String message, Throwable cause, String errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
        this.errorMSG = message;
    }

    public BaseException(Throwable cause, String errorCode, String errorMSG) {
        super(cause);
        this.errorCode = errorCode;
        this.errorMSG = errorMSG;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMSG() {
        return errorMSG;
    }

    public void setErrorMSG(String errorMSG) {
        this.errorMSG = errorMSG;
    }
}