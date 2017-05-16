package com.xinguang.iot.dao.mapper;

import com.xinguang.iot.dao.entity.SysDevice;
import tk.mybatis.mapper.common.Mapper;

public interface SysDeviceMapper extends Mapper<SysDevice> {
    SysDevice checkDevice(String clientId);
}