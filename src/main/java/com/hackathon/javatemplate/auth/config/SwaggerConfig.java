package com.hackathon.javatemplate.auth.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(metaData())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hackathon.javatemplate.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo metaData(){
        return new ApiInfo("Hackathon Template","This is hackathon sample project","1.0","terms of service",
                new Contact("Hackathon Template","https://www.thinkitive.com/","hackathon@thinkitive.com"),
                "Licenses of API", "API licenses URL", Collections.emptyList());
    }
}
