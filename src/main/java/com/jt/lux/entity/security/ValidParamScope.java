package com.jt.lux.entity.security;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 手机格式校验
 */
@Table(name = "phone_valid_param_scope")
public class ValidParamScope {
    /**
     * 记录id
     */
    @Id
    private Long id;

    /**
     * 允许的参数值
     */
    @Column(name = "allow_value")
    private String allowValue;

    /**
     * 参数值类型，01-手机号
     */
    private String type;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 获取记录id
     *
     * @return id - 记录id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置记录id
     *
     * @param id 记录id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取允许的参数值
     *
     * @return allow_value - 允许的参数值
     */
    public String getAllowValue() {
        return allowValue;
    }

    /**
     * 设置允许的参数值
     *
     * @param allowValue 允许的参数值
     */
    public void setAllowValue(String allowValue) {
        this.allowValue = allowValue;
    }

    /**
     * 获取参数值类型，01-手机号
     *
     * @return type - 参数值类型，01-手机号
     */
    public String getType() {
        return type;
    }

    /**
     * 设置参数值类型，01-手机号
     *
     * @param type 参数值类型，01-手机号
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取修改时间
     *
     * @return update_time - 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修改时间
     *
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}