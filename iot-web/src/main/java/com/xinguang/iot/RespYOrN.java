package com.xinguang.iot;

import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * Created by jinx on 2017/4/26.
 */
public class RespYOrN {
    public static final RespYOrN YES = new RespYOrN(true);
    public static final RespYOrN NO = new RespYOrN(false);
    @ApiModelProperty("结果")
    private boolean result;

    public RespYOrN(boolean result) {
        this.result = result;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
