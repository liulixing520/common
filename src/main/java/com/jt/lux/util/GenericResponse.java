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

	/**重复提交异常码，400*/
	public static final String RESUBMIT_NG = "400";

	/**密码过期异常码，9001*/
	public static final String PWEXPIRED_NG = "9001";

	@ApiModelProperty(name = "code", value = "响应码", required=true, position=0)
	@JsonProperty("code")
	private String code;
	
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

	public static final ResponseEntity<?> reSubmitNg(String msg) {
		GenericResponse resp = new GenericResponse(RESUBMIT_NG);
		resp.setMsg(msg);
		return ResponseEntity.ok(resp);
	}

	public static final ResponseEntity<?> pwExpiredNg(String msg) {
		GenericResponse resp = new GenericResponse(PWEXPIRED_NG);
		resp.setMsg(msg);
		return ResponseEntity.ok(resp);
	}
	
}
