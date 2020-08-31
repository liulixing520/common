package com.jt.lux.entity.security;

import java.util.Date;
import javax.persistence.*;

/**
 * 权限与安全组 (多对多)
 */
@Table(name = "security_group_permission")
public class SecurityGroupPermission {
    /**
     * 安全组主键
     */
    @Id
    @Column(name = "GROUP_ID")
    private String groupId;

    /**
     * 权限主键
     */
    @Id
    @Column(name = "PERMISSION_ID")
    private String permissionId;

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
     * 获取权限主键
     *
     * @return PERMISSION_ID - 权限主键
     */
    public String getPermissionId() {
        return permissionId;
    }

    /**
     * 设置权限主键
     *
     * @param permissionId 权限主键
     */
    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
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