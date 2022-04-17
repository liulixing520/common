package com.jt.lux.entity.security;

import javax.persistence.*;
import java.util.Date;

/**
 * 安全组
 */
@Table(name = "security_group")
public class SecurityGroup {
    /**
     * 安全组主键
     */
    @Id
    @Column(name = "GROUP_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String groupId;

    /**
     * 安全组描述
     */
    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "LAST_UPDATED_STAMP")
    private Date lastUpdatedStamp;

    @Column(name = "LAST_UPDATED_TX_STAMP")
    private Date lastUpdatedTxStamp;

    @Column(name = "CREATED_STAMP")
    private Date createdStamp;

    @Column(name = "CREATED_TX_STAMP")
    private Date createdTxStamp;

    /**
     * 获取安全组主键
     *
     * @return GROUP_ID - 安全组主键
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * 设置安全组主键
     *
     * @param groupId 安全组主键
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    /**
     * 获取安全组描述
     *
     * @return DESCRIPTION - 安全组描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置安全组描述
     *
     * @param description 安全组描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return LAST_UPDATED_STAMP
     */
    public Date getLastUpdatedStamp() {
        return lastUpdatedStamp;
    }

    /**
     * @param lastUpdatedStamp
     */
    public void setLastUpdatedStamp(Date lastUpdatedStamp) {
        this.lastUpdatedStamp = lastUpdatedStamp;
    }

    /**
     * @return LAST_UPDATED_TX_STAMP
     */
    public Date getLastUpdatedTxStamp() {
        return lastUpdatedTxStamp;
    }

    /**
     * @param lastUpdatedTxStamp
     */
    public void setLastUpdatedTxStamp(Date lastUpdatedTxStamp) {
        this.lastUpdatedTxStamp = lastUpdatedTxStamp;
    }

    /**
     * @return CREATED_STAMP
     */
    public Date getCreatedStamp() {
        return createdStamp;
    }

    /**
     * @param createdStamp
     */
    public void setCreatedStamp(Date createdStamp) {
        this.createdStamp = createdStamp;
    }

    /**
     * @return CREATED_TX_STAMP
     */
    public Date getCreatedTxStamp() {
        return createdTxStamp;
    }

    /**
     * @param createdTxStamp
     */
    public void setCreatedTxStamp(Date createdTxStamp) {
        this.createdTxStamp = createdTxStamp;
    }
}