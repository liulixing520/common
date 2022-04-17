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
     * 被审核人party_id
     */
    @Column(name = "PARTY_ID")
    private String partyId;

    /**
     * 审核人
     */
    @Column(name = "AUDITOR")
    private String auditor;

    /**
     * 审核状态   Y 认证成功,M 认证中,N 认证失败 U 未认证
     */
    @Column(name = "AUDIT_STATUS")
    private String auditStatus;

    /**
     * 审核意见
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
     * 获取被审核人party_id
     *
     * @return PARTY_ID - 被审核人party_id
     */
    public String getPartyId() {
        return partyId;
    }

    /**
     * 设置被审核人party_id
     *
     * @param partyId 被审核人party_id
     */
    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }

    /**
     * 获取审核人
     *
     * @return AUDITOR - 审核人
     */
    public String getAuditor() {
        return auditor;
    }

    /**
     * 设置审核人
     *
     * @param auditor 审核人
     */
    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    /**
     * 获取审核状态
     *
     * @return AUDIT_STATUS - 审核状态
     */
    public String getAuditStatus() {
        return auditStatus;
    }

    /**
     * 设置审核状态
     *
     * @param auditStatus 审核状态
     */
    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    /**
     * 获取审核意见
     *
     * @return REMARK - 审核意见
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置审核意见
     *
     * @param remark 审核意见
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