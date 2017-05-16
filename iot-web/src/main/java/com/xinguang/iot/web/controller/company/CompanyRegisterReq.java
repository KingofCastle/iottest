package com.xinguang.iot.web.controller.company;

import com.wordnik.swagger.annotations.ApiModelProperty;
import com.xinguang.iot.web.controller.BaseReq;

/**
 * Created by jinx on 2017/4/26.
 */
public class CompanyRegisterReq extends BaseReq{
    @ApiModelProperty("企业账号")
    private String companyLogin ;//'企业账号'
    @ApiModelProperty("密码")
    private String password;//'密码'

    public String getCompanyLogin() {
        return companyLogin;
    }

    public void setCompanyLogin(String companyLogin) {
        this.companyLogin = companyLogin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
