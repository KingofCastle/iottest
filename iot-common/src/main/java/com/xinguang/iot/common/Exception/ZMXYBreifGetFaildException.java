package com.xinguang.iot.common.Exception;

import com.xinguang.iot.common.Exception.base.BaseException;

/**
 * Created by chuantou on 2016/12/22.
 */
public class ZMXYBreifGetFaildException extends BaseException {

    private static final long serialVersionUID = 5464480729968449293L;

    public ZMXYBreifGetFaildException() {
        super("-1", "芝麻信用普惠版调用失败");
    }

    public ZMXYBreifGetFaildException(String errorCode, String errorMSG) {
        super(errorCode, errorMSG);
    }
}
