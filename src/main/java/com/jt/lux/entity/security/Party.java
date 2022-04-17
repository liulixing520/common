package com.jt.lux.entity.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * ������
 */
@Data
public class Party {
    @Id
    @Column(name = "PARTY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String partyId;

    /**
     * ����������   PERSON:����  CORPORATION ��˾
     */
    @ApiModelProperty("����������   PERSON:����  CORPORATION ��˾  ")
    @Column(name = "PARTY_TYPE_ID")
    private String partyTypeId;

    @JsonIgnore
    @Column(name = "EXTERNAL_ID")
    private String externalId;

    @JsonIgnore
    @Column(name = "PREFERRED_CURRENCY_UOM_ID")
    private String preferredCurrencyUomId;

    /**
     * ״̬id
     */
    @JsonIgnore
    @Column(name = "STATUS_ID")
    private String statusId;

    /**
     * �Ǽ�
     */
    @ApiModelProperty("�Ǽ�")
    @Column(name = "STARS_NUM")
    private String starsNum;

    /**
     * ������
     */
    @ApiModelProperty("������")
    @Column(name = "EVALUATE_NUM")
    private String evaluateNum;

    /**
     * �������
     */
    @ApiModelProperty("�������")
    @Column(name = "SERVICE_NUM")
    private String serviceNum;

    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @JsonIgnore
    @Column(name = "CREATED_BY_USER_LOGIN")
    private String createdByUserLogin;

    @JsonIgnore
    @Column(name = "LAST_MODIFIED_DATE")
    private Date lastModifiedDate;

    @JsonIgnore
    @Column(name = "LAST_MODIFIED_BY_USER_LOGIN")
    private String lastModifiedByUserLogin;

    @JsonIgnore
    @Column(name = "DATA_SOURCE_ID")
    private String dataSourceId;

    @JsonIgnore
    @Column(name = "IS_UNREAD")
    private String isUnread;

    @JsonIgnore
    @Column(name = "LAST_UPDATED_STAMP")
    private Date lastUpdatedStamp;

    @JsonIgnore
    @Column(name = "LAST_UPDATED_TX_STAMP")
    private Date lastUpdatedTxStamp;

    @JsonIgnore
    @Column(name = "CREATED_STAMP")
    private Date createdStamp;

    @JsonIgnore
    @Column(name = "CREATED_TX_STAMP")
    private Date createdTxStamp;

    @JsonIgnore
    @Column(name = "DESCRIPTION")
    private String description;

    @ApiModelProperty("��ά��ͼƬ")
    @Column(name = "QR_CODE")
    private String qrCode;

    @ApiModelProperty("�Ƿ��ѿ���  N:δ����  Y:�ѿ���")
    @Column(name = "IS_OPEN_ACCOUNT")
    private String isOpenAccount;

    @ApiModelProperty("���ƿͻ���")
    @Column(name = "CLIENT_ID")
    private String clientId;


    public String getStarsNum() {
        return starsNum;
    }

    public void setStarsNum(String starsNum) {
        this.starsNum = starsNum;
    }

    public String getEvaluateNum() {
        return evaluateNum;
    }

    public void setEvaluateNum(String evaluateNum) {
        this.evaluateNum = evaluateNum;
    }

    public String getServiceNum() {
        return serviceNum;
    }

    public void setServiceNum(String serviceNum) {
        this.serviceNum = serviceNum;
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