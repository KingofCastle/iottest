package com.xinguang.iot.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xinguang.iot.common.utils.PageWrap;
import com.xinguang.iot.dao.entity.SysSensor;
import com.xinguang.iot.dao.entity.SysStore;
import com.xinguang.iot.dao.mapper.SysSensorMapper;
import com.xinguang.iot.dao.mapper.SysStoreMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * Store service
 */
@Service
@Component
public class StoreService {

    @Resource
    private SysStoreMapper sysStoreMapper;

    public void saveStore(SysStore store) {
        sysStoreMapper.insert(store);
    }

    public SysStore findStoreByCode(String storeCode) {
        SysStore store = new SysStore();
        store.setCode(storeCode);
        return sysStoreMapper.selectOne(store);
    }

    public void deleteStore(SysStore store) {
        sysStoreMapper.delete(store);
    }

    public List<SysStore> find(PageWrap<SysStore> page) {
        Page<Object> newPage = PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.isCount());
        List<SysStore> list = sysStoreMapper.selectAll();
        page.setTotals(newPage.getTotal());
        page.setPages(newPage.getPages());

        return list;
    }

    public SysStore findStoreById(String storeId) {
       return sysStoreMapper.selectByPrimaryKey(storeId);
    }

    public void updateStore(SysStore store) {
        sysStoreMapper.updateByPrimaryKey(store);
    }
}
