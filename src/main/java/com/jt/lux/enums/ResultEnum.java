package com.jt.lux.enums;

/**
 * 
 * 描述: 状态码和简单描述枚举
 * @date 2018年1月15日 上午10:40:25
 */
public enum ResultEnum implements BaseEnum{
	/**
	 * 成功
	 */
    SUCCESS("0000", "成功"),
    /**
     * 无效的验证码，请重新输入
     */
     INVALID_KAPTCHA("9004","无效的验证码，请重新输入"),

    /**
     * 错误的验证码，请重新输入
     */
    FAILD_KAPTCHA("9005","错误的验证码，请重新输入"),

    /**
     *手机号已注册，请登录
     */
    PHONENUM_ISEXIST("9006","手机号已注册，请登录"),
    /**
     * 用户不存在
     */
    USER_ISNOTEXIST("9007","用户不存在"),
    /**
	 * 请求路径错误
	 */
    URL_ERROR("404","请求路径错误"),
    /**
	 * 参数不合法
	 */
    PARAM_ERROR("400", "参数不合法"),
    /**
     * 参数不合法,缺少参数
     */
    PARAM_ERROR_LACK("40001", "缺少参数"),
    /**
	 * 请求不合法
	 */
    REQUEST_ERROR("405","请求不合法"),
    /**
     * 请求方式错误
     */
    REQUEST_METHOD_ERROR("4051","请求方式错误"),
    /**
	 * 服务器异常
	 */
    SERVER_ERROR("500","服务器异常"),

    /**
	 * 数据修改失败
	 */
    UPATE_FAILED("3001","数据修改失败"),
    /**
	 * 添加失败
	 */
    ADD_FAILED("3002","数据添加失败！"),
    /**
     * 查询失败
     */
    FIND_FAILED("3003","数据不存在！"),
    /**
     *userCode必填
     */
    USER_CODE_REQUIRED("3004","userCode必填！"),

    /**
     * userId必填
     */
    USER_ID_REQUIRED("3005","userId必填！"),
    /**
     *userType必填
     */
    USER_TYPE_REQUIRED("3006","userType必填！"),
    /**
     * 没有操作权限
     */
    ROLE_ERROR("3007","没有操作权限"),
    /**
     * 渠道编码必传
     */
    CHANNELCODE_ERROR("3008","渠道编码必传"),

    LOG_ADD_ERROR("3009", "日志添加失败"),

    PHONENUM_CHANGE_FAILED("3010", "手机号修改失败");
    ;

    private String code;

    private String message;

    ResultEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }


}
