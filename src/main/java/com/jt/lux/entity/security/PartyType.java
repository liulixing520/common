package com.jt.lux.entity.security;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

/**
 * 当事人类型
 */
@Table(name = "party_type")
public class PartyType {
    @Id
    @Column(name = "PARTY_TYPE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String partyTypeId;

    @JsonIgnore
    @Column(name = "PARENT_TYPE_ID")
    private String parentTypeId;

    @JsonIgnore
    @Column(name = "HAS_TABLE")
    private String hasTable;
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
     * @return PARENT_TYPE_ID
     */
    public String getParentTypeId() {
        return parentTypeId;
    }

    /**
     * @param parentTypeId
     */
    public void setParentTypeId(String parentTypeId) {
        this.parentTypeId = parentTypeId;
    }

    /**
     * @return HAS_TABLE
     */
    public String getHasTable() {
        return hasTable;
    }

    /**
     * @param hasTable
     */
    public void setHasTable(String hasTable) {
        this.hasTable = hasTable;
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