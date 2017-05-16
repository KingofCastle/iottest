package com.xinguang.iot.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xinguang.iot.common.utils.PageWrap;
import com.xinguang.iot.dao.entity.SysFreezer;
import com.xinguang.iot.dao.entity.SysVehicle;
import com.xinguang.iot.dao.mapper.SysFreezerMapper;
import com.xinguang.iot.dao.mapper.SysVehicleMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * freezer service
 */
@Service
@Component
@Transactional(readOnly = true)
public class FreezerService {

    @Resource
    private SysFreezerMapper sysFreezerMapper;

    @Transactional(readOnly = false)
    public void saveFreezer(SysFreezer freezer) {
        if (StringUtils.isNotBlank(freezer.getCode())){

            String freezerCode = "0001";//TODO
            freezer.setCode(freezerCode);
        }
        sysFreezerMapper.insert(freezer);
    }

    public SysFreezer findFreezerByCode(String freezerCode) {
        SysFreezer freezer = new SysFreezer();
        freezer.setCode(freezerCode);
        return sysFreezerMapper.selectOne(freezer);
    }

    @Transactional(readOnly = false)
    public void deleteFreezer(SysFreezer freezer) {
        sysFreezerMapper.delete(freezer);
    }

    public List<SysFreezer> find(PageWrap<SysFreezer> page) {
        Page<Object> newPage = PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.isCount());
        List<SysFreezer> list = sysFreezerMapper.selectAll();
        page.setTotals(newPage.getTotal());
        page.setPages(newPage.getPages());

        return list;
    }

    public SysFreezer findFreezerById(String freezerId) {
       return sysFreezerMapper.selectByPrimaryKey(freezerId);
    }

    @Transactional(readOnly = false)
    public void updateFreezer(SysFreezer freezer) {
        sysFreezerMapper.updateByPrimaryKey(freezer);
    }
}
