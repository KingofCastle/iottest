package com.xinguang.iot.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xinguang.iot.common.utils.PageWrap;
import com.xinguang.iot.dao.entity.SysSensor;
import com.xinguang.iot.dao.mapper.SysSensorMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;


/**
 * 传感器service
 */
@Service
@Component
@Transactional(readOnly = true)
public class SensorService {

    @Resource
    private SysSensorMapper sysSensorMapper;

    @Transactional(readOnly = false)
    public void saveSensor(SysSensor sensor) {
        sysSensorMapper.insert(sensor);
    }

    public SysSensor findSensorByCode(String sensorCode) {
//        Example example = new Example(SysSensor.class);
//        example.createCriteria().andEqualTo("code",sensorCode);
//        if (sysSensorMapper.selectByExample(example).size()==0){
//            return null;
//        }
        SysSensor sensor = new SysSensor();
        sensor.setCode(sensorCode);
        return sysSensorMapper.selectOne(sensor);
    }

    @Transactional(readOnly = false)
    public void deleteSensor(SysSensor sensor) {
        sysSensorMapper.delete(sensor);
    }

    public List<SysSensor> find(PageWrap<SysSensor> page) {
        Page<Object> newPage = PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.isCount());
        List<SysSensor> list = sysSensorMapper.selectAll();
        page.setTotals(newPage.getTotal());
        page.setPages(newPage.getPages());

        return list;
    }

    public SysSensor findSensorById(String sensorId) {
       return sysSensorMapper.selectByPrimaryKey(sensorId);
    }

    @Transactional(readOnly = false)
    public void updateSensor(SysSensor sensor) {
        sysSensorMapper.updateByPrimaryKey(sensor);
    }
}
