package com.xinguang.iot.dao.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_vehicle")
public class SysVehicle {

    @Id
    @GeneratedValue(generator="UUID")
    private String id;

    /**
     * 车牌
     */
    private String plate;

    /**
     * 型号
     */
    private String model;

    /**
     * 车辆司机
     */
    private String driver;

    /**
     * 联系电话
     */
    private String telephone;

    /**
     * 车辆简介
     */
    private String introduction;

    /**
     * 创建者
     */
    @Column(name = "create_by")
    private String createBy;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 更新者
     */
    @Column(name = "update_by")
    private String updateBy;

    /**
     * 更新时间
     */
    @Column(name = "update_date")
    private Date updateDate;

    /**
     * 备注信息
     */
    private String remarks;

    /**
     * 删除标记
     */
    @Column(name = "del_flag")
    private String delFlag;


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    /**
     * 获取车牌
     *
     * @return plate - 车牌
     */
    public String getPlate() {
        return plate;
    }

    /**
     * 设置车牌
     *
     * @param plate 车牌
     */
    public void setPlate(String plate) {
        this.plate = plate;
    }

    /**
     * 获取型号
     *
     * @return model - 型号
     */
    public String getModel() {
        return model;
    }

    /**
     * 设置型号
     *
     * @param model 型号
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * 获取车辆司机
     *
     * @return driver - 车辆司机
     */
    public String getDriver() {
        return driver;
    }

    /**
     * 设置车辆司机
     *
     * @param driver 车辆司机
     */
    public void setDriver(String driver) {
        this.driver = driver;
    }

    /**
     * 获取联系电话
     *
     * @return telephone - 联系电话
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * 设置联系电话
     *
     * @param telephone 联系电话
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * 获取车辆简介
     *
     * @return introduction - 车辆简介
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * 设置车辆简介
     *
     * @param introduction 车辆简介
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    /**
     * 获取创建者
     *
     * @return create_by - 创建者
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * 设置创建者
     *
     * @param createBy 创建者
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     * 获取创建时间
     *
     * @return create_date - 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置创建时间
     *
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取更新者
     *
     * @return update_by - 更新者
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * 设置更新者
     *
     * @param updateBy 更新者
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * 获取更新时间
     *
     * @return update_date - 更新时间
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置更新时间
     *
     * @param updateDate 更新时间
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 获取备注信息
     *
     * @return remarks - 备注信息
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置备注信息
     *
     * @param remarks 备注信息
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * 获取删除标记
     *
     * @return del_flag - 删除标记
     */
    public String getDelFlag() {
        return delFlag;
    }

    /**
     * 设置删除标记
     *
     * @param delFlag 删除标记
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}