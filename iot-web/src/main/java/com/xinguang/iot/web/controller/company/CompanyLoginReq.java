package com.xinguang.iot.web.controller.company;

import com.wordnik.swagger.annotations.ApiModelProperty;
import com.xinguang.iot.web.controller.BaseReq;

/**
 * Created by jinx on 2017/4/26.
 */
public class CompanyLoginReq extends BaseReq{
    @ApiModelProperty("密码")
    String password;
    @ApiModelProperty("账号")
    String loginName ;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
}
