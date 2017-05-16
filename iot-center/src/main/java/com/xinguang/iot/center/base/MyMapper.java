package com.xinguang.iot.center.base;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * Created by chuantou on 2016/12/20.
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {

}
