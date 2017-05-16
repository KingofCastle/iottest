package com.xinguang.iot.dao.mapper;

import com.xinguang.iot.dao.entity.DataHumidity;
import tk.mybatis.mapper.common.Mapper;

public interface DataHumidityMapper extends Mapper<DataHumidity> {
    DataHumidity selectLast(String sensorId);
}