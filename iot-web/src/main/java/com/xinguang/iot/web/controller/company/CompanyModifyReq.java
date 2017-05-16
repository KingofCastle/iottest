package com.xinguang.iot.web.controller.company;

import com.wordnik.swagger.annotations.ApiModelProperty;
import com.xinguang.iot.web.controller.BaseReq;

/**
 * Created by jinx on 2017/4/26.
 */
public class CompanyModifyReq extends BaseReq{
    @ApiModelProperty("原始密码")
    String password;
    @ApiModelProperty("新密码")
    String newPassword;
    @ApiModelProperty("确认密码")
    String confirmNewPwd ;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmNewPwd() {
        return confirmNewPwd;
    }

    public void setConfirmNewPwd(String confirmNewPwd) {
        this.confirmNewPwd = confirmNewPwd;
    }
}
