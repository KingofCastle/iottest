package com.xinguang.iot.dao.mapper;

import com.xinguang.iot.dao.entity.DataTemperature;
import tk.mybatis.mapper.common.Mapper;

public interface DataTemperatureMapper extends Mapper<DataTemperature> {
    DataTemperature selectLast(String sensorId);
}