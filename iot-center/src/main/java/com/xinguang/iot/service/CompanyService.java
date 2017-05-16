package com.xinguang.iot.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xinguang.iot.common.utils.MD5Utils;
import com.xinguang.iot.common.utils.PageWrap;
import com.xinguang.iot.dao.entity.SysCompany;
import com.xinguang.iot.dao.mapper.SysCompanyMapper;
import com.xinguang.iot.exception.DeviceNotValidException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;


/**
 * 企业service
 */
@Service
@Component
@Transactional(readOnly = true)
public class CompanyService {

    @Resource
    private SysCompanyMapper sysCompanyMapper;
    @Transactional(readOnly = false)
    public int saveCompany(SysCompany company) {
        return sysCompanyMapper.insert(company);
    }

    public SysCompany findCompanyByCode(String companyCode) {
        SysCompany company = new SysCompany();
        company.setCode(companyCode);

        return sysCompanyMapper.selectOne(company);
    }

    @Transactional(readOnly = false)
    public void deleteCompany(SysCompany company) {
        sysCompanyMapper.delete(company);
    }
    @Transactional(readOnly = false)
    public void deleteCompanyById(String companyId) {
        sysCompanyMapper.deleteByPrimaryKey(companyId);
    }

    public List<SysCompany> find(PageWrap<SysCompany> page) {
        Page<Object> newPage = PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.isCount());
        List<SysCompany> list = sysCompanyMapper.selectAll();
        page.setTotals(newPage.getTotal());
        page.setPages(newPage.getPages());

        return list;
    }
    @Transactional(readOnly = false)
    public int update(SysCompany company) {
        Example example = new Example(SysCompany.class);
        example.createCriteria().andEqualTo("id", company.getId());
        return sysCompanyMapper.updateByExampleSelective(company, example);
    }

    public List<SysCompany> findAll() {
        return sysCompanyMapper.selectAll();
    }

    public SysCompany findOne(String id) {
        return sysCompanyMapper.selectByPrimaryKey(id);
    }

    public SysCompany checkValid(String loginName,String apiKey) throws DeviceNotValidException{
        SysCompany company =  sysCompanyMapper.checkValid(loginName,apiKey);
        if (company==null){
            throw new DeviceNotValidException();
        }
        return company;
    }

    public SysCompany isValidUser(String loginName, String password) {
        SysCompany company = new SysCompany();
        company.setCompanyLogin(loginName);
        company.setPassword(MD5Utils.MD5(password));
        Example example = new Example(SysCompany.class);
        example.createCriteria().andEqualTo(company);
        return sysCompanyMapper.selectByExample(example).get(0);
    }

    public SysCompany findSecretByKey(String apiKey) {
        Example example = new Example(SysCompany.class);
        example.createCriteria().andEqualTo("apiKey", apiKey);
        List<SysCompany> sysCompanyList = sysCompanyMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(sysCompanyList)){
            return sysCompanyList.get(0);
        }else{
            return null;
        }
    }
}
