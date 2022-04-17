package com.jt.lux.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.http.ResponseEntity;

//import io.swagger.annotations.ApiModelProperty;

@Data
@JsonAutoDetect(fieldVisibility = Visibility.NONE, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GenericResponse {
	/** 正常状态码，0000， */
	public static final String CODE_OK = "0000";
	
	/** 异常态码，9999 */
	public static final String CODE_NG = "9999";

	/** token过期，1001 */
	public static final String TOKEN_EXPIRED = "1001";

	/** refreshtoken过期，1002 */
	public static final String REFRESH_TOKEN_EXPIRED = "1002";

	/** 签到失败*/
	public static final String SIGN_NG = "2001";


	/**
	 * 响应码
	 */
	@ApiModelProperty(name = "code", value = "响应码", required=true, position=0)
	@JsonProperty("code")
	private String code;


	/**
	 * 提示消息
	 */
	@ApiModelProperty(name = "msg", value = "提示消息", position=1)
	@JsonProperty("msg")
	private String msg;
	
	private static final GenericResponse OK_RESP = new GenericResponse(CODE_OK);
	
	public GenericResponse() {
		
	}
	
	public GenericResponse(String code) {
		this.code = code;
	}
	
	public GenericResponse(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public static final ResponseEntity<?> ok() {
		return ResponseEntity.ok(OK_RESP);
	}

	public static final ResponseEntity<?> ok(String msg) {
		GenericResponse resp = new GenericResponse(CODE_OK);
		resp.setMsg(msg);
		return ResponseEntity.ok(resp);
	}
	
	public static final ResponseEntity<?> ng(String msg) {
		GenericResponse resp = new GenericResponse(CODE_NG);
		resp.setMsg(msg);
		return ResponseEntity.ok(resp);
	}

	public static final ResponseEntity<?> ng(String code,String msg) {
		GenericResponse resp = new GenericResponse(code,msg);
		return ResponseEntity.ok(resp);
	}


	public static final ResponseEntity<?> tokenExpired(String msg) {
		GenericResponse resp = new GenericResponse(TOKEN_EXPIRED);
		resp.setMsg(msg);
		return ResponseEntity.ok(resp);
	}

	public static final ResponseEntity<?> signNg(String msg) {
		GenericResponse resp = new GenericResponse(SIGN_NG);
		resp.setMsg(msg);
		return ResponseEntity.ok(resp);
	}

}
