package com.jt.lux.entity.security;

import javax.persistence.*;
import java.util.Date;

/**
 * ��ȫ��
 */
@Table(name = "security_group")
public class SecurityGroup {
    /**
     * ��ȫ������
     */
    @Id
    @Column(name = "GROUP_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String groupId;

    /**
     * ��ȫ������
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
     * ��ȡ��ȫ������
     *
     * @return DESCRIPTION - ��ȫ������
     */
    public String getDescription() {
        return description;
    }

    /**
     * ���ð�ȫ������
     *
     * @param description ��ȫ������
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