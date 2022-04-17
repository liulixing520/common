package com.jt.lux.entity.security;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * �����˹�ϵ��
 */
@Table(name = "party_relationship")
public class PartyRelationship {
    /**
     * Դ������
     */
    @Id
    @Column(name = "PARTY_ID_FROM")
    private String partyIdFrom;

    /**
     * Ŀ�굱����
     */
    @Id
    @Column(name = "PARTY_ID_TO")
    private String partyIdTo;

    /**
     * Դ�����˽�ɫ
     */
    @Id
    @Column(name = "ROLE_TYPE_ID_FROM")
    private String roleTypeIdFrom;

    /**
     * Ŀ�굱���˽�ɫ
     */
    @Id
    @Column(name = "ROLE_TYPE_ID_TO")
    private String roleTypeIdTo;

    /**
     * ��ʼ����
     */
    @Id
    @Column(name = "FROM_DATE")
    private Date fromDate;

    /**
     * ��������
     */
    @Column(name = "THRU_DATE")
    private Date thruDate;

    /**
     * ״̬id
     */
    @Column(name = "STATUS_ID")
    private String statusId;

    /**
     * �����˹�ϵ����
     */
    @Column(name = "RELATIONSHIP_NAME")
    private String relationshipName;

    /**
     * ��ȫ���ʶ  (������Ϊ����ʱ��)
     */
    @Column(name = "SECURITY_GROUP_ID")
    private String securityGroupId;

    /**
     * �������ͱ�ʶ
     */
    @Column(name = "PRIORITY_TYPE_ID")
    private String priorityTypeId;

    /**
     * �����˹�ϵ���ͱ�ʶ
     */
    @Column(name = "PARTY_RELATIONSHIP_TYPE_ID")
    private String partyRelationshipTypeId;

    /**
     * Ȩ��ö�ٱ�ʶ
     */
    @Column(name = "PERMISSIONS_ENUM_ID")
    private String permissionsEnumId;

    /**
     * ְλͷ��
     */
    @Column(name = "POSITION_TITLE")
    private String positionTitle;

    @Column(name = "COMMENTS")
    private String comments;

    @Column(name = "LAST_UPDATED_STAMP")
    private Date lastUpdatedStamp;

    @Column(name = "LAST_UPDATED_TX_STAMP")
    private Date lastUpdatedTxStamp;

    @Column(name = "CREATED_STAMP")
    private Date createdStamp;

    @Column(name = "CREATED_TX_STAMP")
    private Date createdTxStamp;

    /**
     * @return PARTY_ID_FROM
     */
    public String getPartyIdFrom() {
        return partyIdFrom;
    }

    /**
     * @param partyIdFrom
     */
    public void setPartyIdFrom(String partyIdFrom) {
        this.partyIdFrom = partyIdFrom;
    }

    /**
     * @return PARTY_ID_TO
     */
    public String getPartyIdTo() {
        return partyIdTo;
    }

    /**
     * @param partyIdTo
     */
    public void setPartyIdTo(String partyIdTo) {
        this.partyIdTo = partyIdTo;
    }

    /**
     * @return ROLE_TYPE_ID_FROM
     */
    public String getRoleTypeIdFrom() {
        return roleTypeIdFrom;
    }

    /**
     * @param roleTypeIdFrom
     */
    public void setRoleTypeIdFrom(String roleTypeIdFrom) {
        this.roleTypeIdFrom = roleTypeIdFrom;
    }

    /**
     * @return ROLE_TYPE_ID_TO
     */
    public String getRoleTypeIdTo() {
        return roleTypeIdTo;
    }

    /**
     * @param roleTypeIdTo
     */
    public void setRoleTypeIdTo(String roleTypeIdTo) {
        this.roleTypeIdTo = roleTypeIdTo;
    }

    /**
     * @return FROM_DATE
     */
    public Date getFromDate() {
        return fromDate;
    }

    /**
     * @param fromDate
     */
    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * @return THRU_DATE
     */
    public Date getThruDate() {
        return thruDate;
    }

    /**
     * @param thruDate
     */
    public void setThruDate(Date thruDate) {
        this.thruDate = thruDate;
    }

    /**
     * @return STATUS_ID
     */
    public String getStatusId() {
        return statusId;
    }

    /**
     * @param statusId
     */
    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    /**
     * @return RELATIONSHIP_NAME
     */
    public String getRelationshipName() {
        return relationshipName;
    }

    /**
     * @param relationshipName
     */
    public void setRelationshipName(String relationshipName) {
        this.relationshipName = relationshipName;
    }

    /**
     * @return SECURITY_GROUP_ID
     */
    public String getSecurityGroupId() {
        return securityGroupId;
    }

    /**
     * @param securityGroupId
     */
    public void setSecurityGroupId(String securityGroupId) {
        this.securityGroupId = securityGroupId;
    }

    /**
     * @return PRIORITY_TYPE_ID
     */
    public String getPriorityTypeId() {
        return priorityTypeId;
    }

    /**
     * @param priorityTypeId
     */
    public void setPriorityTypeId(String priorityTypeId) {
        this.priorityTypeId = priorityTypeId;
    }

    /**
     * @return PARTY_RELATIONSHIP_TYPE_ID
     */
    public String getPartyRelationshipTypeId() {
        return partyRelationshipTypeId;
    }

    /**
     * @param partyRelationshipTypeId
     */
    public void setPartyRelationshipTypeId(String partyRelationshipTypeId) {
        this.partyRelationshipTypeId = partyRelationshipTypeId;
    }

    /**
     * @return PERMISSIONS_ENUM_ID
     */
    public String getPermissionsEnumId() {
        return permissionsEnumId;
    }

    /**
     * @param permissionsEnumId
     */
    public void setPermissionsEnumId(String permissionsEnumId) {
        this.permissionsEnumId = permissionsEnumId;
    }

    /**
     * @return POSITION_TITLE
     */
    public String getPositionTitle() {
        return positionTitle;
    }

    /**
     * @param positionTitle
     */
    public void setPositionTitle(String positionTitle) {
        this.positionTitle = positionTitle;
    }

    /**
     * @return COMMENTS
     */
    public String getComments() {
        return comments;
    }

    /**
     * @param comments
     */
    public void setComments(String comments) {
        this.comments = comments;
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