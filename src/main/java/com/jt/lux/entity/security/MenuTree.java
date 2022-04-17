package com.jt.lux.entity.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 菜单
 */
@Table(name = "menu_tree")
public class MenuTree {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 菜单名称
     */
    @ApiModelProperty("菜单名称")
    @Column(name = "ITEM_NAME")
    private String itemName;

    /**
     * 菜单路由
     */
    @ApiModelProperty("菜单路由")
    @Column(name = "URL_LOCATION")
    private String urlLocation;

    /**
     * 上级菜单
     */
    @ApiModelProperty("上级菜单")
    @Column(name = "PARENT_ITEM_ID")
    private String parentItemId;
    @JsonIgnore
    @Column(name = "IS_INNER_TAB")
    private String isInnerTab;

    /**
     * 菜单图标
     */
    @ApiModelProperty("菜单图标")
    @Column(name = "ICON")
    private String icon;

    /**
     * 权限
     */
    @JsonIgnore
    @Column(name = "PERMISSION")
    private String permission;

    /**
     * 子菜单， 不参与数据库
     */
    @ApiModelProperty("子菜单")
    @Transient
    private List<MenuTree> children;


    @ApiModelProperty("排序")
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
     * 获取菜单名称
     *
     * @return ITEM_NAME - 菜单名称
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * 设置菜单名称
     *
     * @param itemName 菜单名称
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * 获取菜单路由
     *
     * @return URL_LOCATION - 菜单路由
     */
    public String getUrlLocation() {
        return urlLocation;
    }

    /**
     * 设置菜单路由
     *
     * @param urlLocation 菜单路由
     */
    public void setUrlLocation(String urlLocation) {
        this.urlLocation = urlLocation;
    }

    /**
     * 获取上级菜单
     *
     * @return PARENT_ITEM_ID - 上级菜单
     */
    public String getParentItemId() {
        return parentItemId;
    }

    /**
     * 设置上级菜单
     *
     * @param parentItemId 上级菜单
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
     * 获取菜单图标
     *
     * @return ICON - 菜单图标
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置菜单图标
     *
     * @param icon 菜单图标
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 获取权限
     *
     * @return PERMISSION - 权限
     */
    public String getPermission() {
        return permission;
    }

    /**
     * 设置权限
     *
     * @param permission 权限
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