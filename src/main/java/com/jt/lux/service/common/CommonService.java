package com.jt.lux.service.common;

import com.jt.lux.entity.system.SystemProperty;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @������ ��������
 * @���ߣ� lux
 * @�������ڣ� 2021-4-28 23:06
 */
@Service
public interface CommonService {


    /**
     * @Description:   ��ȡ���п�\���֤��Ϣ�ӿ�
     */
    ResponseEntity<?> scanOCR(MultipartFile file,String type);

    /**
     * �˵��б�
     * @param pn
     * @param ps
     * @return
     */
    ResponseEntity<?> menuList(@Param("pn")Integer pn, @Param("ps")Integer ps);


    /**
     * Ȩ���б�
     * @return
     */
    ResponseEntity<?> securityList();

    /**
     * ��Ա�б�
     * @return
     */
    ResponseEntity<?> secParty(String groupId);


    /**
     * ϵͳ���������б�
     * @return
     */
    ResponseEntity<?> systemPropety();


    /**
     * ϵͳ��������
     * @param systemProperty
     */
    ResponseEntity<?> updateSystemPropety(SystemProperty systemProperty);
}
