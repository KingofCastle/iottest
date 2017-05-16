package com.xinguang.iot.client.store;

import com.wordnik.swagger.annotations.ApiModelProperty;
import com.xinguang.iot.client.ClientReq;

/**
 * Created by jinx on 2017/4/27.
 */
public class StoreEditReq  extends ClientReq{
    @ApiModelProperty("门店id")
    private String storeId;

    @ApiModelProperty("公司id")
    private String companyId;
    @ApiModelProperty("联系电话")
    private String telephone;
    @ApiModelProperty("门店名称")
    private String name;
    @ApiModelProperty("门店地址")
    private String address;
    @ApiModelProperty("负责人")
    private String master;
    @ApiModelProperty("简介")
    private String introduction;

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
