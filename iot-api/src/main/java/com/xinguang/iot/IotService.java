package com.xinguang.iot;

import com.xinguang.iot.request.DeviceValidRequest;
import com.xinguang.iot.response.DataPointQueryInfo;
import com.xinguang.iot.response.IotResponse;

/**
 * Created by jinx on 2017/5/15.
 */
public interface IotService {
    /**
     * 设备认证
     * @param request
     * @return
     */
    IotResponse<Boolean> checkDeviceValid(DeviceValidRequest request);

//    /**
//     * 创建数据点
//     * @param request
//     * @return
//     */
//    IotResponse<Boolean> createDataPoint(DataPointAddRequest request);

    /**
     * 查看数据点
     * @param sensorCode
     * @return
     */
    IotResponse<DataPointQueryInfo> queryDataPoint(String sensorCode);
}
