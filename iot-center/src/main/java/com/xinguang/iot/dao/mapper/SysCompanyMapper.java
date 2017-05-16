package com.xinguang.iot.dao.mapper;

import com.xinguang.iot.dao.entity.SysCompany;
import tk.mybatis.mapper.common.Mapper;

public interface SysCompanyMapper extends Mapper<SysCompany> {
    SysCompany checkValid(String loginName,String apiKey);
}