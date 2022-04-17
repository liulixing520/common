package com.jt.lux.entity.security;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 公司
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Corporation {
    @Id
    @Column(name = "PARTY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("登录账户id")
    private String partyId;

    /**
     * 公司名称
     */
    @ApiModelProperty("公司名称")
    @Column(name = "CORPORATE_NAME")
    private String corporateName;

    /**
     * 公司地址
     */
    @ApiModelProperty("公司地址")
    @Column(name = "ADDRESS")
    private String address;

    /**
     * 三证合一代码
     */
    @ApiModelProperty("三证合一代码")
    @Column(name = "THREE_IN_ONE")
    private String threeInOne;

    /**
     * 公司简介
     */
    @ApiModelProperty("公司简介")
    @Column(name = "INTRODUCTION")
    private String introduction;
    /**
     * 头像地址
     */
    @ApiModelProperty("头像地址")
    @Column(name = "HEAD_PIC_URL")
    private String headPicUrl;

    /**
     * 服务类型
SECURITY 安保
CLEANER 保洁
NANNY 保姆
NURSING 护工
     */
    @ApiModelProperty("服务类型\n" +
            "SECURITY 安保\n" +
            "CLEANER 保洁\n" +
            "NANNY 保姆\n" +
            "NURSING 护工")
    @Column(name = "SERVICE_TYPE")
    private String serviceType;

    /**
     * 宣传视频id
     */
    @ApiModelProperty("宣传视频id")
    @Column(name = "PROPAGANDA_ID")
    private String propagandaId;

    /**
     * 委托协议书id
     */
    @ApiModelProperty("委托协议书id")
    @Column(name = "CONSIGNMENT_ID")
    private String consignmentId;

    /**
     * 资质id
     */
    @ApiModelProperty("资质id")
    @Column(name = "CERTIFICATION_ID")
    private String certificationId;


    /**
     * 联系人
     */
    @ApiModelProperty("联系人")
    @Column(name = "CONTACT")
    private String contact;

    /**
     * 联系人电话
     */
    @ApiModelProperty("联系人电话")
    @Column(name = "CONTACT_PHONE")
    private String contactPhone;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("创建时间")
    @Column(name = "CREATED_STAMP")
    private Date createdStamp;

    @JsonIgnore
    @ApiModelProperty("修改时间")
    @Column(name = "LAST_UPDATE_STAMP")
    private Date lastUpdatedStamp;

    /**
     * 权重 做排名用 权重越低排名越高
     */
    @ApiModelProperty("权重")
    @Column(name = "WEIGHT")
    private Integer weight;


    /**
     * 附件id集合，逗号隔开
     */
    @ApiModelProperty("附件id集合，逗号隔开")
    @Column(name = "ATTR_IDS")
    private String attIds;

    public String getAttIds() {
        return attIds;
    }

    public void setAttIds(String attIds) {
        this.attIds = attIds;
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
     * 获取公司名称
     *
     * @return CORPORATE_NAME - 公司名称
     */
    public String getCorporateName() {
        return corporateName;
    }

    /**
     * 设置公司名称
     *
     * @param corporateName 公司名称
     */
    public void setCorporateName(String corporateName) {
        this.corporateName = corporateName;
    }

    /**
     * 获取公司地址
     *
     * @return ADDRESS - 公司地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置公司地址
     *
     * @param address 公司地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取三证合一代码
     *
     * @return THREE_IN_ONE - 三证合一代码
     */
    public String getThreeInOne() {
        return threeInOne;
    }

    /**
     * 设置三证合一代码
     *
     * @param threeInOne 三证合一代码
     */
    public void setThreeInOne(String threeInOne) {
        this.threeInOne = threeInOne;
    }

    /**
     * 获取公司简介
     *
     * @return INTRODUCTION - 公司简介
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * 设置公司简介
     *
     * @param introduction 公司简介
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    /**
     * 获取服务类型
SECURITY 安保
CLEANER 保洁
NANNY 保姆
NURSING 护工
     *
     * @return SERVICE_TYPE - 服务类型
SECURITY 安保
CLEANER 保洁
NANNY 保姆
NURSING 护工
     */
    public String getServiceType() {
        return serviceType;
    }

    /**
     * 设置服务类型
SECURITY 安保
CLEANER 保洁
NANNY 保姆
NURSING 护工
     *
     * @param serviceType 服务类型
SECURITY 安保
CLEANER 保洁
NANNY 保姆
NURSING 护工
     */
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    /**
     * 获取宣传视频id
     *
     * @return PROPAGANDA_ID - 宣传视频id
     */
    public String getPropagandaId() {
        return propagandaId;
    }

    /**
     * 设置宣传视频id
     *
     * @param propagandaId 宣传视频id
     */
    public void setPropagandaId(String propagandaId) {
        this.propagandaId = propagandaId;
    }

    /**
     * 获取委托协议书id
     *
     * @return CONSIGNMENT_ID - 委托协议书id
     */
    public String getConsignmentId() {
        return consignmentId;
    }

    /**
     * 设置委托协议书id
     *
     * @param consignmentId 委托协议书id
     */
    public void setConsignmentId(String consignmentId) {
        this.consignmentId = consignmentId;
    }

    /**
     * 获取联系人
     *
     * @return CONTACT - 联系人
     */
    public String getContact() {
        return contact;
    }

    /**
     * 设置联系人
     *
     * @param contact 联系人
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * 获取联系人电话
     *
     * @return CONTACT_PHONE - 联系人电话
     */
    public String getContactPhone() {
        return contactPhone;
    }

    /**
     * 设置联系人电话
     *
     * @param contactPhone 联系人电话
     */
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
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

    public Date getLastUpdatedStamp() {
        return lastUpdatedStamp;
    }

    public void setLastUpdatedStamp(Date lastUpdatedStamp) {
        this.lastUpdatedStamp = lastUpdatedStamp;
    }

    public String getCertificationId() {
        return certificationId;
    }

    public void setCertificationId(String certificationId) {
        this.certificationId = certificationId;
    }
}