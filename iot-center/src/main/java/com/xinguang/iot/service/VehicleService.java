package com.xinguang.iot.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xinguang.iot.common.utils.PageWrap;
import com.xinguang.iot.dao.entity.SysStore;
import com.xinguang.iot.dao.entity.SysVehicle;
import com.xinguang.iot.dao.mapper.SysStoreMapper;
import com.xinguang.iot.dao.mapper.SysVehicleMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * Vehicle service
 */
@Service
@Component
public class VehicleService {

    @Resource
    private SysVehicleMapper sysVehicleMapper;

    public void saveVehicle(SysVehicle vehicle) {
        sysVehicleMapper.insert(vehicle);
    }

    /**
     * 根据车牌找车辆信息
     * @param plate 车牌
     * @return 车辆
     */
    public SysVehicle findVehicleByPlate(String plate) {
        SysVehicle vehicle = new SysVehicle();
        vehicle.setPlate(plate);
        return sysVehicleMapper.selectOne(vehicle);
    }

    public void deleteVehicle(SysVehicle vehicle) {
        sysVehicleMapper.delete(vehicle);
    }

    public List<SysVehicle> find(PageWrap<SysVehicle> page) {
        Page<Object> newPage = PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.isCount());
        List<SysVehicle> list = sysVehicleMapper.selectAll();
        page.setTotals(newPage.getTotal());
        page.setPages(newPage.getPages());

        return list;
    }

    public SysVehicle findVehicleById(String vehicleId) {
       return sysVehicleMapper.selectByPrimaryKey(vehicleId);
    }

    public void updateVehicle(SysVehicle vehicle) {
        sysVehicleMapper.updateByPrimaryKey(vehicle);
    }
}
