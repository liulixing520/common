package com.jt.lux.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

@Data
@Component
@ConfigurationProperties(prefix = "app.special-uri")
public class SpecialUriProperties {
	private Set<String> blackList;
	private Set<String> whiteList;
	private Set<String> grayList;
	private Map<String,Map<String,String>> timeoutMap;
}
