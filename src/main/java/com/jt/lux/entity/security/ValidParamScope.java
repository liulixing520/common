package com.jt.lux.entity.security;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * �ֻ���ʽУ��
 */
@Table(name = "phone_valid_param_scope")
public class ValidParamScope {
    /**
     * ��¼id
     */
    @Id
    private Long id;

    /**
     * ����Ĳ���ֵ
     */
    @Column(name = "allow_value")
    private String allowValue;

    /**
     * ����ֵ���ͣ�01-�ֻ���
     */
    private String type;

    /**
     * ����ʱ��
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * �޸�ʱ��
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * ��ȡ��¼id
     *
     * @return id - ��¼id
     */
    public Long getId() {
        return id;
    }

    /**
     * ���ü�¼id
     *
     * @param id ��¼id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * ��ȡ����Ĳ���ֵ
     *
     * @return allow_value - ����Ĳ���ֵ
     */
    public String getAllowValue() {
        return allowValue;
    }

    /**
     * ��������Ĳ���ֵ
     *
     * @param allowValue ����Ĳ���ֵ
     */
    public void setAllowValue(String allowValue) {
        this.allowValue = allowValue;
    }

    /**
     * ��ȡ����ֵ���ͣ�01-�ֻ���
     *
     * @return type - ����ֵ���ͣ�01-�ֻ���
     */
    public String getType() {
        return type;
    }

    /**
     * ���ò���ֵ���ͣ�01-�ֻ���
     *
     * @param type ����ֵ���ͣ�01-�ֻ���
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * ��ȡ����ʱ��
     *
     * @return create_time - ����ʱ��
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * ���ô���ʱ��
     *
     * @param createTime ����ʱ��
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * ��ȡ�޸�ʱ��
     *
     * @return update_time - �޸�ʱ��
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * �����޸�ʱ��
     *
     * @param updateTime �޸�ʱ��
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}