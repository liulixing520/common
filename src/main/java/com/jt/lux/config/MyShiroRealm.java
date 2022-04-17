package com.jt.lux.config;


import com.jt.lux.entity.security.UserLogin;
import com.jt.lux.exception.PassWordExpiredException;
import com.jt.lux.mapper.security.UserLoginMapper;
import com.jt.lux.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Slf4j
public class MyShiroRealm extends AuthorizingRealm{


	@Autowired
	private UserLoginMapper userLoginMapper;


	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
		//��ȡusername
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		String userName = token.getUsername();
		UserLogin userLogin = userLoginMapper.findByMobileNum(userName);
		if(userLogin == null){
			log.warn(Constants.USER_IS_NOT_EXIST+"userName��{}",userName);
			throw new AuthenticationException(Constants.USER_IS_NOT_EXIST);
		}

		if(userLogin.getDisabledDateTime() !=null && new Date().compareTo(userLogin.getDisabledDateTime())>0){
			log.warn(Constants.PASSWORD_EXPIRED+"userName��{}",userName);
			throw new PassWordExpiredException(Constants.PASSWORD_EXPIRED);
		}

		ByteSource credentialsSalt = ByteSource.Util.bytes(userLogin.getSalt());
		AuthenticationInfo authcInfo=new SimpleAuthenticationInfo(userLogin, userLogin.getCurrentPassword(),credentialsSalt, this.getName());

		//�建���е���Ȩ��Ϣ����֤ÿ�ε�½ ������������Ȩ����ΪAuthorizingRealm���ȼ�黺����û�� ��Ȩ��Ϣ���ٵ�����Ȩ����
		super.clearCachedAuthorizationInfo(authcInfo.getPrincipals());

		SecurityUtils.getSubject().getSession().setAttribute(Constants.LOGIN, userLogin);
		
		return authcInfo;
		//���ظ���ȫ��������securityManager����securityManager�ȶ����ݿ��ѯ���������ҳ���ύ������
		//��������⣬�������쳣��һֱ�׵�������
	}

}
