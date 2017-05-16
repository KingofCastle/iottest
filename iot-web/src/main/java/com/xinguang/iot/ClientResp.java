package com.xinguang.iot;


import com.wordnik.swagger.annotations.ApiModelProperty;
import com.xinguang.iot.common.constants.ClientResponeConstants;

public class ClientResp<T> {

    @ApiModelProperty("响应码")
    private String resultCode;
    @ApiModelProperty("响应描述")
    private String resultDesc;
    @ApiModelProperty("响应数据")
    private T resultData;

    public ClientResp() {
        this.setResultCode(ClientResponeConstants.RESULT_CODE_SUCCESS);
        this.setResultDesc("成功");
    }

    public String getResultCode() {
        return resultCode;
    }
    public String getResultDesc() {
        return resultDesc;
    }
    public T getResultData() {
        return resultData;
    }
    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }
    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }
    public void setResultData(T resultData) {
        this.resultData = resultData;
    }

    @Override
    public String toString() {
        return "ClientResp [resultCode=" + resultCode + ", resultDesc="
                + resultDesc + ", resultData=" + resultData + "]";
    }

}
