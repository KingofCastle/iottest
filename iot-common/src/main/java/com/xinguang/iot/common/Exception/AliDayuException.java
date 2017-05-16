package com.xinguang.iot.common.Exception;

import com.xinguang.iot.common.Exception.base.BaseException;

/**
 * Created by chuantou on 2016/12/22.
 */
public class AliDayuException extends BaseException {

    private static final long serialVersionUID = 5464480729968449293L;

    public AliDayuException() {
        super("-2", "阿里大鱼调用失败");
    }

    public AliDayuException(String errorCode, String errorMSG) {
        super(errorCode, errorMSG);
    }
}
