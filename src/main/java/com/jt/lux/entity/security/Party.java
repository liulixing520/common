package com.jt.lux.entity.security;

import java.util.Date;
import javax.persistence.*;

/**
 * 当事人
 */
public class Party {
    @Id
    @Column(name = "PARTY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String partyId;

    /**
     * 当事人类型   PERSON:个人
     */
    @Column(name = "PARTY_TYPE_ID")
    private String partyTypeId;

    @Column(name = "EXTERNAL_ID")
    private String externalId;

    @Column(name = "PREFERRED_CURRENCY_UOM_ID")
    private String preferredCurrencyUomId;

    /**
     * 状态id
     */
    @Column(name = "STATUS_ID")
    private String statusId;

    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @Column(name = "CREATED_BY_USER_LOGIN")
    private String createdByUserLogin;

    @Column(name = "LAST_MODIFIED_DATE")
    private Date lastModifiedDate;

    @Column(name = "LAST_MODIFIED_BY_USER_LOGIN")
    private String lastModifiedByUserLogin;

    @Column(name = "DATA_SOURCE_ID")
    private String dataSourceId;

    @Column(name = "IS_UNREAD")
    private String isUnread;

    @Column(name = "LAST_UPDATED_STAMP")
    private Date lastUpdatedStamp;

    @Column(name = "LAST_UPDATED_TX_STAMP")
    private Date lastUpdatedTxStamp;

    @Column(name = "CREATED_STAMP")
    private Date createdStamp;

    @Column(name = "CREATED_TX_STAMP")
    private Date createdTxStamp;

    @Column(name = "DESCRIPTION")
    private String description;

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
     * @return PARTY_TYPE_ID
     */
    public String getPartyTypeId() {
        return partyTypeId;
    }

    /**
     * @param partyTypeId
     */
    public void setPartyTypeId(String partyTypeId) {
        this.partyTypeId = partyTypeId;
    }

    /**
     * @return EXTERNAL_ID
     */
    public String getExternalId() {
        return externalId;
    }

    /**
     * @param externalId
     */
    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    /**
     * @return PREFERRED_CURRENCY_UOM_ID
     */
    public String getPreferredCurrencyUomId() {
        return preferredCurrencyUomId;
    }

    /**
     * @param preferredCurrencyUomId
     */
    public void setPreferredCurrencyUomId(String preferredCurrencyUomId) {
        this.preferredCurrencyUomId = preferredCurrencyUomId;
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
     * @return CREATED_DATE
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * @param createdDate
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * @return CREATED_BY_USER_LOGIN
     */
    public String getCreatedByUserLogin() {
        return createdByUserLogin;
    }

    /**
     * @param createdByUserLogin
     */
    public void setCreatedByUserLogin(String createdByUserLogin) {
        this.createdByUserLogin = createdByUserLogin;
    }

    /**
     * @return LAST_MODIFIED_DATE
     */
    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    /**
     * @param lastModifiedDate
     */
    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    /**
     * @return LAST_MODIFIED_BY_USER_LOGIN
     */
    public String getLastModifiedByUserLogin() {
        return lastModifiedByUserLogin;
    }

    /**
     * @param lastModifiedByUserLogin
     */
    public void setLastModifiedByUserLogin(String lastModifiedByUserLogin) {
        this.lastModifiedByUserLogin = lastModifiedByUserLogin;
    }

    /**
     * @return DATA_SOURCE_ID
     */
    public String getDataSourceId() {
        return dataSourceId;
    }

    /**
     * @param dataSourceId
     */
    public void setDataSourceId(String dataSourceId) {
        this.dataSourceId = dataSourceId;
    }

    /**
     * @return IS_UNREAD
     */
    public String getIsUnread() {
        return isUnread;
    }

    /**
     * @param isUnread
     */
    public void setIsUnread(String isUnread) {
        this.isUnread = isUnread;
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
     * @return DESCRIPTION
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}