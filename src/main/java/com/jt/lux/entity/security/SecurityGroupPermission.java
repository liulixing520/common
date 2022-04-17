package com.jt.lux.entity.security;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Ȩ���밲ȫ�� (��Զ�)
 */
@Table(name = "security_group_permission")
public class SecurityGroupPermission {
    /**
     * ��ȫ������
     */
    @Id
    @Column(name = "GROUP_ID")
    private String groupId;

    /**
     * Ȩ������
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
     * ��ȡ��ȫ������
     *
     * @return GROUP_ID - ��ȫ������
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * ���ð�ȫ������
     *
     * @param groupId ��ȫ������
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    /**
     * ��ȡȨ������
     *
     * @return PERMISSION_ID - Ȩ������
     */
    public String getPermissionId() {
        return permissionId;
    }

    /**
     * ����Ȩ������
     *
     * @param permissionId Ȩ������
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