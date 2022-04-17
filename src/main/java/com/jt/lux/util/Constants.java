package com.jt.lux.util;

/**
 * @描述： 常量
 * @作者： lux
 * @创建日期： 2018-7-28 14:52

 */
public class Constants {

    /**
     * 手机号不能为空
     */
    public static final String PHONE_NUM_IS_NULL = "手机号不能为空";
	/**
	 * 登录账号不能为空
	 */
	public static final String USER_NAME_IS_NULL = "登录账号不能为空";
	/**
	 * 密码不能为空
	 */
	public static final String PASSWORD_IS_NULL = "密码不能为空";

	/**
	 * 登录账号不存在
	 */
	public static final String USER_IS_NOT_EXIST = "登录账号不存在";

	/**
	 * 密码已过期
	 */
	public static final String PASSWORD_EXPIRED = "密码已过期";

	/**
     * 登录失败
     */
    public static final String LOGIN_FAIL = "账号或密码错误";

	/**
	 * 手机号已存在
	 */
	public static final String PHONENUM_ISEXIST = "手机号已存在";

	/**
	 * 账号密码登录
	 */
	public static final String LOGIN_TYPE_PASSWORD = "password";

	/**
	 * 手机号登录
	 */
	public static final String LOGIN_TYPE_PHONE = "phone";

	/**
	 * session key
	 */
	public static final String LOGIN = "LOGIN";


	/**
	 * 短信服务
	 */
	public static  final String MOBILE_FAILD = "手机号格式错误，请重新输入!";

	public static  final String MOBILE_IS_NOT_EXIST = "手机号未注册,请先添加用户!";

	public static  final String TIP_MSG ="短信验证码发送次数，已超过当日限制";

	public static  final String SEND_MSG_FAILD ="短信发送失败，请联系开发人员!";

	public static  final String MSG_UPDATE_FAILD ="短信信息修改失败";

	public static  final String MSG_ADD_FAILD ="短信信息添加失败";

	public static  final String USER_UNCERTIFIED ="用户未认证或审核尚未通过";


}
