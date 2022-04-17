package com.jt.lux.entity.security;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * ��¼�˻�
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_login")
public class UserLogin implements Serializable {
    /**
     * ��¼�˻�id
     */
    @Id
    @Column(name = "USER_LOGIN_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("��¼�˻�id")
    private String userLoginId;

    /**
     * ��¼����
     */
    @JsonIgnore
    @Column(name = "CURRENT_PASSWORD")
    private String currentPassword;

    /**
     * ����
     */
    @JsonIgnore
    @Column(name = "SALT")
    private String salt;

    /**
     * ������ʾ
     */
    @JsonIgnore
    @Column(name = "PASSWORD_HINT")
    private String passwordHint;



    @JsonIgnore
    @ApiModelProperty("�Ƿ�ϵͳ����Ա Y/N")
    @Column(name = "IS_SYSTEM")
    private String isSystem;
    @JsonIgnore
    @ApiModelProperty("�Ƿ�����")
    @Column(name = "ENABLED")
    private String enabled;

    @Column(name = "HAS_LOGGED_OUT")
    private String hasLoggedOut;
    @JsonIgnore
    @Column(name = "REQUIRE_PASSWORD_CHANGE")
    private String requirePasswordChange;

    @Column(name = "LAST_CURRENCY_UOM")
    private String lastCurrencyUom;

    @Column(name = "LAST_LOCALE")
    private String lastLocale;

    @Column(name = "LAST_TIME_ZONE")
    private String lastTimeZone;

    @Column(name = "DISABLED_DATE_TIME")
    private Date disabledDateTime;

    @Column(name = "SUCCESSIVE_FAILED_LOGINS")
    private BigDecimal successiveFailedLogins;
    @JsonIgnore
    @Column(name = "EXTERNAL_AUTH_ID")
    private String externalAuthId;
    @JsonIgnore
    @Column(name = "USER_LDAP_DN")
    private String userLdapDn;

    @Column(name = "LAST_UPDATED_STAMP")
    private Date lastUpdatedStamp;
    @JsonIgnore
    @Column(name = "LAST_UPDATED_TX_STAMP")
    private Date lastUpdatedTxStamp;

    @Column(name = "CREATED_STAMP")
    private Date createdStamp;
    @JsonIgnore
    @Column(name = "CREATED_TX_STAMP")
    private Date createdTxStamp;

    @ApiModelProperty("������id")
    @Column(name = "PARTY_ID")
    private String partyId;

    @ApiModelProperty("�ֻ���")
    @Column(name = "MOBILE_NUM")
    private String mobileNum;

    @ApiModelProperty("openid")
    @Column(name = "OPENID")
    private String openid;

    /**
     * �Ƿ���֤
     * Y ��֤�ɹ�
     * M ��֤��
     * N ��֤ʧ��
     * U δ��֤
     */
    @ApiModelProperty("�Ƿ���֤  Y ��֤�ɹ�,M ��֤��,N ��֤ʧ�� U δ��֤")
    @Column(name = "STATUS_AUTH")
    private String statusAuth;

    public String getStatusAuth() {
        return statusAuth;
    }

    public void setStatusAuth(String statusAuth) {
        this.statusAuth = statusAuth;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * ��ȡ��¼�˻�id
     *
     * @return USER_LOGIN_ID - ��¼�˻�id
     */
    public String getUserLoginId() {
        return userLoginId;
    }

    /**
     * ���õ�¼�˻�id
     *
     * @param userLoginId ��¼�˻�id
     */
    public void setUserLoginId(String userLoginId) {
        this.userLoginId = userLoginId;
    }

    /**
     * ��ȡ��¼����
     *
     * @return CURRENT_PASSWORD - ��¼����
     */
    public String getCurrentPassword() {
        return currentPassword;
    }

    /**
     * ���õ�¼����
     *
     * @param currentPassword ��¼����
     */
    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    /**
     * ��ȡ������ʾ
     *
     * @return PASSWORD_HINT - ������ʾ
     */
    public String getPasswordHint() {
        return passwordHint;
    }

    /**
     * ����������ʾ
     *
     * @param passwordHint ������ʾ
     */
    public void setPasswordHint(String passwordHint) {
        this.passwordHint = passwordHint;
    }

    /**
     * @return IS_SYSTEM
     */
    public String getIsSystem() {
        return isSystem;
    }

    /**
     * @param isSystem
     */
    public void setIsSystem(String isSystem) {
        this.isSystem = isSystem;
    }

    /**
     * @return ENABLED
     */
    public String getEnabled() {
        return enabled;
    }

    /**
     * @param enabled
     */
    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    /**
     * @return HAS_LOGGED_OUT
     */
    public String getHasLoggedOut() {
        return hasLoggedOut;
    }

    /**
     * @param hasLoggedOut
     */
    public void setHasLoggedOut(String hasLoggedOut) {
        this.hasLoggedOut = hasLoggedOut;
    }

    /**
     * @return REQUIRE_PASSWORD_CHANGE
     */
    public String getRequirePasswordChange() {
        return requirePasswordChange;
    }

    /**
     * @param requirePasswordChange
     */
    public void setRequirePasswordChange(String requirePasswordChange) {
        this.requirePasswordChange = requirePasswordChange;
    }

    /**
     * @return LAST_CURRENCY_UOM
     */
    public String getLastCurrencyUom() {
        return lastCurrencyUom;
    }

    /**
     * @param lastCurrencyUom
     */
    public void setLastCurrencyUom(String lastCurrencyUom) {
        this.lastCurrencyUom = lastCurrencyUom;
    }

    /**
     * @return LAST_LOCALE
     */
    public String getLastLocale() {
        return lastLocale;
    }

    /**
     * @param lastLocale
     */
    public void setLastLocale(String lastLocale) {
        this.lastLocale = lastLocale;
    }

    /**
     * @return LAST_TIME_ZONE
     */
    public String getLastTimeZone() {
        return lastTimeZone;
    }

    /**
     * @param lastTimeZone
     */
    public void setLastTimeZone(String lastTimeZone) {
        this.lastTimeZone = lastTimeZone;
    }

    /**
     * @return DISABLED_DATE_TIME
     */
    public Date getDisabledDateTime() {
        return disabledDateTime;
    }

    /**
     * @param disabledDateTime
     */
    public void setDisabledDateTime(Date disabledDateTime) {
        this.disabledDateTime = disabledDateTime;
    }

    /**
     * @return SUCCESSIVE_FAILED_LOGINS
     */
    public BigDecimal getSuccessiveFailedLogins() {
        return successiveFailedLogins;
    }

    /**
     * @param successiveFailedLogins
     */
    public void setSuccessiveFailedLogins(BigDecimal successiveFailedLogins) {
        this.successiveFailedLogins = successiveFailedLogins;
    }

    /**
     * @return EXTERNAL_AUTH_ID
     */
    public String getExternalAuthId() {
        return externalAuthId;
    }

    /**
     * @param externalAuthId
     */
    public void setExternalAuthId(String externalAuthId) {
        this.externalAuthId = externalAuthId;
    }

    /**
     * @return USER_LDAP_DN
     */
    public String getUserLdapDn() {
        return userLdapDn;
    }

    /**
     * @param userLdapDn
     */
    public void setUserLdapDn(String userLdapDn) {
        this.userLdapDn = userLdapDn;
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

    /**
     * @return PARTY_ID
     */
    public String getPartyId() {
        return partyId;
    }

    /**
     * @param partyId
     */
    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }

    /**
     * @return MOBILE_NUM
     */
    public String getMobileNum() {
        return mobileNum;
    }

    /**
     * @param mobileNum
     */
    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }


}