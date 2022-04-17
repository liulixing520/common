package com.jt.lux.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "app.auth")
public class AuthProperties {
	private String checkUri;
	private String accessTokenUri;
	private String refreshTokenUri;
	private String userloginUri;
	private String smsUri;
	private String registerUri;
	private String uploadFileUri;
	private String scanOCRUri;
}
