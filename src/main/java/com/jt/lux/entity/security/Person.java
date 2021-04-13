package com.jt.lux.entity.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

/**
 * 会员
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
     * 称呼
     */
    @Column(name = "SALUTATION")
    private String salutation;

    /**
     * 姓
     */
    @Column(name = "FIRST_NAME")
    private String firstName;

    /**
     * 中间名
     */
    @Column(name = "MIDDLE_NAME")
    private String middleName;

    /**
     * 最后一个名字
     */
    @Column(name = "LAST_NAME")
    private String lastName;

    /**
     * 头衔
     */
    @Column(name = "PERSONAL_TITLE")
    private String personalTitle;

    @Column(name = "SUFFIX")
    private String suffix;

    @Column(name = "OPEN_ID")
    private String openId;

    /**
     * 昵称
     */
    @Column(name = "NICKNAME")
    private String nickname;

    @Column(name = "FIRST_NAME_LOCAL")
    private String firstNameLocal;

    @Column(name = "MIDDLE_NAME_LOCAL")
    private String middleNameLocal;

    @Column(name = "LAST_NAME_LOCAL")
    private String lastNameLocal;

    @Column(name = "OTHER_LOCAL")
    private String otherLocal;

    /**
     * 成员id
     */
    @Column(name = "MEMBER_ID")
    private String memberId;

    /**
     * 性别
     */
    @Column(name = "GENDER")
    private String gender;

    /**
     * 生日
     */
    @Column(name = "BIRTH_DATE")
    private Date birthDate;

    /**
     * 死亡日期
     */
    @Column(name = "DECEASED_DATE")
    private Date deceasedDate;

    @Column(name = "HEIGHT")
    private Double height;

    @Column(name = "WEIGHT")
    private Double weight;

    @Column(name = "MOTHERS_MAIDEN_NAME")
    private String mothersMaidenName;

    @Column(name = "MARITAL_STATUS")
    private String maritalStatus;

    /**
     * 社会保障号码
     */
    @Column(name = "SOCIAL_SECURITY_NUMBER")
    private String socialSecurityNumber;

    /**
     * 护照号
     */
    @Column(name = "PASSPORT_NUMBER")
    private String passportNumber;

    /**
     * 护照过期日期
     */
    @Column(name = "PASSPORT_EXPIRE_DATE")
    private Date passportExpireDate;

    @Column(name = "TOTAL_YEARS_WORK_EXPERIENCE")
    private Double totalYearsWorkExperience;

    @Column(name = "COMMENTS")
    private String comments;

    @Column(name = "EMPLOYMENT_STATUS_ENUM_ID")
    private String employmentStatusEnumId;

    @Column(name = "RESIDENCE_STATUS_ENUM_ID")
    private String residenceStatusEnumId;

    /**
     * 工作
     */
    @Column(name = "OCCUPATION")
    private String occupation;

    @Column(name = "YEARS_WITH_EMPLOYER")
    private BigDecimal yearsWithEmployer;

    @Column(name = "MONTHS_WITH_EMPLOYER")
    private BigDecimal monthsWithEmployer;

    @Column(name = "EXISTING_CUSTOMER")
    private String existingCustomer;

    /**
     * 身份证号码
     */
    @Column(name = "CARD_ID")
    private String cardId;

    @Column(name = "LAST_UPDATED_STAMP")
    private Date lastUpdatedStamp;

    @Column(name = "LAST_UPDATED_TX_STAMP")
    private Date lastUpdatedTxStamp;

    @Column(name = "CREATED_STAMP")
    private Date createdStamp;

    @Column(name = "CREATED_TX_STAMP")
    private Date createdTxStamp;

    @Column(name = "PERSON_SORT_NUM")
    private BigDecimal personSortNum;

    @Column(name = "PERSON_SORT_DEPART_ID")
    private String personSortDepartId;

    @Column(name = "UFM_FILE_PATH")
    private String ufmFilePath;

    /**
     * 手机号
     */
    @Column(name = "MOBILE_NUM")
    private String mobileNum;

    /**
     * 座机号
     */
    @Column(name = "TELECOM_NUM")
    private String telecomNum;

    /**
     * 邮箱
     */
    @Column(name = "EMAIL")
    private String email;

    /**
     * 详细地址
     */
    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "HEAD_PIC_URL")
    private String headPicUrl;


    @Column(name = "PROFESSION")
    private String profession;

    @Column(name = "MARRIAGE")
    private String marriage;

    /**
     * 国籍
     */
    @Column(name = "NATION")
    private String nation;


    @Column(name = "OTHER_MARRIAGE_DESC")
    private String otherMarriageDesc;

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

    /**
     * @return MEMBER_ID
     */
    public String getMemberId() {
        return memberId;
    }

    /**
     * @param memberId
     */
    public void setMemberId(String memberId) {
        this.memberId = memberId;
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
     * @return CARD_ID
     */
    public String getCardId() {
        return cardId;
    }

    /**
     * @param cardId
     */
    public void setCardId(String cardId) {
        this.cardId = cardId;
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