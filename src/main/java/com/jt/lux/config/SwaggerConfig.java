package com.jt.lux.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig implements EnvironmentAware {

    private final Logger logger = LoggerFactory.getLogger(SwaggerConfig.class);
    private static final String DEFAULT_INCLUDE_PATTERN = "/api/.*";
    private RelaxedPropertyResolver propertyResolver;
    @Override
    public void setEnvironment(Environment environment) {
        this.propertyResolver = new RelaxedPropertyResolver(environment, "swagger.");
    }

    @Bean
    public Docket swaggerSpringfoxDocket() {
        logger.debug("Starting Swagger");
        StopWatch watch = new StopWatch();
        watch.start();
        Docket swaggerSpringMvcPlugin = new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .genericModelSubstitutes(ResponseEntity.class).select().paths(regex(DEFAULT_INCLUDE_PATTERN))
                .build().securitySchemes(Collections.singletonList(apiKey()))
                .groupName("G1")
                .securityContexts(Collections.singletonList(securityContext()));
        watch.stop();
        logger.debug("Started Swagger in {} ms", watch.getTotalTimeMillis());
        return swaggerSpringMvcPlugin;
    }

    private ApiInfo apiInfo() {
        logger.info("api docs title : " + propertyResolver.getProperty("title"));
        return new ApiInfo(propertyResolver.getProperty("title"), propertyResolver.getProperty("description"),
                propertyResolver.getProperty("version"), propertyResolver.getProperty("termsOfServiceUrl"),
                new Contact("", "", ""), propertyResolver.getProperty("license"),
                propertyResolver.getProperty("licenseUrl"), new ArrayList<>());
    }

    private ApiKey apiKey() {
        return new ApiKey("access_token", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex(DEFAULT_INCLUDE_PATTERN)).build();
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        return Collections
                .singletonList(new SecurityReference("access_token", new AuthorizationScope[] { authorizationScope }));
    }
}
