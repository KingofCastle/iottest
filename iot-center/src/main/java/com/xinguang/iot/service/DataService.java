package com.xinguang.iot.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xinguang.iot.common.utils.PageWrap;
import com.xinguang.iot.dao.entity.DataHumidity;
import com.xinguang.iot.dao.entity.DataTemperature;
import com.xinguang.iot.dao.entity.UserInfo;
import com.xinguang.iot.dao.mapper.DataGpsMapper;
import com.xinguang.iot.dao.mapper.DataHumidityMapper;
import com.xinguang.iot.dao.mapper.DataTemperatureMapper;
import com.xinguang.iot.dao.mapper.UserInfoMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


/**
 * 数据点service
 */
@Service
@Component
@Transactional(readOnly = true)
public class DataService {

    @Resource
    private DataTemperatureMapper temperatureMapper;
    @Resource
    private DataHumidityMapper humidityMapper;
    @Resource
    private DataGpsMapper gpsMapper;

    @Transactional(readOnly = false)
    public void saveTemp(DataTemperature temperature) {
        temperatureMapper.insert(temperature);
    }

    @Transactional(readOnly = false)
    public void saveHumi(DataHumidity humidity) {
        humidityMapper.insert(humidity);
    }

    public DataTemperature getTemp(String id){
        return temperatureMapper.selectByPrimaryKey(id);
    }

    public DataTemperature findTemByTime(Date date, String sensorId) {
        DataTemperature temperature = new DataTemperature();
        temperature.setTime(date);
        temperature.setSensorId(sensorId);
        return temperatureMapper.selectOne(temperature);
    }

    @Transactional(readOnly = false)
    public void deleteDataTemp(DataTemperature temperature) {
        temperatureMapper.delete(temperature);
    }

    public DataHumidity findHumByTime(Date date, String sensorId) {
        DataHumidity humidity = new DataHumidity();
        humidity.setSensorId(sensorId);
        humidity.setTime(date);
        return humidityMapper.selectOne(humidity);
    }

    @Transactional(readOnly = false)
    public void deleteDataHum(DataHumidity humidity) {
        humidityMapper.delete(humidity);
    }

    public List<DataTemperature> findTem(Date start,Date end, PageWrap<DataTemperature> page, String sensorId) {

        Page<Object> newPage = PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.isCount());
        Example example = new Example(DataTemperature.class);
        example.createCriteria().andBetween("time",start,end).andEqualTo("sensorId",sensorId);
        List<DataTemperature> list = temperatureMapper.selectByExample(example);
        page.setTotals(newPage.getTotal());
        page.setPages(newPage.getPages());

        return list;
    }

    public List<DataHumidity> findHum(Date start,Date end, PageWrap<DataHumidity> page, String sensorId) {

        Page<Object> newPage = PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.isCount());
        Example example = new Example(DataHumidity.class);
        example.createCriteria().andBetween("time",start,end).andEqualTo("sensorId",sensorId);
        List<DataHumidity> list = humidityMapper.selectByExample(example);
        page.setTotals(newPage.getTotal());
        page.setPages(newPage.getPages());

        return list;
    }

    public DataTemperature findLastTemperature(String sensorId){
        return temperatureMapper.selectLast(sensorId);
    }
    public DataHumidity findLastHumidity(String sensorId){
        return humidityMapper.selectLast(sensorId);
    }
}
