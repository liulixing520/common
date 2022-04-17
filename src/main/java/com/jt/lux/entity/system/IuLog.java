package com.jt.lux.entity.system;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 枚举
 */
public class IuLog {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 序列号
     */
    @Column(name = "SEQ_NO")
    private String seqNo;

    /**
     * 返回结果
     */
    @Column(name = "RET_CODE")
    private String retCode;

    /**
     * 日志类别
     */
    @Column(name = "LOG_TYPE")
    private String logType;

    /**
     * 入参
     */
    @Column(name = "REQ_MSG")
    private String reqMsg;
    /**
     * 返回参数
     */
    @Column(name = "RESP_MSG")
    private String respMsg;
    /**
     * 时间
     */
    @Column(name = "CREATED_STAMP")
    private Date createdStamp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String getReqMsg() {
        return reqMsg;
    }

    public void setReqMsg(String reqMsg) {
        this.reqMsg = reqMsg;
    }

    public String getRespMsg() {
        return respMsg;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }

    public Date getCreatedStamp() {
        return createdStamp;
    }

    public void setCreatedStamp(Date createdStamp) {
        this.createdStamp = createdStamp;
    }
}