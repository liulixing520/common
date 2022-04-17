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
 * ��˾
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Corporation {
    @Id
    @Column(name = "PARTY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("��¼�˻�id")
    private String partyId;

    /**
     * ��˾����
     */
    @ApiModelProperty("��˾����")
    @Column(name = "CORPORATE_NAME")
    private String corporateName;

    /**
     * ��˾��ַ
     */
    @ApiModelProperty("��˾��ַ")
    @Column(name = "ADDRESS")
    private String address;

    /**
     * ��֤��һ����
     */
    @ApiModelProperty("��֤��һ����")
    @Column(name = "THREE_IN_ONE")
    private String threeInOne;

    /**
     * ��˾���
     */
    @ApiModelProperty("��˾���")
    @Column(name = "INTRODUCTION")
    private String introduction;
    /**
     * ͷ���ַ
     */
    @ApiModelProperty("ͷ���ַ")
    @Column(name = "HEAD_PIC_URL")
    private String headPicUrl;

    /**
     * ��������
SECURITY ����
CLEANER ����
NANNY ��ķ
NURSING ����
     */
    @ApiModelProperty("��������\n" +
            "SECURITY ����\n" +
            "CLEANER ����\n" +
            "NANNY ��ķ\n" +
            "NURSING ����")
    @Column(name = "SERVICE_TYPE")
    private String serviceType;

    /**
     * ������Ƶid
     */
    @ApiModelProperty("������Ƶid")
    @Column(name = "PROPAGANDA_ID")
    private String propagandaId;

    /**
     * ί��Э����id
     */
    @ApiModelProperty("ί��Э����id")
    @Column(name = "CONSIGNMENT_ID")
    private String consignmentId;

    /**
     * ����id
     */
    @ApiModelProperty("����id")
    @Column(name = "CERTIFICATION_ID")
    private String certificationId;


    /**
     * ��ϵ��
     */
    @ApiModelProperty("��ϵ��")
    @Column(name = "CONTACT")
    private String contact;

    /**
     * ��ϵ�˵绰
     */
    @ApiModelProperty("��ϵ�˵绰")
    @Column(name = "CONTACT_PHONE")
    private String contactPhone;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("����ʱ��")
    @Column(name = "CREATED_STAMP")
    private Date createdStamp;

    @JsonIgnore
    @ApiModelProperty("�޸�ʱ��")
    @Column(name = "LAST_UPDATE_STAMP")
    private Date lastUpdatedStamp;

    /**
     * Ȩ�� �������� Ȩ��Խ������Խ��
     */
    @ApiModelProperty("Ȩ��")
    @Column(name = "WEIGHT")
    private Integer weight;


    /**
     * ����id���ϣ����Ÿ���
     */
    @ApiModelProperty("����id���ϣ����Ÿ���")
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
     * ��ȡ��˾����
     *
     * @return CORPORATE_NAME - ��˾����
     */
    public String getCorporateName() {
        return corporateName;
    }

    /**
     * ���ù�˾����
     *
     * @param corporateName ��˾����
     */
    public void setCorporateName(String corporateName) {
        this.corporateName = corporateName;
    }

    /**
     * ��ȡ��˾��ַ
     *
     * @return ADDRESS - ��˾��ַ
     */
    public String getAddress() {
        return address;
    }

    /**
     * ���ù�˾��ַ
     *
     * @param address ��˾��ַ
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * ��ȡ��֤��һ����
     *
     * @return THREE_IN_ONE - ��֤��һ����
     */
    public String getThreeInOne() {
        return threeInOne;
    }

    /**
     * ������֤��һ����
     *
     * @param threeInOne ��֤��һ����
     */
    public void setThreeInOne(String threeInOne) {
        this.threeInOne = threeInOne;
    }

    /**
     * ��ȡ��˾���
     *
     * @return INTRODUCTION - ��˾���
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * ���ù�˾���
     *
     * @param introduction ��˾���
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    /**
     * ��ȡ��������
SECURITY ����
CLEANER ����
NANNY ��ķ
NURSING ����
     *
     * @return SERVICE_TYPE - ��������
SECURITY ����
CLEANER ����
NANNY ��ķ
NURSING ����
     */
    public String getServiceType() {
        return serviceType;
    }

    /**
     * ���÷�������
SECURITY ����
CLEANER ����
NANNY ��ķ
NURSING ����
     *
     * @param serviceType ��������
SECURITY ����
CLEANER ����
NANNY ��ķ
NURSING ����
     */
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    /**
     * ��ȡ������Ƶid
     *
     * @return PROPAGANDA_ID - ������Ƶid
     */
    public String getPropagandaId() {
        return propagandaId;
    }

    /**
     * ����������Ƶid
     *
     * @param propagandaId ������Ƶid
     */
    public void setPropagandaId(String propagandaId) {
        this.propagandaId = propagandaId;
    }

    /**
     * ��ȡί��Э����id
     *
     * @return CONSIGNMENT_ID - ί��Э����id
     */
    public String getConsignmentId() {
        return consignmentId;
    }

    /**
     * ����ί��Э����id
     *
     * @param consignmentId ί��Э����id
     */
    public void setConsignmentId(String consignmentId) {
        this.consignmentId = consignmentId;
    }

    /**
     * ��ȡ��ϵ��
     *
     * @return CONTACT - ��ϵ��
     */
    public String getContact() {
        return contact;
    }

    /**
     * ������ϵ��
     *
     * @param contact ��ϵ��
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * ��ȡ��ϵ�˵绰
     *
     * @return CONTACT_PHONE - ��ϵ�˵绰
     */
    public String getContactPhone() {
        return contactPhone;
    }

    /**
     * ������ϵ�˵绰
     *
     * @param contactPhone ��ϵ�˵绰
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