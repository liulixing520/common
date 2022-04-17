package com.jt.lux.entity.system;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Ã¶¾Ù
 */
public class Enumeration {
    @Id
    @Column(name = "ENUM_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String enumId;

    @Column(name = "ENUM_TYPE_ID")
    private String enumTypeId;

    @Column(name = "ENUM_CODE")
    private String enumCode;

    @Column(name = "SEQUENCE_ID")
    private String sequenceId;

    @Column(name = "DESCRIPTION")
    private String description;

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

    /**
     * @return ENUM_ID
     */
    public String getEnumId() {
        return enumId;
    }

    /**
     * @param enumId
     */
    public void setEnumId(String enumId) {
        this.enumId = enumId;
    }

    /**
     * @return ENUM_TYPE_ID
     */
    public String getEnumTypeId() {
        return enumTypeId;
    }

    /**
     * @param enumTypeId
     */
    public void setEnumTypeId(String enumTypeId) {
        this.enumTypeId = enumTypeId;
    }

    /**
     * @return ENUM_CODE
     */
    public String getEnumCode() {
        return enumCode;
    }

    /**
     * @param enumCode
     */
    public void setEnumCode(String enumCode) {
        this.enumCode = enumCode;
    }

    /**
     * @return SEQUENCE_ID
     */
    public String getSequenceId() {
        return sequenceId;
    }

    /**
     * @param sequenceId
     */
    public void setSequenceId(String sequenceId) {
        this.sequenceId = sequenceId;
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