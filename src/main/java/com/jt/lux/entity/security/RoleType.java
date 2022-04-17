package com.jt.lux.entity.security;

import javax.persistence.*;
import java.util.Date;


/**
 * ½ÇÉ«ÀàÐÍ
 */
@Table(name = "role_type")
public class RoleType {
    @Id
    @Column(name = "ROLE_TYPE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String roleTypeId;

    @Column(name = "PARENT_TYPE_ID")
    private String parentTypeId;

    @Column(name = "HAS_TABLE")
    private String hasTable;

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
     * @return ROLE_TYPE_ID
     */
    public String getRoleTypeId() {
        return roleTypeId;
    }

    /**
     * @param roleTypeId
     */
    public void setRoleTypeId(String roleTypeId) {
        this.roleTypeId = roleTypeId;
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