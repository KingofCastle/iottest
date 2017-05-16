package com.xinguang.iot.service;

import com.xinguang.iot.IotService;
import com.xinguang.iot.dao.entity.*;
import com.xinguang.iot.exception.DeviceNotValidException;
import com.xinguang.iot.exception.IotBaseException;
import com.xinguang.iot.request.DeviceValidRequest;
import com.xinguang.iot.response.DataPointQueryInfo;
import com.xinguang.iot.response.IotResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;


/**
 * Created by jinx on 2017/5/15.
 */
@Service(value = "IotServiceImpl")
public class IotServiceImpl implements IotService {

    private final static Logger logger = LoggerFactory.getLogger(IotServiceImpl.class);

    public static <D> IotResponse<D> genErrorCodeResponse(Exception e) {
        if (e instanceof IotBaseException) {
            logger.error("exception:{}", e.toString());
        } else if (e instanceof ConstraintViolationException) {
            logger.info("params error:{}", IotBaseException.genConstraintViolationDetail((ConstraintViolationException) e));
        } else {
            logger.error("exception:", e);
        }
        return IotBaseException.genErrorCodeResponse(e);
    }

    @Autowired
    private CompanyService companyService;
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private DataService dataService;
    @Autowired
    private SensorService sensorService;

    @Override
    public IotResponse<Boolean> checkDeviceValid(DeviceValidRequest request) {
        logger.debug("request:{}", request);

        SysDevice device = null;
        try {
            device = deviceService.checkDevice(request.getClientId());
            logger.debug("valid device id:{}", device.getId());
        } catch (Exception e) {
            return genErrorCodeResponse(e);
        }
        SysCompany company = null;
        try {
            company = companyService.checkValid(request.getLoginName(), request.getApiKey());
            boolean result = true;
            logger.debug("response:{}", result);
            return new IotResponse<Boolean>(result);
        } catch (Exception e) {
            return genErrorCodeResponse(e);
        }
    }

//    @Override
//    public IotResponse<Boolean> createDataPoint(DataPointAddRequest request) {
//        return null;
//    }

    @Override
    public IotResponse<DataPointQueryInfo> queryDataPoint(String sensorCode) {

        SysSensor sensor = sensorService.findSensorByCode(sensorCode);
        if (sensor == null) {
            logger.error("exception:{}", IotResponse.ErrorCode.ERROR_DEVICE_NOT_EXIST);
            return new IotResponse<DataPointQueryInfo>(IotResponse.ErrorCode.ERROR_DEVICE_NOT_EXIST);
        }
        String sensorId = sensor.getId();
        DataPointQueryInfo dataPointQueryInfo = new DataPointQueryInfo();
        dataPointQueryInfo.setSensorCode(sensorCode);
        DataTemperature temperature = dataService.findLastTemperature(sensorId);
        if (temperature!=null){
            dataPointQueryInfo.setTemValue(temperature.getValue());
        }
        DataHumidity humidity = dataService.findLastHumidity(sensorId);
        if (humidity!=null){
            dataPointQueryInfo.setHumValue(humidity.getValue());
        }
        logger.debug("response:{}", dataPointQueryInfo);
        return new IotResponse<DataPointQueryInfo>(dataPointQueryInfo);
    }
}
