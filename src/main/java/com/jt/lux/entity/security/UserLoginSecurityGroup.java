package com.jt.lux.entity.security;

import java.util.Date;
import javax.persistence.*;

/**
 * 用户登录安全组
 */
@Table(name = "user_login_security_group")
public class UserLoginSecurityGroup {
    @Id
    @Column(name = "USER_LOGIN_ID")
    private String userLoginId;

    @Id
    @Column(name = "GROUP_ID")
    private String groupId;

    @Id
    @Column(name = "FROM_DATE")
    private Date fromDate;

    @Column(name = "THRU_DATE")
    private Date thruDate;

    @Column(name = "LAST_UPDATED_STAMP")
    private Date lastUpdatedStamp;

    @Column(name = "LAST_UPDATED_TX_STAMP")
    private Date lastUpdatedTxStamp;

    @Column(name = "CREATED_STAMP")
    private Date createdStamp;

    @Column(name = "CREATED_TX_STAMP")
    private Date createdTxStamp;

    /**
     * @return USER_LOGIN_ID
     */
    public String getUserLoginId() {
        return userLoginId;
    }

    /**
     * @param userLoginId
     */
    public void setUserLoginId(String userLoginId) {
        this.userLoginId = userLoginId;
    }

    /**
     * @return GROUP_ID
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * @param groupId
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
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