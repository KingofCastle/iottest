package com.xinguang.iot.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xinguang.iot.common.utils.PageWrap;
import com.xinguang.iot.dao.entity.SysDevice;
import com.xinguang.iot.dao.mapper.SysDeviceMapper;
import com.xinguang.iot.exception.DeviceNotValidException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * 传感器service
 */
@Service
@Component
@Transactional(readOnly = true)
public class DeviceService {

    @Resource
    private SysDeviceMapper sysDeviceMapper;

    @Transactional(readOnly = false)
    public void saveDevice(SysDevice device) {
        sysDeviceMapper.insert(device);
    }

    public SysDevice findDeviceByCode(String deviceCode) {
//        Example example = new Example(SysDevice.class);
//        example.createCriteria().andEqualTo("code",deviceCode);
//        List<SysDevice> deviceList = sysDeviceMapper.selectByExample(example);
//        if (CollectionUtils.isNotEmpty(deviceList)){
//            return deviceList.get(0);
//        }
//        return null;
        SysDevice device = new SysDevice();
        device.setCode(deviceCode);
        return sysDeviceMapper.selectOne(device);
    }

    @Transactional(readOnly = false)
    public void deleteDevice(SysDevice device) {
        sysDeviceMapper.delete(device);
    }

    public List<SysDevice> find(PageWrap<SysDevice> page) {
        Page<Object> newPage = PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.isCount());
        List<SysDevice> list = sysDeviceMapper.selectAll();
        page.setTotals(newPage.getTotal());
        page.setPages(newPage.getPages());

        return list;
    }

    @Transactional(readOnly = false)
    public void updateDevice(SysDevice device) {
        sysDeviceMapper.updateByPrimaryKey(device);
    }

    public SysDevice checkDevice(String clientId) throws DeviceNotValidException {
        SysDevice device = this.findDeviceByCode(clientId);
        if (device==null){
            throw new DeviceNotValidException(clientId);
        }
        return device;
    }
}
