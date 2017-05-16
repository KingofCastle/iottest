package com.xinguang.iot.client.vehicle;

import com.wordnik.swagger.annotations.ApiModelProperty;
import com.xinguang.iot.client.ClientReq;

/**
 * Created by jinx on 2017/4/27.
 */
public class VehicleAddReq extends ClientReq{
//`plate` varchar(200) default NULL COMMENT '车牌',
//            `model` varchar(64) default NULL COMMENT '型号',
//            `driver` varchar(255) default NULL COMMENT '车辆司机',
//            `telephone` varchar(255) default NULL COMMENT '联系电话',
//            `introduction` varchar(200) default NULL COMMENT '车辆简介',

    @ApiModelProperty("车牌")
    private String plant;
    @ApiModelProperty("车辆型号")
    private String model;
    @ApiModelProperty("司机")
    private String driver;
    @ApiModelProperty("司机联系方式")
    private String telephone;
    @ApiModelProperty("简介")
    private String introduction;

    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
