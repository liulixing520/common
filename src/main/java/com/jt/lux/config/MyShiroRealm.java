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
		//获取username
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
			log.warn(Constants.USER_IS_NOT_EXIST+"userName：{}",userName);
			throw new AuthenticationException(Constants.USER_IS_NOT_EXIST);
		}

		if(userLogin.getDisabledDateTime() !=null && new Date().compareTo(userLogin.getDisabledDateTime())>0){
			log.warn(Constants.PASSWORD_EXPIRED+"userName：{}",userName);
			throw new PassWordExpiredException(Constants.PASSWORD_EXPIRED);
		}

		ByteSource credentialsSalt = ByteSource.Util.bytes(userLogin.getSalt());
		AuthenticationInfo authcInfo=new SimpleAuthenticationInfo(userLogin, userLogin.getCurrentPassword(),credentialsSalt, this.getName());

		//清缓存中的授权信息，保证每次登陆 都可以重新授权。因为AuthorizingRealm会先检查缓存有没有 授权信息，再调用授权方法
		super.clearCachedAuthorizationInfo(authcInfo.getPrincipals());

		SecurityUtils.getSubject().getSession().setAttribute(Constants.LOGIN, userLogin);
		
		return authcInfo;
		//返回给安全管理器，securityManager，由securityManager比对数据库查询出的密码和页面提交的密码
		//如果有问题，向上抛异常，一直抛到控制器
	}

}
