package com.xinguang.iot.center.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by chuantou on 2016/12/19.
 */
@JsonIgnoreProperties(value = { "remarks", "createBy", "createDate", "updateBy", "updateDate", "delFlag" })
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String DEL_FLAG_NORMAL = "0";
    public static final String DEL_FLAG_DELETE = "1";

    @Id
    @GeneratedValue(generator="UUID")
    protected String id;		// 编号
    @Column(name = "remarks")
    protected String remarks;	// 备注
    @Column(name = "create_by")
    protected String createBy;	// 创建者
    @Column(name = "create_date")
    protected Date createDate;// 创建日期
    @Column(name = "update_by")
    protected String updateBy;	// 更新者
    @Column(name = "update_date")
    protected Date updateDate;// 更新日期
    @Column(name = "del_flag")
    protected String delFlag; // 删除标记（0：正常；1：删除；2：审核）

    @Transient
    protected String pageNum;
    @Transient
    protected String pageSize;
    @Transient
    protected String orderBy;

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getId() {
        return id;
    }
    public String getRemarks() {
        return remarks;
    }
    public String getCreateBy() {
        return createBy;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public String getUpdateBy() {
        return updateBy;
    }
    public Date getUpdateDate() {
        return updateDate;
    }
    public String getDelFlag() {
        return delFlag;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public String toString() {
        return "BaseEntity [id=" + id + ", remarks=" + remarks + ", createBy="
                + createBy + ", createDate=" + createDate + ", updateBy="
                + updateBy + ", updateDate=" + updateDate + ", delFlag="
                + delFlag + "]";
    }
}
