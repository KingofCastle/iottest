package com.xinguang.iot.dao.entity;

import javax.persistence.*;

@Table(name = "user_info")
public class UserInfo {
    /**
     * 编号
     */
    @Id
    private Integer id;

    /**
     * 归属公司
     */
    private String name;

    /**
     * 获取编号
     *
     * @return id - 编号
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置编号
     *
     * @param id 编号
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取归属公司
     *
     * @return name - 归属公司
     */
    public String getName() {
        return name;
    }

    /**
     * 设置归属公司
     *
     * @param name 归属公司
     */
    public void setName(String name) {
        this.name = name;
    }
}