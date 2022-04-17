package com.jt.lux.entity.security;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * ��Ա
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Id
    @Column(name = "PARTY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String partyId;

    /**
     * �ƺ�
     */
    @ApiModelProperty("�ƺ�")
    @Column(name = "SALUTATION")
    private String salutation;

    /**
     * ��
     */
    @ApiModelProperty("����-ȡ���ֵ")
    @Column(name = "FIRST_NAME")
    private String firstName;

    /**
     * �м���
     */
    @ApiModelProperty("�м���")
    @Column(name = "MIDDLE_NAME")
    private String middleName;

    /**
     * ���һ������
     */
    @ApiModelProperty("���һ������")
    @Column(name = "LAST_NAME")
    private String lastName;

    /**
     * ͷ��
     */
    @ApiModelProperty("ͷ��")
    @Column(name = "PERSONAL_TITLE")
    private String personalTitle;
    @JsonIgnore
    @Column(name = "SUFFIX")
    private String suffix;

    /**
     * �ǳ�
     */
    @ApiModelProperty("�ǳ�")
    @Column(name = "NICKNAME")
    private String nickname;
    @JsonIgnore
    @Column(name = "FIRST_NAME_LOCAL")
    private String firstNameLocal;
    @JsonIgnore
    @Column(name = "MIDDLE_NAME_LOCAL")
    private String middleNameLocal;
    @JsonIgnore
    @Column(name = "LAST_NAME_LOCAL")
    private String lastNameLocal;

    @JsonIgnore
    @Column(name = "OTHER_LOCAL")
    private String otherLocal;


    /**
     * �Ա�
     */
    @ApiModelProperty("�Ա� M:��  F:Ů")
    @Column(name = "GENDER")
    private String gender;

    /**
     * ����
     */
    @ApiModelProperty("����")
    @Column(name = "BIRTH_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date birthDate;

    /**
     * ����
     */
    @Transient
    @ApiModelProperty("����")
    private String age;

    /**
     * ��������
     */
    @JsonIgnore
    @Column(name = "DECEASED_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date deceasedDate;

    /**
     * ���
     */
    @ApiModelProperty("���")
    @Column(name = "HEIGHT")
    private Double height;
    /**
     * ����
     */
    @ApiModelProperty("����")
    @Column(name = "WEIGHT")
    private Double weight;
    @JsonIgnore
    @Column(name = "MOTHERS_MAIDEN_NAME")
    private String mothersMaidenName;
    @JsonIgnore
    @Column(name = "MARITAL_STATUS")
    private String maritalStatus;

    /**
     * ��ᱣ�Ϻ���
     */
    @JsonIgnore
    @ApiModelProperty("��ᱣ�Ϻ���")
    @Column(name = "SOCIAL_SECURITY_NUMBER")
    private String socialSecurityNumber;

    /**
     * ���պ�
     */
    @ApiModelProperty("���պ�")
    @Column(name = "PASSPORT_NUMBER")
    private String passportNumber;

    /**
     * ���չ�������
     */
    @ApiModelProperty("���չ�������")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "PASSPORT_EXPIRE_DATE")
    private Date passportExpireDate;
    @JsonIgnore
    @Column(name = "TOTAL_YEARS_WORK_EXPERIENCE")
    private Double totalYearsWorkExperience;

    @ApiModelProperty("��ע")
    @Column(name = "COMMENTS")
    private String comments;
    @JsonIgnore
    @Column(name = "EMPLOYMENT_STATUS_ENUM_ID")
    private String employmentStatusEnumId;
    @JsonIgnore
    @Column(name = "RESIDENCE_STATUS_ENUM_ID")
    private String residenceStatusEnumId;
    /**
     * ��ְ��λ
     */
    @ApiModelProperty("��ְ��λ")
    @Column(name = "APPLY_POSITION")
    private String applyPosition;

    /**
     * ����
     */
    @JsonIgnore
    @ApiModelProperty("����")
    @Column(name = "OCCUPATION")
    private String occupation;
    @JsonIgnore
    @Column(name = "YEARS_WITH_EMPLOYER")
    private BigDecimal yearsWithEmployer;
    @JsonIgnore
    @Column(name = "MONTHS_WITH_EMPLOYER")
    private BigDecimal monthsWithEmployer;
    @JsonIgnore
    @Column(name = "EXISTING_CUSTOMER")
    private String existingCustomer;

    /**
     * ֤������
     */
    @ApiModelProperty("֤������")
    @Column(name = "CARD_TYPE")
    private String cardType;

    /**
     * ���֤����
     */
    @ApiModelProperty("���֤����")
    @Column(name = "ID_CARD")
    private String idCard;
    /**
     * ���֤��������
     */
    @ApiModelProperty("���֤��������")
    @Column(name = "ID_CARD_EXPIRE_DATE")
    private String idCardExpireDate;

    /**
     * ���֤����
     */
    @ApiModelProperty("���֤����")
    @Column(name = "ID_CARD_START_DATE")
    private String idCardStartDate;

    @JsonIgnore
    @Column(name = "LAST_UPDATED_STAMP")
    private Date lastUpdatedStamp;
    @JsonIgnore
    @Column(name = "LAST_UPDATED_TX_STAMP")
    private Date lastUpdatedTxStamp;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "CREATED_STAMP")
    private Date createdStamp;
    @JsonIgnore
    @Column(name = "CREATED_TX_STAMP")
    private Date createdTxStamp;
    @JsonIgnore
    @Column(name = "PERSON_SORT_NUM")
    private BigDecimal personSortNum;
    @JsonIgnore
    @Column(name = "PERSON_SORT_DEPART_ID")
    private String personSortDepartId;
    @JsonIgnore
    @Column(name = "UFM_FILE_PATH")
    private String ufmFilePath;

    /**
     * �ֻ���
     */
    @ApiModelProperty("�ֻ���")
    @Column(name = "MOBILE_NUM")
    private String mobileNum;

    /**
     * ������
     */
    @ApiModelProperty("�ƺ�")
    @Column(name = "TELECOM_NUM")
    private String telecomNum;

    /**
     * ����
     */
    @ApiModelProperty("����")
    @Column(name = "EMAIL")
    private String email;

    /**
     * ʡ����
     */
    @ApiModelProperty("ʡ����")
    @Column(name = "PROVINCE")
    private String province;

    /**
     * �б���
     */
    @ApiModelProperty("�б���")
    @Column(name = "CITY")
    private String city;

    /**
     * �أ���������
     */
    @ApiModelProperty("�أ���������")
    @Column(name = "CNTY")
    private String cnty;


    /**
     * ��ϸ��ַ
     */
    @ApiModelProperty("��ϸ��ַ")
    @Column(name = "ADDRESS")
    private String address;

    /**
     * ����������
     */
    @ApiModelProperty("����������")
    @Column(name = "EMRGENCY_CONTACT")
    private String emergencyContact;
    /**
     * ���������˵绰
     */
    @ApiModelProperty("���������˵绰")
    @Column(name = "EMRGENCY_CONTACT_PHONE")
    private String emergencyContactPhone;

    /**
     * ͷ���ַ
     */
    @ApiModelProperty("ͷ���ַ")
    @Column(name = "HEAD_PIC_URL")
    private String headPicUrl;

    /**
     * ����
     */
    @ApiModelProperty("����")
    @Column(name = "PROFESSION")
    private String profession;

    /**
     * �������
     */
    @JsonIgnore
    @Column(name = "MARRIAGE")
    private String marriage;


    @JsonIgnore
    @Column(name = "OTHER_MARRIAGE_DESC")
    private String otherMarriageDesc;

    /**
     * ����
     */
    @ApiModelProperty("����")
    @Column(name = "NATION")
    private String nation;

    /**
     * ����
     */
    @ApiModelProperty("����")
    @Column(name = "NATIVE_PLACE")
    private String nativePlace;

    /**
     * ѧ��
     */
    @ApiModelProperty("ѧ��")
    @Column(name = "EDUCATION")
    private String education;

    /**
     * ���˼��
     */
    @ApiModelProperty("���˼��")
    @Column(name = "INTRODUCTION")
    private String introduction;

    /**
     * ����ȫ����
     */
    @ApiModelProperty("����ȫ����")
    @Column(name = "PHOTOGRAPH_ID")
    private String photographId;


    /**
     * ����������Ƶ
     */
    @ApiModelProperty("����������Ƶ")
    @Column(name = "VIDEO_ID")
    private String videoId;

    /**
     * ����id
     */
    @ApiModelProperty("����id")
    @Column(name = "CERTIFICATION_ID")
    private String certificationId;

    /**
     * ����id���ϣ����Ÿ���
     */
    @ApiModelProperty("����id���ϣ����Ÿ���")
    @Column(name = "ATTR_IDS")
    private String attIds;

    /**
     * ������partyId
     */
    @Transient
    @ApiModelProperty("������partyId")
    private String refPartyId;


    public String getPhotographId() {
        return photographId;
    }

    public void setPhotographId(String photographId) {
        this.photographId = photographId;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getCertificationId() {
        return certificationId;
    }

    public void setCertificationId(String certificationId) {
        this.certificationId = certificationId;
    }

    public String getAttIds() {
        return attIds;
    }

    public void setAttIds(String attIds) {
        this.attIds = attIds;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getEmergencyContactPhone() {
        return emergencyContactPhone;
    }

    public void setEmergencyContactPhone(String emergencyContactPhone) {
        this.emergencyContactPhone = emergencyContactPhone;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
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
     * @return SALUTATION
     */
    public String getSalutation() {
        return salutation;
    }

    /**
     * @param salutation
     */
    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    /**
     * @return FIRST_NAME
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return MIDDLE_NAME
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * @param middleName
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * @return LAST_NAME
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return PERSONAL_TITLE
     */
    public String getPersonalTitle() {
        return personalTitle;
    }

    /**
     * @param personalTitle
     */
    public void setPersonalTitle(String personalTitle) {
        this.personalTitle = personalTitle;
    }

    /**
     * @return SUFFIX
     */
    public String getSuffix() {
        return suffix;
    }

    /**
     * @param suffix
     */
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    /**
     * @return NICKNAME
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * @return FIRST_NAME_LOCAL
     */
    public String getFirstNameLocal() {
        return firstNameLocal;
    }

    /**
     * @param firstNameLocal
     */
    public void setFirstNameLocal(String firstNameLocal) {
        this.firstNameLocal = firstNameLocal;
    }

    /**
     * @return MIDDLE_NAME_LOCAL
     */
    public String getMiddleNameLocal() {
        return middleNameLocal;
    }

    /**
     * @param middleNameLocal
     */
    public void setMiddleNameLocal(String middleNameLocal) {
        this.middleNameLocal = middleNameLocal;
    }

    /**
     * @return LAST_NAME_LOCAL
     */
    public String getLastNameLocal() {
        return lastNameLocal;
    }

    /**
     * @param lastNameLocal
     */
    public void setLastNameLocal(String lastNameLocal) {
        this.lastNameLocal = lastNameLocal;
    }

    /**
     * @return OTHER_LOCAL
     */
    public String getOtherLocal() {
        return otherLocal;
    }

    /**
     * @param otherLocal
     */
    public void setOtherLocal(String otherLocal) {
        this.otherLocal = otherLocal;
    }

    public String getApplyPosition() {
        return applyPosition;
    }

    public void setApplyPosition(String applyPosition) {
        this.applyPosition = applyPosition;
    }

    /**
     * @return GENDER
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return BIRTH_DATE
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * @param birthDate
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * @return DECEASED_DATE
     */
    public Date getDeceasedDate() {
        return deceasedDate;
    }

    /**
     * @param deceasedDate
     */
    public void setDeceasedDate(Date deceasedDate) {
        this.deceasedDate = deceasedDate;
    }

    /**
     * @return HEIGHT
     */
    public Double getHeight() {
        return height;
    }

    /**
     * @param height
     */
    public void setHeight(Double height) {
        this.height = height;
    }

    /**
     * @return WEIGHT
     */
    public Double getWeight() {
        return weight;
    }

    /**
     * @param weight
     */
    public void setWeight(Double weight) {
        this.weight = weight;
    }

    /**
     * @return MOTHERS_MAIDEN_NAME
     */
    public String getMothersMaidenName() {
        return mothersMaidenName;
    }

    /**
     * @param mothersMaidenName
     */
    public void setMothersMaidenName(String mothersMaidenName) {
        this.mothersMaidenName = mothersMaidenName;
    }

    /**
     * @return MARITAL_STATUS
     */
    public String getMaritalStatus() {
        return maritalStatus;
    }

    /**
     * @param maritalStatus
     */
    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    /**
     * @return SOCIAL_SECURITY_NUMBER
     */
    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    /**
     * @param socialSecurityNumber
     */
    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    /**
     * @return PASSPORT_NUMBER
     */
    public String getPassportNumber() {
        return passportNumber;
    }

    /**
     * @param passportNumber
     */
    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    /**
     * @return PASSPORT_EXPIRE_DATE
     */
    public Date getPassportExpireDate() {
        return passportExpireDate;
    }

    /**
     * @param passportExpireDate
     */
    public void setPassportExpireDate(Date passportExpireDate) {
        this.passportExpireDate = passportExpireDate;
    }

    /**
     * @return TOTAL_YEARS_WORK_EXPERIENCE
     */
    public Double getTotalYearsWorkExperience() {
        return totalYearsWorkExperience;
    }

    /**
     * @param totalYearsWorkExperience
     */
    public void setTotalYearsWorkExperience(Double totalYearsWorkExperience) {
        this.totalYearsWorkExperience = totalYearsWorkExperience;
    }

    /**
     * @return COMMENTS
     */
    public String getComments() {
        return comments;
    }

    /**
     * @param comments
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * @return EMPLOYMENT_STATUS_ENUM_ID
     */
    public String getEmploymentStatusEnumId() {
        return employmentStatusEnumId;
    }

    /**
     * @param employmentStatusEnumId
     */
    public void setEmploymentStatusEnumId(String employmentStatusEnumId) {
        this.employmentStatusEnumId = employmentStatusEnumId;
    }

    /**
     * @return RESIDENCE_STATUS_ENUM_ID
     */
    public String getResidenceStatusEnumId() {
        return residenceStatusEnumId;
    }

    /**
     * @param residenceStatusEnumId
     */
    public void setResidenceStatusEnumId(String residenceStatusEnumId) {
        this.residenceStatusEnumId = residenceStatusEnumId;
    }

    /**
     * @return OCCUPATION
     */
    public String getOccupation() {
        return occupation;
    }

    /**
     * @param occupation
     */
    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    /**
     * @return YEARS_WITH_EMPLOYER
     */
    public BigDecimal getYearsWithEmployer() {
        return yearsWithEmployer;
    }

    /**
     * @param yearsWithEmployer
     */
    public void setYearsWithEmployer(BigDecimal yearsWithEmployer) {
        this.yearsWithEmployer = yearsWithEmployer;
    }

    /**
     * @return MONTHS_WITH_EMPLOYER
     */
    public BigDecimal getMonthsWithEmployer() {
        return monthsWithEmployer;
    }

    /**
     * @param monthsWithEmployer
     */
    public void setMonthsWithEmployer(BigDecimal monthsWithEmployer) {
        this.monthsWithEmployer = monthsWithEmployer;
    }

    /**
     * @return EXISTING_CUSTOMER
     */
    public String getExistingCustomer() {
        return existingCustomer;
    }

    /**
     * @param existingCustomer
     */
    public void setExistingCustomer(String existingCustomer) {
        this.existingCustomer = existingCustomer;
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
     * @return PERSON_SORT_NUM
     */
    public BigDecimal getPersonSortNum() {
        return personSortNum;
    }

    /**
     * @param personSortNum
     */
    public void setPersonSortNum(BigDecimal personSortNum) {
        this.personSortNum = personSortNum;
    }

    /**
     * @return PERSON_SORT_DEPART_ID
     */
    public String getPersonSortDepartId() {
        return personSortDepartId;
    }

    /**
     * @param personSortDepartId
     */
    public void setPersonSortDepartId(String personSortDepartId) {
        this.personSortDepartId = personSortDepartId;
    }

    /**
     * @return UFM_FILE_PATH
     */
    public String getUfmFilePath() {
        return ufmFilePath;
    }

    /**
     * @param ufmFilePath
     */
    public void setUfmFilePath(String ufmFilePath) {
        this.ufmFilePath = ufmFilePath;
    }

    /**
     * @return MOBILE_NUM
     */
    public String getMobileNum() {
        return mobileNum;
    }

    /**
     * @param mobileNum
     */
    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    /**
     * @return TELECOM_NUM
     */
    public String getTelecomNum() {
        return telecomNum;
    }

    /**
     * @param telecomNum
     */
    public void setTelecomNum(String telecomNum) {
        this.telecomNum = telecomNum;
    }

    /**
     * @return EMAIL
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return ADDRESS
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return HEAD_PIC_URL
     */
    public String getHeadPicUrl() {
        return headPicUrl;
    }

    /**
     * @param headPicUrl
     */
    public void setHeadPicUrl(String headPicUrl) {
        this.headPicUrl = headPicUrl;
    }

    /**
     * @return PROFESSION
     */
    public String getProfession() {
        return profession;
    }

    /**
     * @param profession
     */
    public void setProfession(String profession) {
        this.profession = profession;
    }

    /**
     * @return MARRIAGE
     */
    public String getMarriage() {
        return marriage;
    }

    /**
     * @param marriage
     */
    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }

    /**
     * @return NATION
     */
    public String getNation() {
        return nation;
    }

    /**
     * @param nation
     */
    public void setNation(String nation) {
        this.nation = nation;
    }


    /**
     * @return OTHER_MARRIAGE_DESC
     */
    public String getOtherMarriageDesc() {
        return otherMarriageDesc;
    }

    /**
     * @param otherMarriageDesc
     */
    public void setOtherMarriageDesc(String otherMarriageDesc) {
        this.otherMarriageDesc = otherMarriageDesc;
    }
}