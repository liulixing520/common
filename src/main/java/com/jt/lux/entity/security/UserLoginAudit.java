package com.jt.lux.entity.security;

import javax.persistence.*;
import java.util.Date;

@Table(name = "user_login_audit")
public class UserLoginAudit {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * �������party_id
     */
    @Column(name = "PARTY_ID")
    private String partyId;

    /**
     * �����
     */
    @Column(name = "AUDITOR")
    private String auditor;

    /**
     * ���״̬   Y ��֤�ɹ�,M ��֤��,N ��֤ʧ�� U δ��֤
     */
    @Column(name = "AUDIT_STATUS")
    private String auditStatus;

    /**
     * ������
     */
    @Column(name = "REMARK")
    private String remark;

    @Column(name = "CREATED_STAMP")
    private Date createdStamp;

    /**
     * @return ID
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * ��ȡ�������party_id
     *
     * @return PARTY_ID - �������party_id
     */
    public String getPartyId() {
        return partyId;
    }

    /**
     * ���ñ������party_id
     *
     * @param partyId �������party_id
     */
    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }

    /**
     * ��ȡ�����
     *
     * @return AUDITOR - �����
     */
    public String getAuditor() {
        return auditor;
    }

    /**
     * ���������
     *
     * @param auditor �����
     */
    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    /**
     * ��ȡ���״̬
     *
     * @return AUDIT_STATUS - ���״̬
     */
    public String getAuditStatus() {
        return auditStatus;
    }

    /**
     * �������״̬
     *
     * @param auditStatus ���״̬
     */
    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    /**
     * ��ȡ������
     *
     * @return REMARK - ������
     */
    public String getRemark() {
        return remark;
    }

    /**
     * ����������
     *
     * @param remark ������
     */
    public void setRemark(String remark) {
        this.remark = remark;
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
}