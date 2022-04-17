package com.jt.lux.service.common;

import com.jt.lux.entity.system.SystemProperty;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @描述： 公共服务
 * @作者： lux
 * @创建日期： 2021-4-28 23:06
 */
@Service
public interface CommonService {


    /**
     * @Description:   获取银行卡\身份证信息接口
     */
    ResponseEntity<?> scanOCR(MultipartFile file,String type);

    /**
     * 菜单列表
     * @param pn
     * @param ps
     * @return
     */
    ResponseEntity<?> menuList(@Param("pn")Integer pn, @Param("ps")Integer ps);


    /**
     * 权限列表
     * @return
     */
    ResponseEntity<?> securityList();

    /**
     * 人员列表
     * @return
     */
    ResponseEntity<?> secParty(String groupId);


    /**
     * 系统管理设置列表
     * @return
     */
    ResponseEntity<?> systemPropety();


    /**
     * 系统管理设置
     * @param systemProperty
     */
    ResponseEntity<?> updateSystemPropety(SystemProperty systemProperty);
}
