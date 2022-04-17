package com.jt.lux.entity.security;

import javax.persistence.*;
import java.util.Date;

/**
 * Ȩ�޲˵�
 */
@Table(name = "security_permission_menu")
public class SecurityPermissionMenu {
    /**
     * Ȩ��id
     */
    @Id
    @Column(name = "PERMISSION_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String permissionId;

    /**
     * �˵�id
     */
    @Id
    @Column(name = "MENU_ID")
    private String menuId;

    @Column(name = "LAST_UPDATED_STAMP")
    private Date lastUpdatedStamp;

    @Column(name = "LAST_UPDATED_TX_STAMP")
    private Date lastUpdatedTxStamp;

    @Column(name = "CREATED_STAMP")
    private Date createdStamp;

    @Column(name = "CREATED_TX_STAMP")
    private Date createdTxStamp;

    /**
     * ��ȡȨ��id
     *
     * @return PERMISSION_ID - Ȩ��id
     */
    public String getPermissionId() {
        return permissionId;
    }

    /**
     * ����Ȩ��id
     *
     * @param permissionId Ȩ��id
     */
    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    /**
     * ��ȡ�˵�id
     *
     * @return MENU_ID - �˵�id
     */
    public String getMenuId() {
        return menuId;
    }

    /**
     * ���ò˵�id
     *
     * @param menuId �˵�id
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId;
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