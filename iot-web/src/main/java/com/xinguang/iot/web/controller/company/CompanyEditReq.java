package com.xinguang.iot.web.controller.company;

import com.wordnik.swagger.annotations.ApiModelProperty;
import com.xinguang.iot.web.controller.BaseReq;

/**
 * Created by jinx on 2017/4/26.
 */
public class CompanyEditReq extends BaseReq{
    @ApiModelProperty("企业编号")
    private String companyCode;
    @ApiModelProperty("企业名称")
    private String companyName;// '企业名称
    @ApiModelProperty("企业类型")
    private String companyType;//'企业类型
    @ApiModelProperty("联系地址")
    private String address;//'联系地址
    @ApiModelProperty("联系电话")
    private String telephone;//'联系电话
    @ApiModelProperty("邮政编码")
    private String zipCode;// '邮政编码
    @ApiModelProperty("负责人")
    private String master;// '负责人'
    @ApiModelProperty("邮箱")
    private String email;//'邮箱'
    @ApiModelProperty("简介")
    private String introduction;//'企业简介

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }


    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
