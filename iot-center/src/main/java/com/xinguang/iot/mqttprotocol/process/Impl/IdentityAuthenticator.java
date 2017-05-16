package com.xinguang.iot.mqttprotocol.process.Impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.xinguang.iot.dao.entity.SysCompany;
import com.xinguang.iot.dao.entity.SysDevice;
import com.xinguang.iot.dao.mapper.SysCompanyMapper;
import com.xinguang.iot.dao.mapper.SysDeviceMapper;
import com.xinguang.iot.mqttprotocol.process.Impl.dataHandler.DBConnection;
import com.xinguang.iot.mqttprotocol.process.Interface.IAuthenticator;
import com.xinguang.iot.service.CompanyService;
import com.xinguang.iot.service.DataService;
import com.xinguang.iot.service.DeviceService;
import com.xinguang.iot.service.SpringContextHolder;

import javax.annotation.Resource;

/**
 *  身份校验类，该类的校验仅允许数据库中有的用户通过验证
 * 
 * @author zer0
 * @version 1.0
 * @date 2015-6-30
 */
public class IdentityAuthenticator implements IAuthenticator {

	private SysCompanyMapper sysCompanyMapper;
	private SysDeviceMapper sysDeviceMapper;

	@Override
	public boolean checkValid(String loginName,String apiKey) {
		//该处连接数据库，到数据库查询是否有该用户，有则通过验证
		sysCompanyMapper =  SpringContextHolder.getBean(SysCompanyMapper.class);
		SysCompany company = sysCompanyMapper.checkValid(loginName,apiKey);
		if (company != null) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean checkDevice(String clientId) {
		sysDeviceMapper =  SpringContextHolder.getBean(SysDeviceMapper.class);
		SysDevice device = sysDeviceMapper.checkDevice(clientId);
		if (device != null) {
			return true;
		}else {
			return false;
		}
	}

}
