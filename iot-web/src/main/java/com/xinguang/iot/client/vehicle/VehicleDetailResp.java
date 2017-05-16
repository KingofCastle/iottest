package com.xinguang.iot.client.vehicle;

import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * Created by jinx on 2017/4/27.
 */
public class VehicleDetailResp {
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
