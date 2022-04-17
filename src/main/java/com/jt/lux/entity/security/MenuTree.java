package com.jt.lux.entity.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * �˵�
 */
@Table(name = "menu_tree")
public class MenuTree {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * �˵�����
     */
    @ApiModelProperty("�˵�����")
    @Column(name = "ITEM_NAME")
    private String itemName;

    /**
     * �˵�·��
     */
    @ApiModelProperty("�˵�·��")
    @Column(name = "URL_LOCATION")
    private String urlLocation;

    /**
     * �ϼ��˵�
     */
    @ApiModelProperty("�ϼ��˵�")
    @Column(name = "PARENT_ITEM_ID")
    private String parentItemId;
    @JsonIgnore
    @Column(name = "IS_INNER_TAB")
    private String isInnerTab;

    /**
     * �˵�ͼ��
     */
    @ApiModelProperty("�˵�ͼ��")
    @Column(name = "ICON")
    private String icon;

    /**
     * Ȩ��
     */
    @JsonIgnore
    @Column(name = "PERMISSION")
    private String permission;

    /**
     * �Ӳ˵��� ���������ݿ�
     */
    @ApiModelProperty("�Ӳ˵�")
    @Transient
    private List<MenuTree> children;


    @ApiModelProperty("����")
    @Column(name = "RANK")
    private BigDecimal rank;
    @JsonIgnore
    @Column(name = "WEB_SITE_ID")
    private String webSiteId;
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

    public List<MenuTree> getChildren() {
        return children;
    }

    public void setChildren(List<MenuTree> children) {
        this.children = children;
    }

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
     * ��ȡ�˵�����
     *
     * @return ITEM_NAME - �˵�����
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * ���ò˵�����
     *
     * @param itemName �˵�����
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * ��ȡ�˵�·��
     *
     * @return URL_LOCATION - �˵�·��
     */
    public String getUrlLocation() {
        return urlLocation;
    }

    /**
     * ���ò˵�·��
     *
     * @param urlLocation �˵�·��
     */
    public void setUrlLocation(String urlLocation) {
        this.urlLocation = urlLocation;
    }

    /**
     * ��ȡ�ϼ��˵�
     *
     * @return PARENT_ITEM_ID - �ϼ��˵�
     */
    public String getParentItemId() {
        return parentItemId;
    }

    /**
     * �����ϼ��˵�
     *
     * @param parentItemId �ϼ��˵�
     */
    public void setParentItemId(String parentItemId) {
        this.parentItemId = parentItemId;
    }

    /**
     * @return IS_INNER_TAB
     */
    public String getIsInnerTab() {
        return isInnerTab;
    }

    /**
     * @param isInnerTab
     */
    public void setIsInnerTab(String isInnerTab) {
        this.isInnerTab = isInnerTab;
    }

    /**
     * ��ȡ�˵�ͼ��
     *
     * @return ICON - �˵�ͼ��
     */
    public String getIcon() {
        return icon;
    }

    /**
     * ���ò˵�ͼ��
     *
     * @param icon �˵�ͼ��
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * ��ȡȨ��
     *
     * @return PERMISSION - Ȩ��
     */
    public String getPermission() {
        return permission;
    }

    /**
     * ����Ȩ��
     *
     * @param permission Ȩ��
     */
    public void setPermission(String permission) {
        this.permission = permission;
    }

    /**
     * @return RANK
     */
    public BigDecimal getRank() {
        return rank;
    }

    /**
     * @param rank
     */
    public void setRank(BigDecimal rank) {
        this.rank = rank;
    }

    /**
     * @return WEB_SITE_ID
     */
    public String getWebSiteId() {
        return webSiteId;
    }

    /**
     * @param webSiteId
     */
    public void setWebSiteId(String webSiteId) {
        this.webSiteId = webSiteId;
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