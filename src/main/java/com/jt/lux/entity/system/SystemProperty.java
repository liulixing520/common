package com.jt.lux.entity.system;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 系统参数
 */
@Table(name = "system_property")
public class SystemProperty {

    @Id
    @ApiModelProperty("系统资源id")
    @Column(name = "SYSTEM_RESOURCE_ID")
    private String systemResourceId;
    @Id
    @ApiModelProperty("系统配置id")
    @Column(name = "SYSTEM_PROPERTY_ID")
    private String systemPropertyId;
    @ApiModelProperty("系统配置值")
    @Column(name = "SYSTEM_PROPERTY_VALUE")
    private String systemPropertyValue;
    @ApiModelProperty("描述")
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
     * @return SYSTEM_RESOURCE_ID
     */
    public String getSystemResourceId() {
        return systemResourceId;
    }

    /**
     * @param systemResourceId
     */
    public void setSystemResourceId(String systemResourceId) {
        this.systemResourceId = systemResourceId;
    }

    /**
     * @return SYSTEM_PROPERTY_ID
     */
    public String getSystemPropertyId() {
        return systemPropertyId;
    }

    /**
     * @param systemPropertyId
     */
    public void setSystemPropertyId(String systemPropertyId) {
        this.systemPropertyId = systemPropertyId;
    }

    /**
     * @return SYSTEM_PROPERTY_VALUE
     */
    public String getSystemPropertyValue() {
        return systemPropertyValue;
    }

    /**
     * @param systemPropertyValue
     */
    public void setSystemPropertyValue(String systemPropertyValue) {
        this.systemPropertyValue = systemPropertyValue;
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