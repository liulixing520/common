package com.jt.lux.service.common;

import com.jt.lux.entity.security.MenuTree;
import com.jt.lux.entity.security.SecurityGroup;
import com.jt.lux.entity.security.UserLoginSecurityGroup;
import com.jt.lux.entity.system.SystemProperty;
import com.jt.lux.exception.ServiceException;
import com.jt.lux.mapper.security.MenuTreeMapper;
import com.jt.lux.mapper.security.SecurityGroupMapper;
import com.jt.lux.mapper.security.UserLoginSecurityGroupMapper;
import com.jt.lux.mapper.system.SystemPropertyMapper;
import com.jt.lux.util.*;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

/**
 * @������ ��������
 * @���ߣ� lux
 * @�������ڣ� 2021-4-28 23:08
 */
@Service
public class CommonServiceImpl implements CommonService{
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MenuTreeMapper menuTreeMapper;

    @Autowired
    private SecurityGroupMapper securityGroupMapper;

    @Autowired
    private UserLoginSecurityGroupMapper userLoginSecurityGroupMapper;
    @Autowired
    private SystemPropertyMapper systemPropertyMapper;


    private static String  SOURCEID = "yougu";

    @Value("${jtpf.ocr.secretId}")
    private String secretId;
    @Value("${jtpf.ocr.secretKey}")
    private String secretKey;
    @Value("${jtpf.ocr.idCardUrl}")
    private String idCardUrl;

    @Override
    public ResponseEntity<?> scanOCR(MultipartFile file, String type) {
        try {
            byte[] fileByte = null;
            try {
                fileByte = file.getBytes();
            } catch (IOException e) {
                e.printStackTrace();
                return GenericDataResponse.ng("ͼ���ļ�ת��ʧ��");
            }
            String voiceBase64= Base64.getEncoder().encodeToString(fileByte);
            log.info("====OCR��α�ʶ====>> "+type);
            String randNumber = RandomCodeUtil.getRandNumber(5);
            Long dateTime = System.currentTimeMillis() / 1000;
            // TreeMap�����Զ�����
            TreeMap<String, Object> paramsTree = new TreeMap<String, Object>();
            paramsTree.put("Nonce", randNumber);//������������� Timestamp �������������ڷ�ֹ�طŹ�����
            paramsTree.put("Timestamp", dateTime);// ʵ�ʵ���ʱӦ��ʹ��ϵͳ��ǰʱ��
            paramsTree.put("Region", "ap-beijing");
            paramsTree.put("SecretId", secretId);
            paramsTree.put("Action", type);
            paramsTree.put("Version", "2018-11-19");
            paramsTree.put("ImageBase64", voiceBase64);
            String str2sign = HmacSHA1Util.getStringToSign("POST","ocr.tencentcloudapi.com",paramsTree);
            log.info("====���ɲ����ַ���====>>"+str2sign);
            String signature = HmacSHA1Util.sign(str2sign,secretKey);//ǩ��
            log.info("====����ǩ��====>>"+signature);
            //�������
            Map<String, String> params = new HashMap<String, String>();
            params.put("Action",type);
            params.put("ImageBase64",voiceBase64);
            params.put("Nonce",randNumber);
            params.put("Region","ap-beijing");
            params.put("SecretId",secretId);
            params.put("Timestamp",dateTime.toString());
            params.put("Version","2018-11-19");
            params.put("Signature",signature);
            String bodyData = HTTPUtils.post(idCardUrl,params,null);//�������󣬻�ȡ���֤��Ϣ
            log.info("====OCR��������====>>"+bodyData);
            com.alibaba.fastjson.JSONObject json = com.alibaba.fastjson.JSONObject.parseObject(bodyData);
            log.info("====OCRjson����====>>"+json);
            if(json.containsKey("Error")){
                String msg = json.getString("Error");
                return GenericDataResponse.ng(msg);
            }
            return GenericDataResponse.okWithData(json);
        }catch (Exception e){
            e.printStackTrace();
            log.error("",e);
            String msg = e.getMessage();
            return GenericDataResponse.ng(msg);
        }
    }


    @Override
    public ResponseEntity<?> menuList(@Param("pn")Integer pn, @Param("ps")Integer ps) {
        List<MenuTree> menulist = menuTreeMapper.selectAllMenuTree(pn,ps);
        Integer n = menuTreeMapper.selectAllMenuTreeCount();
        return GenericListResponse.listAndCount(menulist,n);
    }

    @Override
    public ResponseEntity<?> securityList() {
        List<SecurityGroup> menulist = securityGroupMapper.selectAll();
        return GenericListResponse.listAndCount(menulist,menulist.size());
    }

    @Override
    public ResponseEntity<?> secParty(String groupId) {
        UserLoginSecurityGroup group = new UserLoginSecurityGroup();
        group.setGroupId(groupId);
        List<UserLoginSecurityGroup> list =  userLoginSecurityGroupMapper.select(group);
        return GenericListResponse.listNoCount(list);
    }

    @Override
    public ResponseEntity<?> systemPropety() {
        SystemProperty serviceProperty = new SystemProperty();
        serviceProperty.setSystemResourceId(SOURCEID);
        List<SystemProperty> listSystem = systemPropertyMapper.select(serviceProperty);
        return GenericListResponse.listNoCount(listSystem);
    }

    @Override
    public ResponseEntity<?> updateSystemPropety(SystemProperty systemProperty) {
        SystemProperty serviceProperty = new SystemProperty();
        serviceProperty.setSystemResourceId(systemProperty.getSystemResourceId());
        serviceProperty.setSystemPropertyId(systemProperty.getSystemPropertyId());

        SystemProperty temp  = systemPropertyMapper.selectOne(serviceProperty);
        if(null==temp){
            throw new ServiceException("δ�ҵ�����");
        }

        temp.setSystemPropertyValue(systemProperty.getSystemPropertyValue());
        systemPropertyMapper.updateByPrimaryKeySelective(temp);
        return GenericDataResponse.ok();
    }
}
